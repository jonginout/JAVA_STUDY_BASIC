<%@page import="board.BoardDomain"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardDomain board = new BoardDomain();
	board.setTitle("성꽁!");
	List<BoardDomain> data = new ArrayList<>();
	data.add(board);
	pageContext.setAttribute("data", data);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 실행시 화명에 성공을 출력 -->
	title : ${ data[0].title } <br>
</body>
</html>