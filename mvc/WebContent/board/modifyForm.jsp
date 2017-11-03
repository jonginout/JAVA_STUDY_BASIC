<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<div class="header">
		<c:import url="/include/topMenu.jsp" />
	</div>
	<div class="content">
	<form action="${pageContext.request.contextPath}/board/modify.do">
		<input type="hidden" name="no" value="${board.no}" />
		<table>
		<tbody>
			<tr>
				<th>번호</th>
				<td>${board.no}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="${board.title}" /></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer" value="${board.writer}" /></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content">${board.content}</textarea></td>
			</tr>
			<tr>
				<td colspan="2"><button>수정</button></td>
			</tr>
		</tbody>	
		</table>	
	</form>
	</div>
	<div class="footer">
		<c:import url="/include/footer.jsp" />
	</div>
</div>
</body>
</html>













