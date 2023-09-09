<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="board.write" method="post" 
name="boardWriteForm" onsubmit="return boardWriteCheck();">	
	<input name="token" value="${token}" type="hidden">
	<table border="1" id="regBoardTbl">
		<tr><th>게시글 작성</th></tr>
		<tr><td>작성자ID : <input name="b_owner" placeholder="작성자ID" 
		autocomplete="off" maxlength="10" value="${sessionScope.loginMember.sm_id}" 
		readonly="readonly"></td></tr>
		<tr><td>제목 : <input name="b_title" placeholder="제목" 
		autocomplete="off" autofocus="autofocus"></td></tr>
		<tr><td> 글내용 : <textarea name="b_txt" placeholder="글내용" 
		autocomplete="off" rows="4" cols="80"></textarea></td></tr>
		<tr><td><button>등록</button>
			<button type="reset">취소</button>
		</td></tr>
	</table>
</form>
</body>
</html>