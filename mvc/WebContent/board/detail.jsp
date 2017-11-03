<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>     
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
			<table>
			<tbody>
				<tr>
					<th>번호</th>
					<td>${board.no}</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${board.title}</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${board.content}</td>
				</tr>
				<tr>
					<th>등록일시</th>
					<td><fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
			</tbody>	
			</table>	
			<a href="${pageContext.request.contextPath}/board/list.do">목록</a>
			<a href="${pageContext.request.contextPath}/board/modifyform.do?no=${board.no}">수정</a>
			<a href="${pageContext.request.contextPath}/board/delete.do?no=${board.no}">삭제</a> 
		</div>
		<div class="footer">
			<c:import url="/include/footer.jsp" />
		</div>
	</div>
</body>
</html>













