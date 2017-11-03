<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
</head>

<body>


	<h2>${ error }</h2>

	<form action="${pageContext.request.contextPath}/day05/login/loginprocess" method="post">
	
		id : <input type="text" name="id" value="${cid}"/>
		<br />
		pw : <input type="text" name="pass"/>
		<br />
		
		<button>로그인</button>
		<br />

		<label for="idcheck">아이디 저장</label>		
		<input type="checkbox" name="idcheck" id="idcheck"
			       <c:if test="${not empty cid}">checked</c:if> value="Y" />
	</form>
</body>
</html>