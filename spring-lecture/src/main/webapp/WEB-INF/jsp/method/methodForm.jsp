<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>메소드 폼</h1>
	<body>
 		<form action="${pageContext.request.contextPath}/method/method.do" method="POST">
 			<input type="submit" value="호출" />
 		</form>
	</body>
</body>
</html>