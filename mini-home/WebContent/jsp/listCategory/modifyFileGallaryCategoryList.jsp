<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
					<p class="h1">카테고리 편집</p>
					
					<form method="post" action="${pageContext.request.contextPath}/jsp/fileGallary/writefilegallarycategory
					?homepage_no=${homepage_no}
					&category_group_no=${category_group_no}">
					
					<table class="table">
					<tbody>
						<c:forEach var="s" items="${list}">
						<tr>
							<c:choose>
								<c:when test="${category_no != s.categoryNo}">
									<td colspan="2">${s.name}</td>
								</c:when>
								<c:when test="${category_no == s.categoryNo}">
									<td><input type="text" name="modifyCategory" value="${s.name}"></td>
								</c:when>		
							</c:choose>

							<c:choose>
								<c:when test="${category_no != s.categoryNo}">
									<td><a href="${pageContext.request.contextPath}
									/jsp/filegallary/modifyformfilegallarycategorylist
									?homepage_no=${homepage_no}
									&category_group_no=${category_group_no}
									&category_no=${s.categoryNo}">수정</a></td>
								</c:when>
								<c:when test="${category_no == s.categoryNo}">
										<td>
											<input type="hidden" name="category_no" value="${s.categoryNo}">
											<button>등록</button>
										</td>
								</c:when>	
							</c:choose>
								<td><a href="${pageContext.request.contextPath}
								/jsp/filegallary/deletefilegallarycategory
								?homepage_no=${homepage_no}
								&category_no=${s.categoryNo}
								&category_group_no=${category_group_no}">삭제</a></td>
						</tr>
						</c:forEach>
						
						<tr>
							<th>카테고리 추가</th>
							<td><input type="text" name="name"></td>
							<td><button>등록</button></td>
						</tr>
						
						<tr>
							
					</tbody>	
					</table>
				</form>
				<a href="${pageContext.request.contextPath}/jsp/filegallary/listfilegallarycategory?homepage_no=${homepage_no}&category_group_no=${category_group_no}">카테고리 목록으로</a>
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
	</script>
</body>
</html>