<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<ul>
		<li><a href="${pageContext.request.contextPath}/main/main.do">홈</a></li>
		<li><a href="${pageContext.request.contextPath}/board/list.do">자유게시판</a></li>
		<li><a href="${pageContext.request.contextPath}/board/list.do">갤러리</a></li>
		<li><a href="${pageContext.request.contextPath}/board/list.do">자료실</a></li>
	<c:choose>
		<c:when test="${empty user}">
			<li><a href="${pageContext.request.contextPath}/login/loginform.do">로그인</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${pageContext.request.contextPath}/login/logout.do">로그아웃</a></li>
		</c:otherwise>
	</c:choose>		
	</ul>	
</div>