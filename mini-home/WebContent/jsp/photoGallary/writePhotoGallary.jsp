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
					<p class="h1">사진첩 글</p>
		<form method="post"
		
			action="${pageContext.request.contextPath}
			/jsp/photogallary/writephotogallary
			?homepage_no=${homepage_no}
			&category_group_no=${category_group_no}
			&category_no=${category_no}"
			
   			enctype="multipart/form-data">
		<table class="table">
			<tbody>
				<tr>
					<td>
						<input type="hidden" name="writer" value="${user.getId()}"> 
						<!-- readonly="readonly" -->
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>
						<input class="form-control" type="text" name="title">
					</td>
				</tr>
				<tr>
					<th>파일첨부</th>
					<td>
						<input type="file" name="attachFile1">
						<input type="file" name="attachFile2">
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea class="form-control" rows="6" cols="80" name="content"
						>내용을 입력하세요</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<button class="btn btn-default">등록</button>
					</td>
				</tr>
			</tbody>
		</table>	
	</form>
				</center>
			</div>
		</div>

		<div class="footer">
			<c:import url="/jsp/include/footer.jsp" />
		</div>
		
	</div>
	
</body>
</html>