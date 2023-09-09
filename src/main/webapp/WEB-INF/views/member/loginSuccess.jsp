<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table id="memberLoginSuccessTbl">
	<tr><td>
		<img style="width: 100px; height: 100px; border-radius: 50%;" 
			src="resources/img/${sessionScope.loginMember.sm_photo}">
		ID : ${sessionScope.loginMember.sm_id}<p>	
		(${sessionScope.loginMember.sm_name})님 환영합니다!<p>
		<button onclick="memberInfoGo();">회원정보</button>
		<button onclick="logout();">로그아웃</button>
	</td></tr>	
</table>
</body>
</html>