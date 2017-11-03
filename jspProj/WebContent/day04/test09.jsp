<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- 
	

		
	--%>

	<c:set var ="msg" value="테스트" />
	<c:set var ="msg" value="<h3>테스트</h3>" />
	${msg}
	<c:out value="${msg}" />
	<br />

	<c:out value="${msg2}" default="홈페이지 음슴"/>
	
	
</body>
</html>