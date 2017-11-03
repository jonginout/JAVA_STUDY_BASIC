<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>quiz02.jsp</title>
</head>
<body>
	<h1>학생정보</h1>
	
	<form action="/jspProj/quiz/quiz02" method="post">
	// quiz02
	// /jspProj/quiz/quiz02
		<p>
			<input type="text" name="id">
		</p>
		<p>
			<input type="password" name="pw">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
</body>
</html>