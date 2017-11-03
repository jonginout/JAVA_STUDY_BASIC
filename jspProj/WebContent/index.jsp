<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	/// 김수한무 거북이와 두루미 삼천갑자 동박사 워리워리
	Date now = new Date();
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jspProj</title>
</head>
<body>
나는 선생이여
<%=now %>
<a href="NowSer">NowSer</a>
<br>basic<br>
<a href="basic/first.jsp">first</a>
<a href="basic/monthDiary.jsp">monthDiary</a>
<a href="basic/yearDiary.jsp">yearDiary</a>

<br>request<br>
<a href="request/info.jsp?aaa=<%=URLEncoder.encode("배고파", "utf-8")%>">info</a>
<a href="request/join.jsp?id=qqq&pw=1111&email=qqqq@qqq.com&name=<%=URLEncoder.encode("현구안뇽", "utf-8")%>&hobby=sleep&hobby=song&hobby=fishing">join</a>
<a href="request/joinForm.jsp">joinForm</a>

<br>response<br>
<a href="response/a.jsp">a</a>
<a href="response/movieForm.html">movieForm</a>
<a href="stud/list.jsp">stud/</a>

<br>include<br>
<a href="include/aaa.jsp?name=abcd">aaa</a>
<a href="include/template.jsp">template</a>
<a href="include/tot.jsp?name=abcd">tot</a>

<br>forward<br>
<a href="forward/aaa.jsp?name=abcd">aaa</a>
<a href="forward/gall/">사이트</a>
</body>
</html>
