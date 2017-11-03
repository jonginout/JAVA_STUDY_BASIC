<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 결과만 포함되는 방식이기 때문에 여기서도 태그립 -->
<!-- 헤더 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
<div class="navbar navbar-expand-md navbar-light fixed-top" style="background-color: #e3f2fd;">

	<nav class="nav nav-pills flex-column flex-sm-row">
	  <a class="flex-sm-fill text-sm-center nav-link " 
	  href="${pageContext.request.contextPath}/main/main">홈 화면</a>
	  <a class="flex-sm-fill text-sm-center nav-link " 
	  href="${pageContext.request.contextPath}/board/list">자유게시판</a>
	  <a class="flex-sm-fill text-sm-center nav-link " 
	  href="${pageContext.request.contextPath}/board/list">갤러리</a>
	  <a class="flex-sm-fill text-sm-center nav-link " 
	  href="${pageContext.request.contextPath}/board/list">자료실</a>
	 
	  <c:choose>
		<c:when test="${empty user}">
			<a class="flex-sm-fill text-sm-center nav-link " 
	  		href="${pageContext.request.contextPath}/login/loginform">로그인</a>
			<a class="flex-sm-fill text-sm-center nav-link " 
	  		href="${pageContext.request.contextPath}/signin/signinform">회원가입</a>
		</c:when>
		<c:otherwise>
		  	<a class="flex-sm-fill text-sm-center nav-link " 
		 	 href="${pageContext.request.contextPath}/login/loginout">로그아웃</a>
		</c:otherwise>
	  </c:choose>
	
	</nav>

</div>
<br /><br /><br /><br /><br />

