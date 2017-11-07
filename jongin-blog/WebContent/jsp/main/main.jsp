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
			
			<h1 id="mainLogo">
				<a href="${pageContext.request.contextPath}/main/main.do">
					<i class="fa fa-home" aria-hidden="true"></i>
					Jongin Blog
				</a>
			</h1>
			
			<h3>
				<c:choose>
					<c:when test="${empty user}">
			 			<a href="${pageContext.request.contextPath}/login/loginform.do">로그인</a>
						<a href="${pageContext.request.contextPath}/signin/signinform.do">회원가입</a>					
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/login/logout.do">로그아웃</a>		
						<c:choose>
							<c:when test="${myBlog}">
								<button id="blogBtn" onclick="myBlog()">내 블로그</button>
							</c:when>
							<c:otherwise>
								<button id="blogBtn" onclick="makeBlog()">블로그 생성</button>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</h3>
			
			<form action="">
				<div id="searchBox">
					<select id="searchSelect" name="column">
						<option value="title">블로그 제목</option>
						<option value="memberNo">블로거</option>
						<option value="tag">태그</option>
					</select> 
					<input id="searchText" type="text" name="word"/>
					<button id="searchBtn">검색</button>
				</div>
			</form>	
			
		
		
				<table id="blogList" border="1" width="400">
					<tr>
						<td>번호</td>
						<td>블로그명</td>
						<td>블로거</td>
						<td>로고</td>
					</tr>
					<c:forEach var="blog" items="${blogList}">
						<tr>
							<td>${blog.blogNo}</td>
							<td>${blog.title}</td>
							<td>${blog.id}</td>
							<td>${blog.logoNo}</td>
						</tr>
					</c:forEach>
				</table>
			
		</div>
		
</body>
</html>

<script type="text/javascript">
	
	if("${param.column}") $("#searchSelect").val("${param.column}");
	if("${param.word}") $("#searchText").val("${param.word}");

	function makeBlog() {
		if (confirm('나만의 블로그를 생성하겠습니까?')) {			
			location.href=		
			"${pageContext.request.contextPath}/blog/bloginsert.do";
		}
	}
	
	function myBlog() {
		location.href=		
		"${pageContext.request.contextPath}/blog/blog.do";
	}

</script>