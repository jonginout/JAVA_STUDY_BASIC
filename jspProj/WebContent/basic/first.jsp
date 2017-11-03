<%@page import="org.apache.jasper.tagplugins.jstl.core.Out"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!

	int a =10;

	void meth(){
		System.out.println("난 콘솔에 찍힌다.");
		
	}
	
	/* if(true){
		
	} */
	//meth();

%>
<%
	meth();
	out.print("난 브라우져에 찍힌다");
	
	/* void meth_1(){
		
	} */
	
	for(int i =0;i<10;i++)
	{
		out.println(i);
	}
	
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
a:<%=a %>

<table border="1">
	<tr>
		
	
<%
/* for(int i =0;i<10;i++){
	out.print("<td>"+i+"</td>");
} */

for(int i =0;i<10;i++){ %>

<td><%=i %></td>

<% } %>
</tr>
</table>
</body>
</html>