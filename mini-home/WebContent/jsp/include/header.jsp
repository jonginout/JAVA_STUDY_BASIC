<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
.nav-link{
	color: black;
}
</style>
<div class="navbar navbar-expand-md fixed-top"
	style="box-shadow: -22px -18px 15px 20px grey;">
	<ul class="nav nav-pills headMenu">
		<a class="nav-link" href="${pageContext.request.contextPath}/jsp/main/main"> 
			<i class="fa fa-home" aria-hidden="true"></i> IGS
		</a>
		<c:choose>
			<c:when test="${empty user}">
				<a class="nav-link" href="${pageContext.request.contextPath}/jsp/login/loginform">로그인</a>
				<a class="nav-link" href="${pageContext.request.contextPath}/jsp/signin/signinform">회원가입</a>
			</c:when>
			<c:otherwise>
				<a class="nav-link" href="${pageContext.request.contextPath}/jsp/homepage/myhomepagelist?id=${user.id}">내홈피</a>
			     <li class="nav-item dropdown">
			        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			          	<i class="fa fa-comments-o" aria-hidden="true"></i>
			          	쪽지
			        </a>
			        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="${pageContext.request.contextPath}/jsp/msg/msgbox?field=rec_id">쪽지함</a>
						<a class="dropdown-item" data-toggle="modal" data-target="#exampleModal" href="">
							쪽지 보내기
						</a>
			        </div>
			      </li>
				<a class="nav-link" href="${pageContext.request.contextPath}/jsp/login/logout">로그아웃</a>
			</c:otherwise>
		</c:choose>
	</ul>
</div>
<c:import url="/jsp/include/messageModal.jsp" />

