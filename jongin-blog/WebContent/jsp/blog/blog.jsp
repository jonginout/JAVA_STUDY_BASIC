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
					<button>로그인</button>
					<button>로그아웃</button>
					<button>블로그 관리</button>
				</div>
			</div>
	
		</div>
		
		
<script type="text/javascript">

</script>	
</body>
</html>

