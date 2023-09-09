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
<h1 id="boardH1">게시판 목록 LIST</h1>
<c:if test="${sessionScope.loginMember != null}">
<form action="board.search" name="boardSearchForm" 
onsubmit="return boardSearchListCheck();">	
	<table id="boardSearchTbl"><tr><td><input name="search" placeholder="검색어를 입력하세요" 
		autocomplete="off" autofocus="autofocus">
		<button>검색</button></td></tr></table>
</form>
</c:if>
<table id="boardListTbl">
	<tr><th>번호</th>
	<th>제목</th>
	<th>작성자ID</th>
	<th>등록일자</th>
	<th>수정일자</th>
	<th>글내용</th>
	<th>조회수</th>
	<th>좋아요</th></tr>
	<c:forEach var="b" items="${boards}">	
		<tr><td>${b.b_no}</td>
			<c:choose><c:when test="${sessionScope.loginMember.sm_id == null}">
				<td><div class="noLog">${b.b_title}</div></td></c:when>
				<c:otherwise><td><div onclick="boardDetailGO('${b.b_no}');">
				${b.b_title}</div></td></c:otherwise></c:choose>
			<td>${b.b_owner}</td>
			<td><fmt:formatDate value="${b.b_when}" 
			pattern="yyyy/MM/dd a hh:mm:ss" /></td>
			<c:if test="${b.b_updateWhen != null}">			
				<td><fmt:formatDate value="${b.b_updateWhen}" 
				pattern="yyyy/MM/dd a hh:mm:ss" /></td>
			</c:if>
			<td>${b.b_txt}</td>
			<td>${b.b_viewcnt}</td>
			<td>${b.b_good}</td></tr>
	</c:forEach>
</table>
<c:if test="${curPage != 1}">
	<table id="boardlt"><tr><td align="center" 
	onclick="boardPageChange(${curPage - 1});">&lt;</td></tr></table>
</c:if>
<c:if test="${curPage != allPageCount}">
	<table id="boardgt"><tr><td align="center" 
	onclick="boardPageChange(${curPage + 1});">&gt;</td></tr></table>
</c:if>
<c:if test="${sessionScope.loginMember != null}">	
	<table id="boardWriteGOTbl">
		<tr><td><button onclick="boardWrite();">
		게시글 작성</button></td></tr>		
	</table>
</c:if>	
</body>
</html>