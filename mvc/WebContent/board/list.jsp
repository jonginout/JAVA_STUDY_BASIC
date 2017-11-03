<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
 #li:hover{
 	background: skyblue;
 	cursor : pointer;
 }
</style>
</head>
<body>
<div class="container">
	<div class="header">
		<c:import url="/include/topMenu.jsp" />
	</div>
		<h1>
			<a href="${pageContext.request.contextPath}/board/writeform.do">글쓰기</a>
		</h1>
	<div class="content">
		<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
		</thead>	
		<tbody>
		<c:forEach var="board" items="${list}">
			<tr id="li" onclick="url(${board.no})">
				<td>${board.no}</td>
				<td>${board.title}</td>
				<td>${board.writer}</td>
				<td><td><fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd" /></td></td>
			</tr>		
		</c:forEach>
		</tbody>
		</table>
	</div>
	<div class="footer">
		<c:import url="/include/footer.jsp" />
	</div>
</div>

</body> 
</html>


<script type="text/javascript">
	function url(no) {
		location.href = "${pageContext.request.contextPath}/board/detail.do?no="+no;
	};
</script>



