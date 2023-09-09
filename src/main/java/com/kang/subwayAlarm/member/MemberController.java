package com.kang.subwayAlarm.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {
	@Autowired
	private MemberDAO mDAO;
	
	//회원가입 페이지로 이동
	@RequestMapping(value = "/member.signupGo", method = RequestMethod.GET)
	public String signUpGo(HttpServletRequest req) {
		mDAO.loginCheck(req);
		req.setAttribute("contextPage", "member/signup.jsp");
		return "index";
	}
	
	//회원가입 등록 기능
	@RequestMapping(value = "/member.signup", method = RequestMethod.POST)
	public String signUp(Member m, HttpServletRequest req) {
		mDAO.signup(m, req);
		mDAO.loginCheck(req);
		req.setAttribute("contextPage", "home.jsp");
		return "index";
	}
	
	//로그인 기능
	@RequestMapping(value = "/member.login", method = RequestMethod.POST)
	public String login(Member m, HttpServletRequest req) {
		mDAO.login(m, req);
		mDAO.loginCheck(req);
		req.setAttribute("contextPage", "home.jsp");
		return "index";
	}
	
	//로그아웃 기능
	@RequestMapping(value = "/member.logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest req) {
		mDAO.logout(req);
		mDAO.loginCheck(req);
		req.setAttribute("contextPage", "home.jsp");
		return "index";
	}
	
	//회원정보 페이지로 이동 및 보여주기
	@RequestMapping(value = "/member.info.go", method = RequestMethod.GET)
	public String memberInfoGo(HttpServletRequest req) {
		if(mDAO.loginCheck(req)) {
			mDAO.divideAddr(req);
			req.setAttribute("contextPage", "member/info.jsp");
		}
		else {
			req.setAttribute("contextPage", "home.jsp");
		}
		return "index";
	}
	
	//회원정보 수정하기
	@RequestMapping(value = "/member.update", method = RequestMethod.POST)
	public String memberUpdate(Member m, HttpServletRequest req) {
		mDAO.loginCheck(req);
		mDAO.update(m, req);
		mDAO.divideAddr(req);
		req.setAttribute("contextPage", "member/info.jsp");
		return "index";
	}
	
	//회원탈퇴 기능
	@RequestMapping(value = "/member.delete", method = RequestMethod.GET)
	public String memberDelete(Member m, HttpServletRequest req) {
		mDAO.delete(req);
		mDAO.logout(req);
		mDAO.loginCheck(req);
		req.setAttribute("contextPage", "home.jsp");
		return "index";
	}
}
