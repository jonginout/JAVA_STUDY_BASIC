<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("data", new String[]{"일", "이", "삼"});

	List<String> data2 = new ArrayList<>();
	data2.add("일");
	data2.add("이");
	data2.add("삼");
	pageContext.setAttribute("data2", data2);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	0 : ${ data[0] } <br>
	1 : ${ data[1] } <br>
	2 : ${ data[2] } <br>

	<hr />

	0 : ${ data2[0] } <br>
	1 : ${ data2[1] } <br>
	2 : ${ data2[2] } <br>
</body>
</html>