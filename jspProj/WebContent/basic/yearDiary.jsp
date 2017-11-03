<%@page import="java.util.HashMap"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	HashMap <String , String>map = new HashMap();

	map.put("05-05","어린이날");
	map.put("09-27","오늘날");
	map.put("10-04","추석");
	map.put("12-25","성탄절");
	map.put("05-03","석탄일");

	Calendar today = Calendar.getInstance();

	SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
	String now = sdf.format(today.getTime());
	
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

<table border="1">
	<tr>
	<% for(int m =0; m<12;m++) {
		today.set(today.get(Calendar.YEAR), m, 1);
	
	%>
	
		
		<td valign="top">
		<table border=1>
	<tr height="10">
		<td align="center" colspan="7">
			<img alt="" src="../img/diary/<%=m %>.jpeg" height=200>
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
			
			String bg = "#fff";
			
			String sp="&nbsp;";
			
			String toStr = sdf.format(today.getTime());
			if(map.containsKey(toStr))
			{
				sp = map.get(toStr);
				bg = "#0f0";
			}
			
			if(now.equals(toStr))
				bg = "#ff0";
			
			
			
			
		%>
		<td bgcolor="<%=bg %>" style="color: <%=cc[1][day]%>"><%=i %><br><%=sp %></td>
		<%
			
		
			if(day==7){
				out.print("</tr><tr  align='right'>");
			}
		
		} %>
	</tr>
	
</table>
		
		
		
		</td>
		
	
	<%
	
	if((m+1)%3==0){
			out.print("</tr><tr>");
		}
	
	} %>
	</tr>
</table>



</body>
</html>