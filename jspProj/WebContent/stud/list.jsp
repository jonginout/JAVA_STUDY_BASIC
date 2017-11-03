<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.StudVO"%>
<%@page import="model.StudDAO"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	StudDAO dao = new StudDAO();
	
	
	String ban = null;
	
	if(request.getParameter("ban")!=null)
		ban = request.getParameter("ban");
	
	
	ArrayList<String> banList = dao.banlist();
	ArrayList<StudVO> list = dao.list(ban);

	
	
%>    
<!DOCTYPE html PUBLIC "-//W4C//DTD HTML 4.01 Transitional//EN" "http://www.w4.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border="1">
	<tr>
		<td colspan="4" align="right">
			<a href="?">전체</a>
			<%for(String bb: banList){%>
				<a href="?ban=<%=URLEncoder.encode(bb,"utf-8") %>"><%=bb%></a>
			<%} %>
		</td>
	</tr>
	<tr>
		<td>반</td><td>id</td><td>성별</td><td>이메일</td>
		
	</tr>
	<% for(StudVO vo: list) {%>
	<tr>
		<td><%=vo.getBan() %></td>
		<td><a href="detail.jsp?id=<%=vo.getId() %>"><%=vo.getId() %></a></td>
		<td><%=vo.getGender() %></td>
		<td><%=vo.getEmail() %></td>
		
	</tr>
	<%} %>
	
	<tr>
		<td colspan="4" align="right">
			<a href="joinForm.jsp">회원가입</a>
		</td>
	</tr>
</table>
</body>
</html>
