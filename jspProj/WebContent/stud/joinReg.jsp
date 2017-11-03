<%@page import="model.StudVO"%>
<%@page import="model.StudDAO"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	String hobby = Arrays.toString(request.getParameterValues("hobby"));
	String email = request.getParameter("email1")+"@"+request.getParameter("email2");
	StudDAO dao = new StudDAO();

	StudVO vo = new StudVO();
	
	vo.setId(request.getParameter("id"));
	vo.setPw(request.getParameter("pw"));
	vo.setGender(request.getParameter("gender"));
	vo.setHobby(hobby);
	vo.setContent(request.getParameter("content"));
	vo.setEmail(email);
	
	dao.insert(vo);
	
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
	alert('회원가입되었습니다.');
	location.href="detail.jsp?id=<%=vo.getId()%>";
</script>
</body>
</html>
