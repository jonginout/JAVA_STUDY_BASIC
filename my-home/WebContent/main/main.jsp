<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="container">
	
		<div class="header">
			<c:import url="/include/topMenu.jsp" />
		</div>
		
		<div class="content inner cover jumbotron">
			<c:choose>
				<c:when test="${empty sessionScope.user.name}">
					<h1 class="cover-heading">손님 안녕하세요!</h1>
		            <p class="lead">※로그인 해주세요※</p>
					<p class="lead">
						<a href="${pageContext.request.contextPath}/signin/signinform" class="btn btn-lg btn-primary">SignIn</a>
						<a href="${pageContext.request.contextPath}/login/loginform" class="btn btn-lg btn-secondary">Login</a>
					</p>
				</c:when>
				<c:otherwise>
            		<h1 class="cover-heading">'${sessionScope.user.name}'님 안녕하세요!</h1>
					<p class="lead">(${sessionScope.user.id})님이 접속하였습니다.</p>
					<p class="lead">
						<a href="${pageContext.request.contextPath}/board/list" class="btn btn-lg btn-secondary">Go Board!</a>
					</p>
				</c:otherwise>
			</c:choose>
          </div>
		
		<div class="footer">
			<c:import url="/include/footer.jsp" />
		</div>
	</div>
	<script type="text/javascript">
	$(".flex-sm-fill.text-sm-center.nav-link.active").removeClass("active");
	$(".flex-sm-fill.text-sm-center.nav-link:eq(0)").addClass("active");
</script>
</body>
</html>