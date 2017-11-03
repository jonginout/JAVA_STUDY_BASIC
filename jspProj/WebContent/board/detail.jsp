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
	table {
	    border-collapse: collapse;
	}
	hr{
		margin: 30px;
	}
	.commentForm{
	  display:inline-block;
	  position:relative;
	  margin-bottom: 20px;
	}
		
	.commentForm>button{
	  position:absolute;
	  bottom:10px;
	  right:10px;
	}
		
	.commentForm>textarea{
	  display:block;
	}	
</style>
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
	<div align="center">
	
	<h1>게시글</h1>

	<!-- 	인풋으로 넣을 수 있는 것 들은 c:out -->
	<table border="1">
		<tr>
			<td>글 번호</td>
			<td><c:out value="${detail.no}" /></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><c:out value="${detail.title}" /></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><c:out value="${detail.writer}" /></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><c:out value="${detail.content}" /></td>
		</tr>
		<tr>
			<td>작성일자</td>
			<td><c:out value="${detail.regDate}" /></td>
		</tr>
	</table>

	<br />
	<a href="${pageContext.request.contextPath}/board/list"></a>
	<button onclick="history.back();">뒤로가기</button>
	<button onclick="del();">삭제</button>
	<button onclick="mod();">수정</button>
	<button onclick="list()">리스트</button>

	<hr />


	<form
		action="${pageContext.request.contextPath}/board/writecomment?no=${detail.no}"
		method="post">
		<div>
			<input type="text" name="writer" placeholder="글 작성자" style="width: 368px"/>
		</div>
		<div class="commentForm">
			<textarea name="content" rows="4" cols="50" placeholder="코멘트" ></textarea>
			<button>댓글작성</button>
		</div>
	</form>

	
	<strong>댓글 ( ${ listCount } )</strong>
	
	<c:if test="${listCount!=0}">
	
	<table border="1">
		<tr>
			<th>작성자</th>
			<th>코멘트</th>
			<th colspan="3">작성시</th>
		</tr>
		<c:forEach var="li" items="${comment}">
			<form action="${pageContext.request.contextPath}/board/commentmodify?no=${detail.no}&comment_no=${li.commentNo}"
				method="post">
			<c:set var="modifyForm" value="${param.comment_no}" />
				<tr>
					<c:choose>
						<c:when test="${modifyForm==li.commentNo}">
							<td><input type="text" name="writer" value="${li.writer}" /></td>
							<td><textarea name="content" rows="4" cols="50">${li.content}</textarea></td>
						</c:when>
						<c:otherwise>
							<td><c:out value="${li.writer}" /></td>
							<td><c:out value="${li.content}" /></td>
						</c:otherwise>
					</c:choose>

					<td><c:out value="${li.regDate}" /></td>
					<td>
						<button onclick="commentDel(${li.commentNo});">삭제</button>
					</td>
					<td>
						<c:choose>
							<c:when test="${modifyForm==li.commentNo}">
								<button onclick="return commentMod()">댓글수정</button>
							</c:when>
							<c:otherwise>
								<a href="detail?no=${detail.no}&comment_no=${li.commentNo}">수정</a>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</form>
		</c:forEach>
	</table>
	
		<h3>${coPage} / ${pageCount}</h3>
		
		
		<c:if test="${firstPage!=1}">
			<a href="${pageContext.request.contextPath}/board/detail?no=${detail.no}&co_page=${firstPage-1}">
				<button>이전</button>
			</a>
		</c:if>
		
		<c:forEach var="p" begin="${firstPage}" end="${lastPage}">
			<a href="${pageContext.request.contextPath}/board/detail?no=${detail.no}&co_page=${p}">
				<button>${p}</button>
			</a>
		</c:forEach>

		<c:if test="${lastPage!=pageCount}">
			<a href="${pageContext.request.contextPath}/board/detail?no=${detail.no}&co_page=${lastPage+1}">
				<button>다음</button>
			</a>
		</c:if>


		</c:if>
	</div>

</body>
</html>
