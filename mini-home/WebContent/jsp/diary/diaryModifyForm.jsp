<%@page import="diary.DiaryDomain"%>
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
					<p class="h1">다이어리 수정</p>
					<form action="${pageContext.request.contextPath }/jsp/diary/diarymodifyprocess?no=${diary.no}" method="post">
						<input type="hidden" value="3" name="categoryNo">
						<input type="hidden" value="1" name="homepageNo">
						<table class="table">
							<tr>
								<th>제목: </th>
								<td><input type="text" name="title" value="${diary.title}" class="form-control"></td>
							</tr>
							<tr>
								<th>글쓴이: </th>
								<td><input type="text" name="writer" value="${diary.writer}" readonly class="form-control"></td>
							</tr>
							<tr>
								<th>내용: </th>
								<td><textarea rows="6" cols="80" name="content" class="form-control" rows="3">${diary.content}</textarea></td>
							</tr>
							<tr>
								<td align="left"><button class="btn btn-default">등록</button></td>
								<td align="right"><a href="${pageContext.request.contextPath }/jsp/diary/diarydetail?no=${diary.no}" class="btn btn-default btn-lg active" role="button">뒤로</a></td>
							</tr>
						</table>
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