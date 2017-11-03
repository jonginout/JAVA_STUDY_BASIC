<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function list()
	{
		location.href="${pageContext.request.contextPath}/board/list";
	}
	function del()
	{
		if(confirm('삭제하시겠습니까?')){
			location.href="${pageContext.request.contextPath}/board/delete?no=${detail.no}";
		}
	}
	function commentDel(commentNo)
	{
		if(confirm('삭제하시겠습니까?')){
			location.href="${pageContext.request.contextPath}/board/deletecomment?no=${detail.no}&comment_no="+commentNo;
			return false;
		}
	}
	function mod()
	{
		location.href="${pageContext.request.contextPath}/board/modifyform?no=${detail.no}";
	}
	function commentMod()
	{
		if(confirm('수정하시겠습니까?')){
			return true;
		}else {
			return false;
		}
	}
</script>

<body>
	<div class="container">

		<div class="header">
			<c:import url="/include/topMenu.jsp" />
		</div>

		<div class="content">
			<h1><c:out value="${detail.title}" /></h1>
			<h6 class="text-right">No.${detail.no} | 작성자 : ${detail.writer} </h6>
			<!-- 	인풋으로 넣을 수 있는 것 들은 c:out -->
			<table class="table table-hover" border="3">
				
				<tr>
					<td>
					<c:out value="${detail.content}" />
					<br /><br />
					<c:forEach var="file" items="${listFile}">
						<img src="${pageContext.request.contextPath}/common/down?filePath=${file.filePath}&sName=${file.systemName}" />
						<a href="${pageContext.request.contextPath}/common/down?filePath=${file.filePath}&sName=${file.systemName}&dName=${file.oriName}">${file.oriName}</a>
					</c:forEach>
					</td>
				</tr>
			</table>
			<div class="text-right">작성시 : ${detail.regDate}</div>
			
			<div class="text-right">
				<br /> <a href="${pageContext.request.contextPath}/board/list"></a>
				<button class="btn btn-primary" onclick="history.back();">뒤로가기</button>
				<button class="btn btn-primary" onclick="list()">리스트</button>
				<c:if test="${detail.writer==sessionScope.user.id}">
					<button class="btn btn-primary" onclick="del();">삭제</button>
					<button class="btn btn-primary" onclick="mod();">수정</button>
				</c:if>
			</div>
		<br /><br />
		<div class="card">
		  <div class="card-body">
		  
			<form action="${pageContext.request.contextPath}/board/writecomment?no=${detail.no}"
				method="post">
				<div class="form-group">
					<input type="text" name="writer" placeholder="글 작성자" 
						value="${sessionScope.user.id}"
						readonly="readonly" class="form-control"/>

					<textarea name="content" rows="4" cols="50" placeholder="코멘트" class="form-control"></textarea>
					<button class="btn btn-secondary btn-sm btn-block">댓글작성</button>
				</div>
			</form>


			<strong>댓글 ( ${ coListCount } )</strong>

			<c:if test="${listCount!=0}">

				<table class="table table-hover">
					<tr>
						<th>작성자</th>
						<th>코멘트</th>
						<th colspan="3">작성시</th>
					</tr>
					<c:forEach var="li" items="${comment}">
						<form
							action="${pageContext.request.contextPath}/board/commentmodify?no=${detail.no}&comment_no=${li.commentNo}"
							method="post">
							<c:set var="modifyForm" value="${param.comment_no}" />
							<tr>
								<c:choose>
									<c:when test="${modifyForm==li.commentNo}">
										<div class="form-group">
											<td>
												<input class="form-control" type="text" name="writer" value="${li.writer}" readonly="readonly"/>
												</td>
											<td>
												<textarea class="form-control" name="content" rows="2" cols="50">${li.content}</textarea>
											</td>
										</div>
									</c:when>
									<c:otherwise>
										<td><c:out value="${li.writer}" /></td>
										<td><c:out value="${li.content}" /></td>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${li.writer==sessionScope.user.id}">
										<td><c:out value="${li.regDate}" /></td>
										<td>
											<button class="btn btn-outline-dark btn-sm"
											onclick="return commentDel(${li.commentNo});">삭제</button>
										</td>
										<td>
											<c:choose>
												<c:when test="${modifyForm==li.commentNo}">
													<button class="btn btn-outline-dark btn-sm"
													onclick="return commentMod()">댓글수정</button>
												</c:when>
												<c:otherwise>
													<a class="btn btn-outline-dark btn-sm"
													href="detail?no=${detail.no}&comment_no=${li.commentNo}">수정</a>
												</c:otherwise>
											</c:choose>
										</td>
									</c:when>
									<c:otherwise>
										<td colspan="3"><c:out value="${li.regDate}" /></td>
									</c:otherwise>
								</c:choose>
							</tr>
						</form>
					</c:forEach>
				</table>


				<h3 class="text-center">${coPage}/${coPageCount}</h3>

				<ul class="pagination justify-content-center">
				<c:if test="${firstCoPage!=1}">
					<li class="page-item">
					<a class="page-link" 
					href="${pageContext.request.contextPath}/board/detail?no=${detail.no}&co_page=${firstCoPage-1}">
						이전
					</a>
					</li>
				</c:if>

				<c:forEach var="p" begin="${firstCoPage}" end="${lastCoPage}">
					<li class="page-item">
					<a class="page-link" 
					href="${pageContext.request.contextPath}/board/detail?no=${detail.no}&co_page=${p}">
						${p}
					</a>
					</li>
				</c:forEach>

				<c:if test="${lastCoPage!=coPageCount}">
					<li class="page-item">
					<a class="page-link" 
					href="${pageContext.request.contextPath}/board/detail?no=${detail.no}&co_page=${lastCoPage+1}">
						다음
					</a>
					</li>
				</c:if>
				</ul>

			</c:if>
		</div>
	</div>
	</div>



		<div class="footer">
			<c:import url="/include/footer.jsp" />
		</div>

	</div>
</body>
</html>
