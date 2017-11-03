<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<%@ include file="/jsp/include/basicInclude.jsp" %>
</head>
<body>
<div class="container">

	<div class="header">
		<c:import url="/jsp/include/topMenu.jsp" />
	</div>

	<div class="content">
		<ol class="breadcrumb">
		  <li><a href="#1">Home</a></li>
		  <li class="active">자유게시판</li>
		</ol>
		<hr>		
		<div class="row">
		    <div class="col-md-10"></div>
		    <div class="col-md-2" style="text-align: right">
				전체 (${count})개
		    </div>
		</div>
		<div class="table-responsive">
		<table class="table table-hover">
			<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>등록일</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="board" items="${list}">
				<tr>
					<td><c:out value="${board.no}" /></td>
					<td><c:out value="${board.title}" /></td>
					<td><c:out value="${board.writer}" /></td>
					<td><fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
				</tr>
			</c:forEach>
			<c:if test="${empty list}">
				<tr>
					<td colspan='4'>입력된 게시물이 없습니다.</td>
				</tr>
			</c:if>
			</tbody>
		</table>
		</div>

			<c:if test="${count != 0 }">
			<nav>
				<ul class="pagination">
					<c:if test="${prev}">
						<li>
							<a href="list2.do?pageNo=${beginPage-1}" aria-label="Previous"> 
								<span aria-hidden="true">&laquo;</span>
							</a>
						</li>
					</c:if>
					<c:forEach var="i" begin="${beginPage}" end="${endPage}">
						<c:choose>
							<c:when test="${pageNo==i}">
								<li class="active">
									<a href="#${i}">${i}</a>
								</li>						
							</c:when>
							<c:otherwise>
								<li>
									<a href="${pageContext.request.contextPath}/board/list2.do?pageNo=${i}">${i}</a>
								</li>						
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${next}">
						<li>
							<a href="list2.do?pageNo=${endPage+1}" aria-label="Next">
								<span aria-hidden="true">&raquo;</span>
							</a>
						</li>
					</c:if>
				</ul>
			</nav>
			</c:if>
		</div>		
</div>

<script type="text/javascript">
	$("ul.nav.navbar-nav > li").removeClass(); //모든 li 클래스를 다 지우고
	$("ul.nav.navbar-nav > li:eq(1)").addClass("active"); // 추가
</script>

</body>
</html>


















