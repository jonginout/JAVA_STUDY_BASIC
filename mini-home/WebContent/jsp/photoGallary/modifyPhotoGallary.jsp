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
					<p class="h1">사진첩 등록</p>
					
					<form method="post" action="${pageContext.request.contextPath}
					/jsp/photogallary/modifyphotogallary
					?homepage_no=${homepage_no}
					&category_group_no=${category_group_no}
					&category_no=${category_no}
					&no=${board.no}
					&member_no=${member_no}"
						enctype="multipart/form-data">
						<input type="hidden" name="no" value ="${board.no}" />
						<table class="table">
						<tbody>
							<tr>
								<th>번호</th>
								<td>${board.no}</td>
							</tr>
							<tr>
								<th>제목</th>
								<td><input type="text" name = "title" value="${board.title}"></td>
								<td><input type="hidden" name = "writer" value="${board.writer}"></td>
							</tr>

							<tr>
								<th>첨부된 파일</th>
								<c:choose>
									<c:when test="${fileListSize == 0}">
											<td>
												<input type="file" name="attachFile1">
												<input type="file" name="attachFile2">
											</td>
									</c:when>
									<c:when test="${fileListSize == 1}">
										<c:forEach var = "s" items="${fileList}" varStatus="loop">
												<td>
													<a href="${pageContext.request.contextPath}
													/jsp/photogallary/deletefile
													?homepage_no=${homepage_no}
													&category_group_no=${category_group_no}
													&category_no=${category_no}
													&no=${board.no}&fileNo=${s.fileNo}">${s.oriName}</a>
													<input type="file" name="attachFile2">
												</td>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<c:forEach var = "s" items="${fileList}" varStatus="loop">
												<td>
													<a href="${pageContext.request.contextPath}
													/jsp/photogallary/deletefile
													?homepage_no=${homepage_no}
													&category_group_no=${category_group_no}
													&category_no=${category_no}
													&no=${board.no}&fileNo=${s.fileNo}">${s.oriName}</a>
												</td>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</tr>
							<tr><td colspan="2">(파일 클릭시 삭제됩니다.)</td></tr>
							<tr>
								<th>내용</th>
								<td><textarea class="form-control" rows="6" cols="80" name = "content">${board.content}</textarea></td>
							</tr>
							<tr>
								<td colspan="2"><button class="btn btn-default">수정</button></td>
							</tr>
						</tbody>	
						</table>
					
					<a href="${pageContext.request.contextPath}/jsp/photogallary/listphotogallary
					?homepage_no=${homepage_no}
					&category_group_no=${category_group_no}
					&category_no=${category_no}">목록</a>
					<a href="${pageContext.request.contextPath}/jsp/photogallary/deletephotogallary
					?homepage_no=${homepage_no}
					&category_group_no=${category_group_no}
					&category_no=${category_no}
					&no=${board.no}">삭제</a>
					</form>
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