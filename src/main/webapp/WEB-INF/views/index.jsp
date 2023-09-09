<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지하철 정보웹</title>
<link rel="stylesheet" href="resources/css/index.css">
<link rel="stylesheet" href="resources/css/member.css">
<link rel="stylesheet" href="resources/css/subway.css">
<link rel="stylesheet" href="resources/css/board.css">
<script type="text/javascript" src="resources/js/go.js"></script>
<script type="text/javascript" src="resources/js/JQuery.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="resources/js/KangValidChecker.js"></script>
<script type="text/javascript" src="resources/js/KangCheck.js"></script>
<script type="text/javascript" src="resources/js/Kang_JQuery.js"></script>
</head>
<body>
<table>
	<tr><td><a href="index.go"><img id="logo" style="width: 200px; height: 150px;" 
			src="resources/img/logo.png"></a></td></tr>	
</table>
<table id="siteLoginTbl">
	<tr><td><jsp:include page="${loginPage}" /></td><td><div id="r">${r}</div></td></tr>
</table>
<table id="siteMenuTbl">
	<tr><td><a href="board.go">Board</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td><a href="subway.go">Subway</a></td></tr>
</table>
<table id="siteContentTbl">
	<tr><td><jsp:include page="${contextPage}" /></td></tr>
</table>	
</body>
</html>