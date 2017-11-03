<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String menuUrl= (String)request.getAttribute("menuUrl");
	String mainUrl= (String)request.getAttribute("mainUrl");

%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
	<tr>
		<td colspan="2">
			<jsp:include page="inc/top.jsp"/>
		</td>
	</tr>
	<tr>
		<td width="100">
			<jsp:include page="<%=menuUrl %>"/>
			
		</td>
		<td width="500">
			<jsp:include page="<%=mainUrl %>"/>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<jsp:include page="inc/bottom.jsp"/>
		</td>
	</tr>
	
</table>
</body>
</html>