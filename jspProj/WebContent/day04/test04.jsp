ㄷ<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Map<String, String> data = new HashMap<>();
	data.put("id", "hong");
	data.put("name", "홍길동");
	data.put("email", "hong@hong.com");
	data.put("self-img", "나의...");
	
	pageContext.setAttribute("data", data);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	data : ${ data } <br />
	id : ${ data.id } <br />
	name : ${ data.name } <br />
	email : ${ data.email } <br />
	self-img : ${ data["self-img"] } <br />
</body>
</html>