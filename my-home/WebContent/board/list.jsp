<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.li:hover { 
		cursor:pointer;
    	background-color: yellow;
	}
</style>
</head>


<body>

	<div class="container">

		<div class="header">
			<c:import url="/include/topMenu.jsp" />
		</div>

		<div class="content">
			<h1><a href="list">자유게시판</a></h1>
	
			<table class="table table-hover">
				<thead>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
				</thead>
				<c:forEach var="li" items="${ list }">
					<tr onclick="detail(${li.no})" class="li">
						<td><c:out value="${li.no}" /></td>
						<td><c:out value="${li.title}" /></td>
						<td><c:out value="${li.writer}" /></td>
						<td><c:out value="${li.regDate}" /></td>
					</tr>
				</c:forEach>
	
			</table>
			<c:if test="${sessionScope.user!=null}">
				<br />
				<a class="btn btn-primary float-right" 
				href="${pageContext.request.contextPath}/board/writeform">글쓰기</a>
			</c:if>
	
			<h3 class="text-center">${page}/ ${pageCount}</h3>

			<c:if test="${pageCount != 0 }">
			<ul class="pagination justify-content-center">
			<c:if test="${firstPage!=1}">
				<li class="page-item">
				<a class="page-link" 
				href="${pageContext.request.contextPath}/board/list?page=${firstPage-1}&search=${param.search}&field=${param.field}">
					이전
				</a>
				</li>
			</c:if>
	
			<c:forEach var="p" begin="${firstPage}" end="${lastPage}">
				<c:choose>
					<c:when test="${page==p}">
						<li class="page-item active">
							<a class="page-link" 
							href="#${p}">
								${p}
							</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="page-item">
							<a class="page-link" 
							href="${pageContext.request.contextPath}/board/list?page=${p}&search=${param.search}&field=${param.field}">
								${p}
							</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
	
			<c:if test="${lastPage!=pageCount}">
				<li class="page-item">
				<a class="page-link" 
				href="${pageContext.request.contextPath}/board/list?page=${lastPage+1}&search=${param.search}&field=${param.field}">
					다음
				</a>
				</li>
			</c:if>
			</ul>
			</c:if>
		</div>
		
		<center>
			<form action="${pageContext.request.contextPath}/board/list" method="get">
				<div class="input-group col-md-6">
					<select name="field" class="custom-select" id="searchForm">
						<option value="title">제목</option>
						<option value="content">내용</option>
						<option value="writer">작성자</option>
					</select> <input type="text" class="form-control" 
					name="search" placeholder="검색어.." value="${param.search}">
					<span class="input-group-btn">
						<button class="btn btn-primary">검색</button>
					</span>
				</div>
			</form>
		</center>

		<div class="footer">
			<c:import url="/include/footer.jsp" />
		</div>

	</div>
	
</body>
</html>

<script type="text/javascript">
	function detail(no) {
		location.href = "${pageContext.request.contextPath}/board/detail?no="+no;
	}
	var field = "${param.field}";
	if(field!=""){
	    document.getElementById("searchForm").value = field;
	}
</script>