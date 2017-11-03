<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="navi" tagdir="/WEB-INF/tags" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/jsp/include/basicInclude.jsp"%>
</head>
<body background="${pageContext.request.contextPath}/common/down?filePath=${homePage.backgroundImg}&flag=home">
	<div class="row justify-content-center">

		<div class="header">
			<c:import url="/jsp/include/header.jsp" />
		</div>

		<div class="content row">
			<div class="container">
				<center>
					<p class="h1">자료실 목록</p>
					<table class="table table-hover">
	
					<tbody>
					
					<c:forEach var = "s" items="${list}" varStatus="loop">
						<tr>
							<td style="cursor: pointer;"
							onclick="detail(${s.homepageNo}, ${s.categoryGroupNo}, ${s.categoryNo})">
							${s.name}</td>
						</tr>
					</c:forEach>
					
					<c:if test="${empty list}">
						<tr>
							<td colspan='4'>입력된 게시물이 없습니다.</td>
						</tr>
					</c:if>
					</tbody>
					</table>						
				<div align="right">
				<a href="${pageContext.request.contextPath}
				/jsp/filegallary/modifyformfilegallarycategorylist
				?homepage_no=${homepage_no}
				&category_group_no=${category_group_no}">카테고리 편집</a>
				</div>
				<div align="right">
				<a href="${pageContext.request.contextPath}/jsp/homepage/homepage?homepage_no=${homepage_no}" class="btn btn-default"  >뒤로</a>
				</div>
				
				</center>
								
			</div>
		</div>
		
		
		<div class="footer">
			<c:import url="/jsp/include/footer.jsp" />
		</div>
		
	</div>
	
	<script type="text/javascript">
	function detail(homepageNo, categoryGrpNo, 
			categoryNo) {
	var url = "${pageContext.request.contextPath}/jsp/filegallary/listfilegallary?homepage_no=" 
			+ homepageNo + "&category_group_no="
			+ categoryGrpNo + "&category_no="
			+ categoryNo + "&no=";

	location.href=url;
	}
	</script>
</body>
</html>