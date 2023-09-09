package com.kang.subwayAlarm.subway;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kang.subwayAlarm.member.MemberDAO;

@Controller
public class subwayController {
	
	@Autowired
	private MemberDAO mDAO;
	
	@Autowired
	private subwayDAO sDAO;
	
	private boolean isFirstReq;
	
	public subwayController() {
		isFirstReq = true;
	}
	
	//open api 데이터 JSON방식으로 가지고옴
	@RequestMapping(value = "/subway.get", method = RequestMethod.GET,
			produces = "application/json; charset=UTF-8")
	public @ResponseBody ArrayList<Subway> subwayJSON() {
		return sDAO.getSubwayJSON();
	}
	
	//지하철 실시간 도착정보 페이지로 이동시 가지고온 정보 insert 및 보여주기
	@RequestMapping(value = "/subway.go", method = RequestMethod.GET)
	public String subwayGO(HttpServletRequest req) {
		if(isFirstReq) {
			sDAO.SubwayInfoCount();
			isFirstReq = false;
		}
		ArrayList<Subway> subways = subwayJSON();
		String insert = null;
		for(Subway ss : subways) {
			insert = sDAO.insertSubway(ss);			
		}
		System.out.println(insert);
		mDAO.loginCheck(req);
		sDAO.clearSearch(req);
	    sDAO.getSubwayList(1, req);
		req.setAttribute("contextPage", "subway/subwayInfo.jsp");
		return "index";
	}
	
	//기존 지하철 정보 삭제, 새로운 정보 보기
	@RequestMapping(value = "/subway.delete", method = RequestMethod.GET)
	public String deleteSubway(Subway s, HttpServletRequest req) {
		mDAO.loginCheck(req);
		sDAO.deleteSubway(s);
		ArrayList<Subway> subways = subwayJSON();
		String insert = null;
		for(Subway ss : subways) {
			insert = sDAO.insertSubway(ss);			
		}
		System.out.println(insert);
		sDAO.getSubwayList(1, req);
		req.setAttribute("contextPage", "subway/subwayInfo.jsp");
		return "index";
	}
	
	//지하철 정보 검색
	@RequestMapping(value = "/subway.search", method = RequestMethod.GET)
	public String searchSubway(HttpServletRequest req) {
		mDAO.loginCheck(req);
		sDAO.getSearchSubway(req);
		sDAO.getSubwayList(1, req);
		req.setAttribute("contextPage", "subway/subwayInfo.jsp");
		return "index";
	}
	
	//지하철 정보 페이징 처리
	@RequestMapping(value = "/subway.page.change", method = RequestMethod.GET)
	public String subwayPageChange(HttpServletRequest req) {
		mDAO.loginCheck(req);
		int p = Integer.parseInt(req.getParameter("p"));
		sDAO.getSubwayList(p, req);
		req.setAttribute("contextPage", "subway/subwayInfo.jsp");
		return "index";
	}
}
