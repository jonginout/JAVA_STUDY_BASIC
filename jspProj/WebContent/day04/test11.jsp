<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 
	조건 처리하기
	type의 값에 따라서 아래와 같이 출력
	S : 수퍼 계정
	U : 일반 사용자 계정 
	S 또는 U 가 아닐 경우
-->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- 	<c:set var="type" value="S"/> --%>
	<c:set var="type" value="U"/>
<%-- 	<c:set var="type" value="F"/> --%>
	
	<%-- 
		다중조건	
	--%>
	<c:choose>
		<c:when test="${type=='S'}">
			<h3>관리자 계정</h3>
		</c:when>
		<c:when test="${type=='U'}">
			<h3>일반사용자 계정</h3>
		</c:when>
		<c:otherwise>
			<h3>잘못된 접근입니당</h3>
		</c:otherwise>
	</c:choose>
</body>
</html>