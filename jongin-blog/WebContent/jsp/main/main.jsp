<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/jsp/include/basicInclude.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main/main.css">
</head>
<body>
		<div id="content">
			
			<div id="mainLogo">
				<a href="${pageContext.request.contextPath}/main/main.do">
					<img width="200" src="http://postfiles4.naver.net/MjAxNzExMDVfMjUx/MDAxNTA5ODA5NDMzNzk3.frdOfv32zFGzwyEfnT0yFUK3qfyTcCuNiadOhg_hW40g.8gk74wuaTAb5kCdZ3XRMLIZblkv2MCvMkeXl2URcHtAg.PNG.jonginout/main.png?type=w2" alt="메인로고" />
				</a>
			</div>
			
			<h3>
				<c:choose>
					<c:when test="${empty user}">
						<a href="${pageContext.request.contextPath}/login/loginform.do">로그인</a>
						<a href="${pageContext.request.contextPath}/signin/signinform.do">회원가입</a>					
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/login/logout.do">로그아웃</a>
						<c:if test="홈페이지 없으면">
							<a href="">내 블로그 생성하기</a>
						</c:if>				
						<c:if test="홈페이지 있으면">
							<a href="">내 블로그</a>
						</c:if>				
					</c:otherwise>
				</c:choose>
			</h3>
			
			<form action="">
				<div id="searchBox">
					<select id="searchSelect" name="column">
						<option value="">블로그 제목</option>
						<option value="">블로거</option>
						<option value="">태그</option>
					</select> 
					<input id="searchText" type="text" name="word"/>
					<button id="searchBtn">검색</button>
				</div>
			</form>	
			
		</div>
</body>
</html>