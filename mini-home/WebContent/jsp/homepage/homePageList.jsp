<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/jsp/include/basicInclude.jsp"%>
</head>
<body>

	<div class="row justify-content-center">

		<div class="header">
			<c:import url="/jsp/include/header.jsp" />
		</div>

		<div class="content row">
			<div class="container">
				<center>
					<p class="h1">홈페이지 리스트</p>
					<c:forEach var="hp" items="${homePageList}" >
						<a href="${pageContext.request.contextPath}/jsp/homepage/homepage?homepage_no=${hp.homepageNo}">
							<h3>${hp.title}</h3>
						</a>
					</c:forEach>
				</center>
			</div>
		</div>

		<div class="footer">
			<c:import url="/jsp/include/footer.jsp" />
		</div>
		
	</div>
	
	
	<script type="text/javascript">
	$(".nav.nav-pills.headMenu > a").removeClass("active"); //모든 li 클래스를 다 지우고
	$(".nav.nav-pills.headMenu > a:eq(1)").addClass("active"); // 추가
	</script>
</body>
</html>



