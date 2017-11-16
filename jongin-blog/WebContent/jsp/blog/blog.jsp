<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/jsp/include/basicInclude.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/blog/blog.css">
</head>
<body>
		<div id="content">
			
			<div id="blogtitle">
				<a id="mainBtn" href="${pageContext.request.contextPath}/main/main.do">메인으로..</a>
				<h1>
					<a href="${pageContext.request.contextPath}/blog/blog.do?blogNo=${blog.blogNo}">
						<i class="fa fa-smile-o" aria-hidden="true"></i>
						${blog.title}
					</a>
				</h1>
			</div>
			
			<div id="blogContent">
				<div id="main">
					fdg
				</div>
				<div id="sidebar">
					<div id="loginBtn">
						<c:choose>
							<c:when test="${empty user}">
								<a href="${pageContext.request.contextPath}/login/loginform.do">로그인</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath}/login/logout.do">로그아웃</a>
								<c:if test="${blog.memberNo == user.memberNo}">
									<a href="javascript:blogSet();">블로그관리</a>
								</c:if>
							</c:otherwise>
						</c:choose>
					</div>
					<div id="logoBox">
						<c:choose>
							<c:when test="${empty user}">
								<img src="https://s3.ap-northeast-2.amazonaws.com/dankookie/profiles/default.jpg"/>
							</c:when>
							<c:otherwise>
								<img src="https://s3.ap-northeast-2.amazonaws.com/dankookie/profiles/default.jpg"/>
							</c:otherwise>
						</c:choose>
					</div>
					<div id="categoryBox">
						<strong>카테고리</strong>
						<ul>
							<li><a href="">카테고리</a></li>
							<li><a href="">카테고리</a></li>
							<li><a href="">카테고리</a></li>
						</ul>
					</div>
					<div id="mainLogoBox">
						<h2>
							<a href="${pageContext.request.contextPath}/main/main.do"> 
								<i class="fa fa-home" aria-hidden="true"></i>
								Jongin Blog
							</a>
						</h2>
					</div>
				</div>
			</div>
	
		</div>
		
		
<script type="text/javascript">
	function blogSet() {
		location.href = "/jongin-blog/jsp/blog/blogsetform.html?blogNo=${blog.blogNo}";
	}
</script>	
</body>
</html>

