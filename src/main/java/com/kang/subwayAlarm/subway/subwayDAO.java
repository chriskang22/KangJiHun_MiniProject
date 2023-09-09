package com.kang.subwayAlarm.subway;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kang.http.client.KangHttpClient;
import com.kang.subwayAlarm.SiteOption;

@Service
public class subwayDAO {
	private int allSubwayInfoCount; 
	
	@Autowired
	private SqlSession ss;
	
	@Autowired
	private SiteOption so;
	
	public ArrayList<Subway> getSubwayJSON() {
		try {
			InputStream is = KangHttpClient.download("http://swopenapi.seoul.go.kr/api/subway/"
					+ "68796a56626a686b373755486d7a63/json/realtimeStationArrival/0/20/");
			String str = KangHttpClient.convert(is, "UTF-8");
			
			Subway s = new Subway();
			JSONParser jp = new JSONParser();
			JSONObject jo = (JSONObject) jp.parse(str);
			JSONArray ja = (JSONArray) jo.get("realtimeArrivalList");
			JSONObject jos = null;
			
			ArrayList<Subway> subways = new ArrayList<Subway>();
			for (int i = 0; i < ja.size(); i++) {
				s = new Subway();
				jos = (JSONObject) ja.get(i);
				s.setBtrainNo(jos.get("btrainNo").toString());
				s.setStatnNm(jos.get("statnNm").toString());
				s.setTrnsitCo(jos.get("trnsitCo").toString());
				s.setBtrainSttus(jos.get("btrainSttus").toString());
				s.setBarvlDt(jos.get("barvlDt").toString());
				s.setBstatnNm(jos.get("bstatnNm").toString());
				String recptnDt = jos.get("recptnDt").toString();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date when = sdf.parse(recptnDt);
				s.setRecptnDt(when);
				s.setArvlMsg2(jos.get("arvlMsg2").toString());
				subways.add(s);
			}
			return subways;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//JSON 파싱된 데이터 insert하기
	public String insertSubway(Subway s) {
		try {
			if(ss.getMapper(SubwayMapper.class).insertSubway(s) == 1) {
				return "insert성공";
			}
			else {				
				return "insert실패";
			}
		} catch (Exception e) {
			return "insert에러";
		}
	}
	
	//기존 데이터 삭제
	public String deleteSubway(Subway s) {
		try {
			if(ss.getMapper(SubwayMapper.class).subwayDelete(s) == 1) {
				return "삭제성공";
			}
			else {				
				return "삭제실패";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "삭제에러";
		}
	}
	
	//모든 지하철 정보 총 갯수
	public void SubwayInfoCount() {
		allSubwayInfoCount = ss.getMapper(SubwayMapper.class).AllSubwayInfo();
	}
	
	//지하철 정보 List 보여주기 및 페이징 처리
	public void getSubwayList(int page, HttpServletRequest req) {
		req.setAttribute("curPage", page);
		String search = (String) req.getSession().getAttribute("search");
		int subwayCount = 0;
		if(search == null) {
			subwayCount = allSubwayInfoCount;
			search = "";
		}
		else {
			SubWaySelector sSel2 = new SubWaySelector(search, 0, 0);
			subwayCount = ss.getMapper(SubwayMapper.class).searchCount(sSel2);
		}
		int allPageCount = (int) Math.ceil((double)subwayCount / so.getSubwayPerPage());
		req.setAttribute("allPageCount", allPageCount);
		
		int start = (page - 1) * so.getSubwayPerPage() + 1;
		int end = (page == allPageCount) ? subwayCount : start + so.getSubwayPerPage() - 1;
		
		SubWaySelector sSel = new SubWaySelector(search, start, end);
		List<Subway> subways = ss.getMapper(SubwayMapper.class).getSearch(sSel);
		req.setAttribute("subways", subways);
	}	
	
	//지하철 정보에 처음 접근 or 검색어 입력을 하지 않았을 때
	public void clearSearch(HttpServletRequest req) {
		req.getSession().setAttribute("search", null);
	}
	
	//검색어 값 가져오기
	public void getSearchSubway(HttpServletRequest req) {
		String search = req.getParameter("search");
		req.getSession().setAttribute("search", search);
	}
}
