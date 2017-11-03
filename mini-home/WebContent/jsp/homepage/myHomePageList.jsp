<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/jsp/include/basicInclude.jsp"%>
<style type="text/css">
	#homePageLi:hover { 
		cursor:pointer;
    	background-color: skyblue;
	}
</style>
</head>
<body>

	<div class="row justify-content-center">

		<div class="header">
			<c:import url="/jsp/include/header.jsp" />
		</div>

		<div class="content row">
			<div class="container">
				<center>
					<p class="h1">${id}님의 홈페이지 리스트</p>
					<div class="form-group col-md-8 ">
						<button class="btn btn-success btn-lg btn-block" type="button"
							onclick="newHomePage()">
							 <i class="fa fa-plus" aria-hidden="true"></i> 
							 새 홈페이지 만들기
						</button>
					</div>
						<table class="table">
							<thead>
								<th>홈피 번호</th>
								<th>홈피 명</th>
							</thead>
							<c:forEach var="hp" items="${homePageList}" >
								<tr id="homePageLi" onclick="homepageDetail(${hp.homepageNo})">
									<td>${hp.homepageNo}</td>
									<td>${hp.title}</td>
								</tr>
							</c:forEach>
						</table>
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
		function newHomePage() {
			if (confirm('새 홈페이지를 생성합니다.')) {				
				location.href="${pageContext.request.contextPath}/jsp/homepage/homepageinsert?id=${id}";
			}
		}
		function homepageDetail(homepageNo){
			location.href = "${pageContext.request.contextPath}/jsp/homepage/homepage?homepage_no="+homepageNo;
		};
	</script>
</body>
</html>



