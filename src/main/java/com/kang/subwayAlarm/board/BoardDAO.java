package com.kang.subwayAlarm.board;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.kang.subwayAlarm.SiteOption;
import com.kang.subwayAlarm.member.Member;

@Service
public class BoardDAO {
	private int AllboardCount;
	
	@Autowired
	private SqlSession ss;
	
	@Autowired
	private SiteOption so;
	
	//총 게시판 갯수  
	public void countAllBoard() {
		AllboardCount = ss.getMapper(BoardMapper.class).getAllBoardCount();
	}
	
	//게시판 글 작성하기
	public void regBoardWrite(Board b, HttpServletRequest req) {
		try {
			String token = req.getParameter("token");

			String st2 = (String) req.getSession().getAttribute("st");

			if (st2 != null && token.equals(st2)) {
				req.setAttribute("r", "글쓰기실패(새로고침)");
				return;
			}
			
			b.setB_title(req.getParameter("b_title"));
			b.setB_owner(req.getParameter("b_owner"));
			b.setB_txt(req.getParameter("b_txt").replace("\r\n", "<br>"));
			
			if(ss.getMapper(BoardMapper.class).regBoardWrite(b) == 1) {
				req.setAttribute("r", "게시판등록성공!");
				req.getSession().setAttribute("st", token);
				AllboardCount++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("r", "게시판등록실패!");
		}
	}
	
	//게시판 List로 보여주기
	public void boardList(int page, HttpServletRequest req) {
		req.setAttribute("curPage", page);
		String search = (String) req.getSession().getAttribute("search");
		int boardCount = 0;
		if(search == null) {
			boardCount = AllboardCount;
			search = "";
		}
		else {
			BoardSelector sSel2 = new BoardSelector(search, 0, 0);
			boardCount = ss.getMapper(BoardMapper.class).getSearchBoardCount(sSel2);
		}
		int allPageCount = (int) Math.ceil((double)boardCount / so.getBoardPerPage());
		req.setAttribute("allPageCount", allPageCount);
		
		int start = (page - 1) * so.getBoardPerPage() + 1;
		int end = (page == allPageCount) ? boardCount : start + so.getBoardPerPage() - 1;
		
		BoardSelector sSel = new BoardSelector(search, start, end);
		List<Board> boards = ss.getMapper(BoardMapper.class).getBoard(sSel);

		req.setAttribute("boards", boards);	
	}
	
	//게시판 정보에 처음 접근 or 검색어 입력을 하지 않았을 때
	public void clearSerachBoard(HttpServletRequest req) {
		req.getSession().setAttribute("search", null);
	}
	
	//검색어 값 가져오기
	public void getSearchBoard(HttpServletRequest req) {
		String search = req.getParameter("search");
		req.getSession().setAttribute("search", search);
	}
	
	//상세보기에서의 게시판 정보 보여주기
	public void getBoardDetail(int no, HttpServletRequest req) {
		Board boardDetail = ss.getMapper(BoardMapper.class).boardDetail(no);
		req.setAttribute("boardDetail", boardDetail);
	}
	
	//게시판 좋아요
	public int bGood(Board b, HttpServletRequest req) {
		Member m = (Member) req.getSession().getAttribute("loginMember");
		b.setB_owner(m.getSm_id());
		return ss.getMapper(BoardMapper.class).boardGoodCount(b);
	}
	
	//게시판 좋아요 취소
	public int bGoodReset(Board b, HttpServletRequest req) {
		Member m = (Member) req.getSession().getAttribute("loginMember");
		b.setB_owner(m.getSm_id());
		return ss.getMapper(BoardMapper.class).boardGoodCountReset(b);
	}
	
	//게시판 삭제하기
	public void boardDelete(Board b, HttpServletRequest req) {
		try {
			if(ss.getMapper(BoardMapper.class).boardDelete(b) == 1) {
				req.setAttribute("r", "게시판삭제성공!");
				AllboardCount--;
			}
			else {
				req.setAttribute("r", "게시판삭제실패!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("r", "게시판삭제실패!-DB");
		}
	}
	
	//게시판 조회수
	public int boardViews(int b_no) {
		return ss.getMapper(BoardMapper.class).boardViews(b_no);
	}
	
	//게시판 조회수(중복 방지)
	public ModelAndView deduplicateViews(int b_no, HttpServletRequest req, HttpServletResponse res) {
		// 해당 게시판 번호를 받아 상세페이지로 넘겨줌
		Board review = ss.getMapper(BoardMapper.class).boardDetail(b_no);
		ModelAndView view = new ModelAndView();
		
		Cookie[] cookies = req.getCookies();
		
		// 비교하기 위해 새로운 쿠키
		Cookie viewCookie = null;
		
		// 쿠키가 있을 경우
		if(cookies != null && cookies.length > 0) {
			for(int i = 0; i < cookies.length; i++) {
				// Cookie의 name이 cookie + b_no와 일치하는 쿠키를 viewCookie에 넣어줌 
				if(cookies[i].getName().equals("cookie" + b_no)) {
					System.out.println("처음 쿠키가 생성한 뒤 들어옴.");
					viewCookie = cookies[i];
				}
			}
		}
		
		if(review != null) {
			System.out.println("System - 해당 상세페이지로 넘어감");
			view.addObject("review", review);
			
			// 만일 viewCookie가 null일 경우 쿠키를 생성해서 조회수 증가 로직을 처리함.
			if(viewCookie == null) {
				System.out.println("cookie 없음");
				
				// 쿠키 생성(이름, 값)
				Cookie newCookie = new Cookie("cookie" + b_no, "|" + b_no + "|");
				// 쿠키 추가
				res.addCookie(newCookie);
				// 쿠키를 추가 시키고 조회수 증가시킴
				int result = boardViews(b_no);
				
				if(result > 0) {
					System.out.println("조회수 증가");
				}
				else {
					System.out.println("조회수 증가 에러");
				}
			}
			// viewCookie가 null이 아닐경우 쿠키가 있으므로 조회수 증가 로직을 처리하지 않음.
			else {
				System.out.println("cookie 있음");
				// 쿠키 값 받아옴.
				String value = viewCookie.getValue();
				
				System.out.println("cookie 값 : " + value);
			}
			
			view.setViewName("reviewDetail");
			return view;
		}
		else {
			// 에러 페이지 설정
			view.setViewName("board/board.jsp");
			return view;
		}
	}
	
	//게시판 수정하기
	public void boardUpdate(Board b, HttpServletRequest req) {
		try {
			if (ss.getMapper(BoardMapper.class).boardUpdate(b) == 1) {
				req.setAttribute("r", "게시판수정성공!");
			} else {
				req.setAttribute("r", "게시판수정실패!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("r", "게시판수정실패!-DB");
		}
	}
	
	//댓글 작성하기
	public void regReply(Reply ry, HttpServletRequest req) {
		try {
			String token = req.getParameter("token");
			String st2 = (String) req.getSession().getAttribute("st");
			if (st2 != null && token.equals(st2)) {
				req.setAttribute("r", "댓글쓰기실패(새로고침)");
				return;
			}
			
			Member m = (Member) req.getSession().getAttribute("loginMember");
			ry.setR_owner(m.getSm_id());
			
			if(ss.getMapper(BoardMapper.class).regReply(ry) == 1) {
				req.setAttribute("r", "댓글등록성공!");
				req.getSession().setAttribute("st", token);
			}
			else {				
				req.setAttribute("r", "댓글등록실패!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("r", "댓글등록실패!-DB");
		}
	}
	
	//댓글정보 List
	public void replyList(int r_b_no, HttpServletRequest req) {
		List<Reply> replys = ss.getMapper(BoardMapper.class).replyList(r_b_no);
		req.setAttribute("replys", replys);
	}
	
	//댓글 삭제
	public void replyDelete(Reply ry, HttpServletRequest req) {
		try {
			if(ss.getMapper(BoardMapper.class).replyDelete(ry) == 1) {
				req.setAttribute("r", "댓글삭제성공!");
			}
			else {				
				req.setAttribute("r", "댓글삭제실패!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("r", "댓글삭제실패!-DB");
		}
	}
}
