<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">
	#submit{
		width: 577px;
		margin-bottom: 20px;
	}
</style>
<body>
	<div class="container">

		<div class="header">
			<c:import url="/include/topMenu.jsp" />
		</div>

		<div class="content">


			<h1>글 작성</h1>

			<form method="post"
				action="${pageContext.request.contextPath}/board/write"
				enctype="multipart/form-data">

				<div>
					제목 : <input type="text" name="title"> 
					작성자 : <input type="text" name="writer" value="${sessionScope.user.id}" readonly="readonly">
				</div>
				<br />
				<input type="file" name="attachFile1"/>				
				<input type="file" name="attachFile2"/>
				<br /><br />
				<div>
					<textarea rows="6" cols="80" name="content"></textarea>
				</div>
				<div>
					<button id="submit">등록</button>
				</div>
			</form>

			<a href="${pageContext.request.contextPath}/board/list">글 목록</a>
		</div>


		<div class="footer">
			<c:import url="/include/footer.jsp" />
		</div>

	</div>
</body>
</html>











