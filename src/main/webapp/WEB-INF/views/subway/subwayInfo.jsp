<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table id="searchSubwayTbl">
	<form action="subway.search" name="subwaySearchForm" 
	onsubmit="return subwaySearchCheck();">	
		<tr><td><input name="search" autocomplete="off" 
			placeholder="검색어를 입력하세요" autofocus="autofocus">
			<button>검색</button>
	</form>
			<button onclick="subwayDelete();">초기화</button>
		</td></tr>
</table>
<c:if test="${curPage != 1}">
	<table id="sublt"><tr><td align="center" 
	onclick="subwayPageChange(${curPage - 1});">&lt;</td></tr></table>
</c:if>
<c:if test="${curPage != allPageCount}">
	<table id="subgt"><tr><td align="center" 
	onclick="subwayPageChange(${curPage + 1});">&gt;</td></tr></table>
</c:if>	
<table border="1" id="subwayInfoTbl">
	<tr><th>열차번호</th>
		<th>지하철역명</th>
		<th>환승노선수</th>
		<th>열차종류(급행,ITX,일반)</th>
		<th>열차도착예정시간(단위:초)</th>
		<th>종착지하철역명</th>
		<th>열차도착정보를 생성한 시각</th>
		<th>도착메세지</th></tr>
	<c:forEach var="ss" items="${subways}">	
		<tr><td>${ss.btrainNo}</td>
			<td>${ss.statnNm}</td>
			<td>${ss.trnsitCo}</td>
			<td>${ss.btrainSttus}</td>
			<td>${ss.barvlDt}</td>
			<td>${ss.bstatnNm}</td>
			<td><fmt:formatDate value="${ss.recptnDt}" 
			pattern="yyyy-MM-dd HH:mm:ss" /></td>
			<td>${ss.arvlMsg2}</td></tr>	
	</c:forEach>	
</table>
</body>
</html>