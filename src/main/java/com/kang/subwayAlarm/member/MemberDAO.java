package com.kang.subwayAlarm.member;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Service
public class MemberDAO {
	@Autowired
	private SqlSession ss;
	
	public boolean loginCheck(HttpServletRequest req) {
		Member m = (Member) req.getSession().getAttribute("loginMember");
		if(m != null) {
			req.setAttribute("loginPage", "member/loginSuccess.jsp");
			return true;
		}
		else {
			req.setAttribute("loginPage", "member/login.jsp");
			return false;
		}
	}
	
	//회원등록 기능
	public void signup(Member m, HttpServletRequest req) {
		try {
			String path = req.getSession().getServletContext().getRealPath("resources/img");
			MultipartRequest mr = new MultipartRequest(req, path, 10 * 1024 * 1024, "UTF-8", new DefaultFileRenamePolicy());			
			
			m.setSm_id(mr.getParameter("sm_id"));
			m.setSm_pw(mr.getParameter("sm_pw"));
			m.setSm_name(mr.getParameter("sm_name"));
			String photo = mr.getFilesystemName("sm_photo");
			String photo_kr = URLEncoder.encode(photo, "UTF-8").replace("+", " ");
			m.setSm_photo(photo_kr);
			String addr1 = mr.getParameter("sm_addr1");
			String addr2 = mr.getParameter("sm_addr2");
			String addr3 = mr.getParameter("sm_addr3");
			String addr = addr2 + "!" + addr3 + "!" + addr1;
			m.setSm_addr(addr);
			m.setSm_role(mr.getParameter("sm_role"));
			
			if(ss.getMapper(MemberMapper.class).signup(m) == 1) {
				req.setAttribute("r", "회원등록 성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("r", "회원등록 실패");
		}
	}
	
	public Members memberIdCheck(Member m) {
		return new Members(ss.getMapper(MemberMapper.class).getMemberById(m));
	}
	
	//로그인 기능
	public void login(Member inputM, HttpServletRequest req) {
		try {
			List<Member> lm = ss.getMapper(MemberMapper.class).getMemberById(inputM);
			if(lm.size() != 0) {
				Member m = lm.get(0);
				if(m.getSm_pw().equals(inputM.getSm_pw())) {
					req.getSession().setAttribute("loginMember", m);
					req.getSession().setMaxInactiveInterval(10 * 70);
					req.setAttribute("r", "로그인성공");
				}
				else {
					req.setAttribute("r", "로그인실패(PW오류)");
				}
			}
			else {
				req.setAttribute("r", "로그인실패(미가입ID)");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("r", "로그인실패-DB");
		}
	}
	
	//로그아웃 기능
	public void logout(HttpServletRequest req) {
		req.getSession().setAttribute("loginMember", null);
	}
	
	//회원정보에서 우편번호, 주소, 상세주소 보여주기
	public void divideAddr(HttpServletRequest req) {
		Member m = (Member) req.getSession().getAttribute("loginMember");
		String addr = m.getSm_addr();
		String[] addr2 = addr.split("!");
		req.setAttribute("addr", addr2);
	}
	
	//회원정보 수정하기
	public void update(Member m, HttpServletRequest req) {		
		String path = req.getSession().getServletContext().getRealPath("resources/img");
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(req, path, 10 * 1024 * 1024, "UTF-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("r", "수정실패(파일용량 초과)");
		}
		
		Member dm = (Member) req.getSession().getAttribute("loginMember");
		String oldFile = dm.getSm_photo();
		String newFile = mr.getFilesystemName("sm_photo");
		try {
			String id = mr.getParameter("sm_id");
			String pw = mr.getParameter("sm_pw");
			String name = mr.getParameter("sm_name");
			String addr1 = mr.getParameter("sm_addr1");
			String addr2 = mr.getParameter("sm_addr2");
			String addr3 = mr.getParameter("sm_addr3");
			
			if(newFile == null) {
				newFile = oldFile;
			}
			else {
				newFile = URLEncoder.encode(newFile, "UTF-8").replace("+", " ");		
			}
			m.setSm_id(id);
			m.setSm_pw(pw);
			m.setSm_name(name);
			m.setSm_addr(addr2 + "!" + addr3 + "!" + addr1);
			m.setSm_photo(newFile);
			
			if(ss.getMapper(MemberMapper.class).memberInfoUpdate(m) == 1) {
				req.setAttribute("r", "수정성공");
				
				req.getSession().setAttribute("loginMember", m);
				
				if(!oldFile.equals(newFile)) {
					oldFile = URLDecoder.decode(oldFile, "UTF-8");
					new File(path + "/ "+ oldFile).delete();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("r", "수정실패");
			if (!oldFile.equals(newFile)) {
				try {
					oldFile = URLDecoder.decode(oldFile, "UTF-8");
				} catch (UnsupportedEncodingException e2) {
					e2.printStackTrace();
					new File(path + "/" + oldFile).delete();
				}
			}
		}
	}

	//회원탈퇴하기
	public void delete(HttpServletRequest req) {
		try {
			Member m = (Member) req.getSession().getAttribute("loginMember");
			
			if(ss.getMapper(MemberMapper.class).memberDelete(m) == 1) {
				req.setAttribute("r", "탈퇴성공");
				String photo = m.getSm_photo();
				photo = URLDecoder.decode(photo, "UTF-8");

				String path = req.getSession().getServletContext().getRealPath("resources/img");

				new File(path + "/" + photo).delete();
			}
			else {
				req.setAttribute("r", "탈퇴실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("r", "탈퇴실패-DB");
		}
	}
}
