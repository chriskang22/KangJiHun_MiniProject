<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" id="memberInfoTbl">
	<form action="member.update" method="post" enctype="multipart/form-data" 
		name="memberUpdateForm" onsubmit="return memberUpdateCheck();">
		<tr><th>회원정보</th></tr>
		<tr><td>아이디 : <input name="sm_id" readonly="readonly" 
			value="${sessionScope.loginMember.sm_id}"></td></tr>
		<tr><td>암호 : <input name="sm_pw" type="password" 
			value="${sessionScope.loginMember.sm_pw}" placeholder="암호" 
			autocomplete="off"></td></tr>
		<tr><td>이름 : <input name="sm_name" 
			value="${sessionScope.loginMember.sm_name}" placeholder="이름" 
			autocomplete="off"></td></tr>
		<tr><td><img style="width: 140px; height: 100%;" 
			src="resources/img/${sessionScope.loginMember.sm_photo}">
			<input name="sm_photo" type="file" id="file"></td></tr>
		<tr><td>우편번호 : <input id="info_addr1" name="sm_addr1" 
			value="${addr[2]}" readonly="readonly"></td></tr>
		<tr><td>주소 : <input id="info_addr2" name="sm_addr2" 
			value="${addr[0]}" readonly="readonly"></td></tr>
		<tr><td>상세주소 : <input name="sm_addr3" 
			value="${addr[1]}" placeholder="상세주소"></td></tr>
		<tr><td><div class="role">권한&nbsp;&nbsp;&nbsp;
			<c:choose>
				<c:when test="${sessionScope.loginMember.sm_role == 'c'}">
					<input name="sm_role" type="radio" value="c" checked="checked">일반&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="sm_role" type="radio" value="m">관리자
				</c:when>
				<c:otherwise>
					<input name="sm_role" type="radio" value="c">일반&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="sm_role" type="radio" value="m" checked="checked">관리자
				</c:otherwise>
			</c:choose>
		</div></td></tr>	
		<tr><td><button>수정</button>
	</form><button onclick="memberDelete();">탈퇴</button></td></tr>	
</table>
</body>
</html>