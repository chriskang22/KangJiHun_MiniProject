<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="member.signup" method="post" enctype="multipart/form-data" 
	name="signUpForm" onsubmit="return signUpCheck();">
	<table border="1" id="memberSignTbl">	
		<tr><th>회원가입</th></tr>	
		<tr><td><input name="sm_id" placeholder="ID" autofocus="autofocus" 
			autocomplete="off" maxlength="10"></td></tr>
		<tr><td><input name="sm_pw" placeholder="PW" autocomplete="off" 
			maxlength="20" type="password"></td></tr>
		<tr><td><input name="sm_pwChk" placeholder="PW확인" autocomplete="off" 
			maxlength="20" type="password"></td></tr>
		<tr><td><input name="sm_name" placeholder="이름" autocomplete="off" 
			maxlength="10"></td></tr>
		<tr><td><input name="sm_photo" type="file" id="file"></td></tr>
		<tr><td><input id="addr1" name="sm_addr1" placeholder="우편번호" 
			readonly="readonly"></td></tr>
		<tr><td><input id="addr2" name="sm_addr2" placeholder="주소" 
			readonly="readonly"></td></tr>
		<tr><td><input name="sm_addr3" placeholder="상세주소" autocomplete="off"></td></tr>
		<tr><td><div class="role">권한&nbsp;&nbsp;&nbsp;
				<input name="sm_role" type="radio" value="c" checked="checked">일반&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="sm_role" type="radio" value="m">관리자</div></td></tr>
		<tr><td><button>회원등록</button></td></tr>
	</table>	
</form>
</body>
</html>