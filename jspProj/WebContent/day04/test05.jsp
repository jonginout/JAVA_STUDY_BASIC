<%@page import="board.BoardDomain"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardDomain board = new BoardDomain();
	board.setNo(100);
	board.setTitle("제목입니당!");
	
	pageContext.setAttribute("board", board);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	board : ${ board } <br />
	no : ${ board.no } <br />
	title : ${ board.title } <br />
	content : ${ board.content } <br />
</body>
</html>