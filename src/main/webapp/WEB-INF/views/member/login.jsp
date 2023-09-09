<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table id="memberLoginTbl">
	<form action="member.login" method="post" name="loginForm" 
		onsubmit="return loginCheck();">	
		<tr><td><input name="sm_id" placeholder="ID" autofocus="autofocus" 
				autocomplete="off"></td></tr>
		<tr><td><input name="sm_pw" placeholder="PW" type="password" 
				autocomplete="off"></td></tr>
		<tr><td><button>로그인</button>
	</form>
				<button onclick="signupGo();">회원가입</button></td></tr>	
</table>
</body>
</html>