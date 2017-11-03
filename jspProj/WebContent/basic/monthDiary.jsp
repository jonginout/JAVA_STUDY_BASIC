<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	Calendar today = Calendar.getInstance();
String [][] cc = {
		{"#f00","#fff","#fff","#fff","#fff","#fff","#00f"},
		{"","#f00","000","000","000","000","000","#00f"},
		{"#fff","000","000","000","000","000"}};
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border=1>
	<tr height="10">
		<td align="center" colspan="7">
			<img alt="" src="../img/son.jpg" width=100>
		</td>
	</tr>
	<tr height="10">
		<td align="center" colspan="7" style="font-size: 20px">
			<%=today.get(Calendar.YEAR) %> 년 <%=today.get(Calendar.MONTH)+1 %> 월
		</td>
	</tr>
	<tr height="10">
	
		<%
			String sss = "일월화수목금토";
			for(int i =0; i<sss.length();i++ ){
		
		%>
		<td align="center" bgcolor="<%=cc[0][i] %>" 
		style="color: <%=cc[2][i%6]%>"><%=sss.charAt(i) %></td>
		<%} %>
	</tr>
	<tr height="10" align="right">
		<%
		
		today.set(Calendar.DATE,1);
		
		int cnt = today.get(Calendar.DAY_OF_WEEK)-1;
		
		if(cnt>0){
			%>
			<td colspan="<%=cnt%>"></td>
		
		<%}for(int i =1; i<=today.getActualMaximum(Calendar.DATE);i++) {
			today.set(Calendar.DATE,i);
			int day=today.get(Calendar.DAY_OF_WEEK);
		%>
		<td style="color: <%=cc[1][day]%>"><%=i %></td>
		<%
			
		
			if(day==7){
				out.print("</tr><tr  align='right'>");
			}
		
		} %>
	</tr>
	
</table>
</body>
</html>