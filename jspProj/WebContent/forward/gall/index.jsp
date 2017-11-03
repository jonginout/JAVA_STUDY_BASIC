<%@page import="java.util.ArrayList"%>
<%@page import="forwardSite.Inc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	new Inc(request);


	ArrayList<String>gallList = new ArrayList();
	
	gallList.add("son.jpg");
	gallList.add("son2.jpg");
	gallList.add("diary/0.jpeg");
	gallList.add("diary/1.jpeg");
	
	request.setAttribute("gallList", gallList);

%>    
<jsp:forward page="../view/template.jsp"/>