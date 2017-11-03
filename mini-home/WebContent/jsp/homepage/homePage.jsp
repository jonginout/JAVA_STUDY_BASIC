<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/jsp/include/basicInclude.jsp"%>
<style type="text/css">
.dropdown-item:hover{
	cursor:pointer;	
	background-color: skyblue;
}
.jumbotron{
    border-radius: 10px;
    padding: 15px;
    margin: 10px;
}
.jumbotron:hover{
	background-color: #D8D8D8;
	font: 17px bold;
}
</style>
</head>
	<body background="${pageContext.request.contextPath}/common/down?filePath=${homePage.backgroundImg}&flag=home">

	<div class="row justify-content-center">

		<div class="header">
			<c:import url="/jsp/include/header.jsp" />
		</div>
		<div class="content row">
			<div class="container">
				<audio controls="controls" autoplay loop> 
					<source src="${pageContext.request.contextPath}/common/down?filePath=${homePage.bgm}&flag=home" 
					type="audio/${bgmExt}"/>
				</audio> 
				<span class="float-right">방문 수 : ${homePage.visits}</span>
				<center>
					<p class="h1">${homePage.title}</p>
						<c:choose>
							<c:when test="${empty homePage.profile}">
								<img src="default.jpg" alt="기본 이미지" class="img-thumbnail"/>
							</c:when>
							<c:otherwise>
								<img src="${pageContext.request.contextPath}/common/down?filePath=${homePage.profile}&flag=home" alt="profile" class="img-thumbnail"/>
							</c:otherwise>
						</c:choose>
					
					<div class="dropdown show">
						<a class="btn btn-danger dropdown-toggle" href="" role="button"
							id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">
							${homePage.id}님의 홈피</a>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<c:if test="${user!=null and homePage.id!=user.id }">
								<a class="dropdown-item" onclick="applyFriend('${homePage.id}')">친구걸기</a>
								<a class="dropdown-item" data-toggle="modal" data-target="#exampleModal" onclick="recIdChk('${homePage.id}')">쪽지 보내기</a>
							</c:if>
							<a class="dropdown-item" href="${pageContext.request.contextPath}/jsp/main/main?field=id&word=${homePage.id}">다른 홈피 보기</a>
						</div>
					</div>
					<div class="jumbotron jumbotron-fluid text-justify-center">
						${homePage.introduce}
					</div>
					<c:if test="${homePage.photoGallaryUseYn}">
						<a onclick="photoGall()" class="btn btn-primary" style="cursor:pointer;">포토 갤러리</a>
					</c:if>
					<c:if test="${homePage.guestBookUseYn}">
						<a onclick="diary()" class="btn btn-primary" style="cursor:pointer;" >방명록</a>
					</c:if>
					<c:if test="${homePage.diaryUseYn}">
						<a onclick="diary()" class="btn btn-primary" style="cursor:pointer;">다이어리</a>
					</c:if>
					<c:if test="${homePage.fileGallaryUseYn}">
						<a onclick="fileGall()" class="btn btn-primary" style="cursor:pointer;">파일 갤러리</a>
					</c:if>
					<c:if test="${homePage.id==user.id}">
						<a href="${pageContext.request.contextPath}/jsp/homepage/homepagesettingform?homepage_no=${homePage.homepageNo}"
							class="btn btn-warning">홈피 설정</a>
					</c:if>
				</center>
			</div>


		</div>

		<div class="footer">
			<c:import url="/jsp/include/footer.jsp" />
		</div>

	</div>


	<script type="text/javascript">
		function applyFriend(friendMemberId) {
			if(confirm(friendMemberId+'님에게 친구 신청하시겠습니까?')){
				location.href = "${pageContext.request.contextPath}/jsp/friend/friendapply?homepage_no=${homePage.homepageNo}&member_id=${user.id}&friend_member_id="
						+ friendMemberId;
			}
		}
		$(".nav.nav-pills.headMenu > a").removeClass("active"); //모든 li 클래스를 다 지우고
		$(".nav.nav-pills.headMenu > a:eq(1)").addClass("active"); // 추가
		
		
		/////링크
		function diary() {
			location.href = "${pageContext.request.contextPath}/jsp/category/categorylist?homepageNo=${homePage.homepageNo}&categoryGroupNo=3";	
		}
		function fileGall() {
			location.href = "${pageContext.request.contextPath}/jsp/filegallary/listfilegallarycategory?homepage_no=${homePage.homepageNo}&category_group_no=4";
		}
		function photoGall() {
			location.href = "${pageContext.request.contextPath}/jsp/photogallary/listphotogallarycategory?homepage_no=${homePage.homepageNo}&category_group_no=1";		
		}
		
	</script>
</body>
</html>



