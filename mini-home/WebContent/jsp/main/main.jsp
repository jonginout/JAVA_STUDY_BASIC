<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="navi" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/jsp/include/basicInclude.jsp" %>
</head>
<body>
	<div class="row justify-content-center">
	
		<div class="header"> 
			<c:import url="/jsp/include/header.jsp" />
		</div>
		
		<div class="content row">
			<div class="container">
				<div class="col-md-12">
					<center>
						<p class="h1">
							<a href="main">
								<i class="fa fa-home" aria-hidden="true"></i>
								 미니홈피 찾기
								<i class="fa fa-home" aria-hidden="true"></i>
							</a>
						</p>
						<form action="${pageContext.request.contextPath}/jsp/main/main" method="get">
							<input type="hidden" name="friendPageNo" value="${friendPageNo}"/>
							<div class="input-group col-md-6">
								<select name="field" id="field" class="custom-select" id="searchForm">
									<option value="title" checked>제목</option>
									<option value="id">ID</option>
								</select> 
								<input type="text" class="form-control" name="word" placeholder="검색어.." value="${param.word}"> 
								<span class="input-group-btn">
									<button class="btn btn-primary">검색</button>
								</span>
							</div>
						</form>
						<c:import url="/jsp/main/searchResult.jsp"></c:import>
						
						<navi:searchPage data="${pageResult}" url="main"/>
						
					</center>
				</div>
			</div>
			<div class="container" >
				<div class="row align-items-center text-center">
					<div class="col">
						<h3>내 친구 목록</h3>
						<c:import url="/jsp/friend/myFriendList.jsp"></c:import>
						<navi:friendPage data="${friendPageResult}" url="main"/>
						
					</div>
					<div class="col">
						<c:import url="/jsp/main/recentBoardList.jsp"></c:import>
					</div>
				</div>
			</div>
		</div>
		
		<div class="footer">
			<c:import url="/jsp/include/footer.jsp" />
		</div>
	</div>
	<script type="text/javascript">
		$(".nav.nav-pills.headMenu > a").removeClass("active"); //모든 li 클래스를 다 지우고
		$(".nav.nav-pills.headMenu > a:eq(0)").addClass("active"); // 추가
		var field = "${param.field}";
		if(field!=""){
			$("#field").val(field);
		}
		
		function goPage(url) {
			alert(url);
		}
	</script>
</body>
</html>