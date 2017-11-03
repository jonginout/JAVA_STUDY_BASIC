<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="navi" tagdir="/WEB-INF/tags" %>
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
					<p class="h1">다이어리 목록</p>
				 		<table class="table table-hover">
				 			<tbody>
					 			<c:forEach var="list" items="${list}">
					 				<c:if test="${categoryGroupNo eq 3}">
							 			<c:choose>
								 			<c:when test="${list.categoryNo eq modifyNo}">
								 				<tr>
								 					<form action="${pageContext.request.contextPath}/jsp/category/categorymodifyprocess?categoryNo=${list.categoryNo}" method="post">
									 					<input type="hidden" name="homepageNo" value="${homepageNo}" />
												 		<input type="hidden" name="categoryGroupNo" value="${categoryGroupNo}" />
									 					<th>제목 : </th>
									 					<td><input type="text" value="${list.name}" name="name"></td>
									 					<td><button class="btn btn-default">등록</button></td>
									 					<td><a href="${pageContext.request.contextPath}/jsp/category/categorydelete?categoryNo=${list.categoryNo}&homepageNo=${list.homepageNo}&categoryGroupNo=${list.categoryGroupNo}">삭제</a></td>
								 					</form>
								 				</tr>
							 				</c:when>
							 				<c:otherwise>
							 					<tr>
							 						<th>제목 : </th>
								 					<td onclick="detail(${list.homepageNo}, ${list.categoryNo})" style="cursor:pointer;" width="70%">${list.name}</td>
								 					<td><a href="${pageContext.request.contextPath}/jsp/category/categorymodify?modifyNo=${list.categoryNo}&categoryNo=${list.categoryNo}&homepageNo=${list.homepageNo}&categoryGroupNo=${list.categoryGroupNo}">수정</a></td>
								 					<td><a href="${pageContext.request.contextPath}/jsp/category/categorydelete?categoryNo=${list.categoryNo}&homepageNo=${list.homepageNo}&categoryGroupNo=${list.categoryGroupNo}">삭제</a></td>
								 				</tr>
							 				</c:otherwise>
						 				</c:choose>
					 				</c:if>
					 			</c:forEach>
					 			
			 				</tbody>
				 		</table>
				 		<c:if test="${add eq 1}">
				 			<form action="${pageContext.request.contextPath}/jsp/category/categorywrite" method="post">
						 		<table>
							 		<input type="hidden" name="homepageNo" value="${homepageNo}" />
							 		<input type="hidden" name="categoryGroupNo" value="${categoryGroupNo}" />
						 			<tr>
						 				<td>다이어리 : </td>
						 				<td><input type="text" name="name" class="form-control"></td>
						 				<td><button class="btn btn-default">등록</button></td>
						 			</tr>
						 		</table>
					 		</form>
				 		</c:if>
				 	<a href="${pageContext.request.contextPath}/jsp/category/categorylist?homepageNo=${homepageNo}&categoryGroupNo=${categoryGroupNo}&add=1" class="btn btn-default"  >추가</a>
				 	<a href="${pageContext.request.contextPath}/jsp/homepage/homepage?homepage_no=${homepageNo}" class="btn btn-default"  >뒤로</a>
				 		<navi:categoryListPage data="${pageResult}" />
				</center>
			</div>
		</div>

		<div class="footer">
			<c:import url="/jsp/include/footer.jsp" />
		</div>
		
	</div>
	
	
	<script type="text/javascript">
		$("ul.nav.navbar-nav > li").removeClass(); //모든 li 클래스를 다 지우고
		$("ul.nav.navbar-nav > li:eq(2)").addClass("active"); // 추가
		
		function detail(homepageNo, categoryNo) {
			location.href = "${pageContext.request.contextPath}/jsp/diary/diarylist?"+
					"homepageNo=" + homepageNo +
					"&categoryNo=" + categoryNo
			};
	</script>
</body>
</html>