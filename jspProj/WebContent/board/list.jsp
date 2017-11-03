<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<style type="text/css">
	table {
	    border-collapse: collapse;
	}
	.li:hover { 
		cursor:pointer;
    	background-color: yellow;
	}
	.li:active {
		background-color: skyblue;
	}
</style>
<script type="text/javascript">
	function detail(no) {
		location.href = "/jspProj/board/detail?no="+no;
	}
</script>

<body>
	<div align="center">
	<h1>자유게시판</h1>
	
	<table border="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
		<c:forEach var="li" items="${ list }">
		<tr onclick="detail(${li.no})" class="li">
			<td><c:out value="${li.no}" /></td>
			<td><c:out value="${li.title}" /></td>
			<td><c:out value="${li.writer}" /></td>
			<td><c:out value="${li.regDate}" /></td>
		</tr>
		</c:forEach>
		
	</table>
	<br />
	<a href="${pageContext.request.contextPath}/board/writeform">글쓰기</a>
	
	<h3>${page} / ${pageCount}</h3>
	
		<c:if test="${firstPage!=1}">
			<a href="${pageContext.request.contextPath}/board/list?page=${firstPage-1}">
				<button>이전</button>
			</a>
		</c:if>
		
		<c:forEach var="p" begin="${firstPage}" end="${lastPage}">
			<a href="${pageContext.request.contextPath}/board/list?page=${p}">
				<button>${p}</button>
			</a>
		</c:forEach>

		<c:if test="${lastPage!=pageCount}">
			<a href="${pageContext.request.contextPath}/board/list?page=${lastPage+1}">
				<button>다음</button>
			</a>
		</c:if>
		
		</div>
</body>
</html>