<%@page import="java.util.ArrayList"%>
<%@page import="model.StudVO"%>
<%@page import="model.StudDAO"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	
	StudVO vo = new StudDAO().detail(request.getParameter("id"));

	
	
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function del()
	{
		if(confirm('삭제하시겠습니까?')){
			location.href="delete.jsp?id=<%=vo.getId() %>";
		}
	}

</script>
</head>
<body>
<table border="1">
	<table border="1">
	<tr>
		<td>id</td>
		<td><%=vo.getId() %></td>
	</tr>
	<tr>
		<td>pw</td>
		<td><%=vo.getPw() %></td>
	</tr>
	<tr>
		<td>성별</td>
		<td><%=vo.getGender()%></td>
	</tr>
	<tr>
		<td>취미</td>
		<td><%=vo.getHobby() %></td>
	</tr>
	
	<tr>
		<td>이메일</td>
		<td><%=vo.getEmail() %></td>
	</tr>
	<tr>
		<td>내용</td>
		<td><%=vo.getContent().replaceAll("\r\n", "<br>") %></td>
	</tr>
	<tr>
		<td colspan="2" align="right">
			<a href="javascript:del()">삭제하기</a>
			<a href="modifyForm.jsp?id=<%=vo.getId() %>">수정하기</a>
			<a href="list.jsp">목록으로</a>
		</td>
	</tr>
</table>
	
	
	
</table>
</body>
</html>
