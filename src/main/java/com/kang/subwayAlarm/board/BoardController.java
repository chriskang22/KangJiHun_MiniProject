package com.kang.subwayAlarm.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kang.subwayAlarm.TokenMaker;
import com.kang.subwayAlarm.member.MemberDAO;

@Controller
public class BoardController {
	@Autowired
	private MemberDAO mDAO;
	
	@Autowired
	private BoardDAO bDAO;
	
	private boolean isFirstReq;
	
	public BoardController() {
		isFirstReq = true;
	}
	
	//게시판 List로 보여주기
	@RequestMapping(value = "/board.go", method = RequestMethod.GET)
	public String boardGO(HttpServletRequest req) {
		if (isFirstReq) {
			bDAO.countAllBoard();
			isFirstReq = false;
		}
		mDAO.loginCheck(req);
		bDAO.clearSerachBoard(req);
		bDAO.boardList(1, req);
		TokenMaker.makeToken(req);
		req.setAttribute("contextPage", "board/board.jsp");
		return "index";
	}
	
	//게시판 검색어 기능
	@RequestMapping(value = "/board.search", method = RequestMethod.GET)
	public String boardSearch(HttpServletRequest req) {
		mDAO.loginCheck(req);
		bDAO.getSearchBoard(req);
		bDAO.boardList(1, req);
		TokenMaker.makeToken(req);
		req.setAttribute("contextPage", "board/board.jsp");
		return "index";
	}
	
	//게시판 페이징 처리
	@RequestMapping(value = "/board.page.change", method = RequestMethod.GET)
	public String boardPageChange(HttpServletRequest req) {
		mDAO.loginCheck(req);
		int p = Integer.parseInt(req.getParameter("p"));
		bDAO.boardList(p, req);
		TokenMaker.makeToken(req);
		req.setAttribute("contextPage", "board/board.jsp");
		return "index";
	}
	
	//게시글 작성 페이지로 이동
	@RequestMapping(value = "/board.write.go", method = RequestMethod.GET)
	public String boardWriteGO(HttpServletRequest req) {
		mDAO.loginCheck(req);
		TokenMaker.makeToken(req);
		req.setAttribute("contextPage", "board/boardWrite.jsp");
		return "index";
	}
	
	//게시글 작성하기
	@RequestMapping(value = "/board.write", method = RequestMethod.POST)
	public String boardWrite(Board b, HttpServletRequest req) {
		if(mDAO.loginCheck(req)) {
			bDAO.regBoardWrite(b, req);
		}
		bDAO.boardList(1, req);
		TokenMaker.makeToken(req);
		req.setAttribute("contextPage", "board/board.jsp");
		return "index";
	}
	
	//게시글 상세보기 페이지로 이동(게시글 정보, 조회수, 댓글 정보 List)
	@RequestMapping(value = "/board.detail.go", method = RequestMethod.GET)
	public String boardDetailGO(Reply ry, int b_no, HttpServletRequest req, HttpServletResponse res) {
		mDAO.loginCheck(req);
		bDAO.deduplicateViews(b_no, req, res);
		bDAO.getBoardDetail(b_no, req);
		bDAO.replyList(b_no, req);
		TokenMaker.makeToken(req);
		req.setAttribute("contextPage", "board/boardDetail.jsp");
		return "index";
	}
	
	//게시판 좋아요
	@RequestMapping(value = "/board.good", method = RequestMethod.GET)
	public String boardGood(Board b, Reply ry, int b_no, HttpServletRequest req, HttpServletResponse res) {
		mDAO.loginCheck(req);
		bDAO.deduplicateViews(b_no, req, res);
		bDAO.bGood(b, req);
		bDAO.getBoardDetail(b_no, req);
		bDAO.replyList(b_no, req);
		TokenMaker.makeToken(req);
		req.setAttribute("contextPage", "board/boardDetail.jsp");
		return "index";
	}
	
	//게시판 좋아요 취소
	@RequestMapping(value = "/board.goodReset", method = RequestMethod.GET)
	public String boardGoodReset(Board b, Reply ry, int b_no, HttpServletRequest req, HttpServletResponse res) {
		mDAO.loginCheck(req);
		bDAO.deduplicateViews(b_no, req, res);
		bDAO.bGoodReset(b, req);
		bDAO.getBoardDetail(b_no, req);
		bDAO.replyList(b_no, req);
		TokenMaker.makeToken(req);
		req.setAttribute("contextPage", "board/boardDetail.jsp");
		return "index";
	}
	
	//게시판 삭제
	@RequestMapping(value = "/boardDetail.delete", method = RequestMethod.GET)
	public String boardDelete(Board b, HttpServletRequest req) {
		if(mDAO.loginCheck(req)) {
			bDAO.boardDelete(b, req);
		}
		bDAO.boardList(1, req);
		TokenMaker.makeToken(req);
		req.setAttribute("contextPage", "board/board.jsp");
		return "index";
	}
	
	//게시판 수정
	@RequestMapping(value = "/boardDetail.update", method = RequestMethod.GET)
	public String boardUpdate(Board b, HttpServletRequest req) {
		if(mDAO.loginCheck(req)) {
			bDAO.boardUpdate(b, req);
		}
		bDAO.boardList(1, req);
		TokenMaker.makeToken(req);
		req.setAttribute("contextPage", "board/board.jsp");
		return "index";
	}
	
	//댓글 작성하기
	@RequestMapping(value = "/reply.write", method = RequestMethod.POST)
	public String regReply(int r_b_no, Reply ry, HttpServletRequest req, HttpServletResponse res) {
		mDAO.loginCheck(req);
		bDAO.deduplicateViews(r_b_no, req, res);
		bDAO.getBoardDetail(r_b_no, req);
		bDAO.regReply(ry, req);
		bDAO.replyList(r_b_no, req);
		TokenMaker.makeToken(req);
		req.setAttribute("contextPage", "board/boardDetail.jsp");
		return "index";
	}
	
	//댓글 삭제
	@RequestMapping(value = "/reply.delete", method = RequestMethod.GET)
	public String replyDelete(int r_b_no, Reply ry, Board b, HttpServletRequest req, HttpServletResponse res) {
		mDAO.loginCheck(req);
		bDAO.deduplicateViews(r_b_no, req, res);
		bDAO.getBoardDetail(r_b_no, req);
		bDAO.replyDelete(ry, req);
		bDAO.replyList(r_b_no, req);
		TokenMaker.makeToken(req);
		req.setAttribute("contextPage", "board/boardDetail.jsp");
		return "index";
	}
}
