<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// el 에서 내장객체에 접근하는 순서
	
	// pageContext -> request > session -> application
	pageContext.setAttribute("msg", "페이지 컨텍스트");
	request.setAttribute("msg", "리퀘스트");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	msg : ${msg} <br />
	msg : ${requestScope.msg} <br />
</body>
</html>