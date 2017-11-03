<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	String url = "movie/"+request.getParameter("movie")
			+".jsp"
			+"?cnt="+request.getParameter("cnt");

	response.sendRedirect(url);

%>
