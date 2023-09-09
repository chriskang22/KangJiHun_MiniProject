<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function goodCheck() {
	let query = {b_no : ${boardDetail.b_no}};
	$.ajax({
		url : "board.good",
		data : query,
		success : function(data) {
			location.reload();
		}
	});
}
function goodResetCheck() {
	let query2 = {b_no : ${boardDetail.b_no}};
	$.ajax({
		url : "board.goodReset",
		data : query2,
		success : function(data) {
			location.reload();
		}
	});
}
</script>
</head>
<body>
<table border="1" id="boardDetailTbl">
	<tr><th>게시글 상세정보</th></tr>
	<tr><td><input name="b_no" value="${boardDetail.b_no}" type="hidden">
	등록일자 : <fmt:formatDate value="${boardDetail.b_when}" 
	pattern="yyyy/MM/dd a hh:mm:ss" /></td></tr>
	<tr><td>수정일자 : <fmt:formatDate 
	value="${boardDetail.b_updateWhen}" pattern="yyyy/MM/dd a hh:mm:ss" /></td></tr>
	<tr><td>조회수 : <input name="b_viewcnt" 
	value="${boardDetail.b_viewcnt}" readonly="readonly"></td></tr>
	<tr><td>제목 : <input name="b_title" id="title" 
	value="${boardDetail.b_title}" readonly="readonly"></td></tr>
	<tr><td>작성자ID : <input name="b_owner" 
	value="${boardDetail.b_owner}" readonly="readonly"></td></tr>
	<tr><td>글내용 : <textarea name="b_txt" id="txt" rows="8" cols="80" 
	readonly="readonly">${boardDetail.b_txt}</textarea></td></tr>
	<c:if test="${sessionScope.loginMember.sm_id == boardDetail.b_owner}"><tr><td>
		<button onclick="btnUpdate(${boardDetail.b_no}, '${boardDetail.b_txt}');">수정</button>
		<button onclick="btnDelete('${boardDetail.b_no}');">삭제</button>
		<button onclick="location.href='board.go';" 
		style="float: right;">글 목록</button>
	</td></tr></c:if>
	<c:if test="${sessionScope.loginMember.sm_id != boardDetail.b_owner}">
		<tr><td><button onclick="location.href='board.go';" 
		style="float: right;">글 목록</button></td></tr>
	</c:if>	
	<c:if test="${sessionScope.loginMember.sm_id != boardDetail.b_owner}">
		<tr><td>좋아요 : ${boardDetail.b_good}
			<c:choose>
			<c:when test="${boardDetail.b_likeCheck == 0}">
				<input type="button" value="♥" onclick="goodCheck();"></c:when>
			<c:when test="${boardDetail.b_likeCheck == 1}">			
				<input type="button" value="♡" onclick="goodResetCheck();"></c:when>
			</c:choose>
		</td></tr>
	</c:if>
</table>
<p><p>
<form action="reply.write" method="post" name="replyWriteForm" 
onsubmit="return replyWriteCheck();">
	<input name="token" value="${token}" type="hidden">
	<table border="1" id="regReplyTbl">
		<tr><td><input name="r_b_no" value="${boardDetail.b_no}" type="hidden">
		댓글내용 : <textarea name="r_txt" placeholder="댓글내용" autocomplete="off" 
		autofocus="autofocus" rows="8" cols="78"></textarea></td></tr>
		<tr><td><button>댓글작성</button>
			<button type="reset">취소</button></td></tr>
	</table>
</form>
<p><p>
<c:forEach var="rs" items="${replys}">
	<table border="1" id="replyListTbl">
		<tr><td align="center">${rs.r_owner}</td>
			<td align="right"><fmt:formatDate value="${rs.r_when}"
			pattern="yyyy/MM/dd a hh:mm:ss" />
			<c:if test="${sessionScope.loginMember.sm_id == rs.r_owner}">
				<button onclick="replyDelete('${rs.r_no}', '${rs.r_b_no}');">삭제</button>
			</c:if>
		</td></tr>
		<tr><td colspan="2">${rs.r_txt}</td></tr>
	</table><p>
</c:forEach>
</body>
</html>