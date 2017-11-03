<%@page import="boardComment.BoardCommentDomain"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="diary.DiaryDomain"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="navi" tagdir="/WEB-INF/tags" %>

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
					<p class="h3">다이어리 글</p>
					<table class="table">
					
						<tr>
							<th>글쓴이 </th>
							<td width="85%"><input type="text" class="form-control" value="${diary.writer}" readonly></td>
						</tr>
						<tr>
							<th>제목 </th>
							<td width="85%"><input type="text" class="form-control" value="${diary.title}" readonly></td>
						</tr>
						<tr>
							<th>내용 </th>
							<td width="85%"><textarea class="form-control" rows="3" readonly>${diary.content}</textarea></td>
						</tr>
						<tr>
							<th>작성일 </th>
							<td width="85%"><input type="text" class="form-control" value="${diary.updateDate}" readonly></td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<a href="${pageContext.request.contextPath }/jsp/diary/diarymodify?no=${diary.no}">수정</a>
								<a href="${pageContext.request.contextPath }/jsp/diary/diarydelete?no=${diary.no}">삭제</a>
								<a href="${pageContext.request.contextPath }/jsp/diary/diarylist?no=${diary.no}">뒤로</a>
							</td>
						</tr>
					</table>		
				<table class="table">
			
					<tbody>
					
					<c:choose>
					<c:when test="${empty commentContent}">
						<c:forEach var="comment" items="${list}" varStatus="loop">
							<tr>
								<th>${comment.writer} </td>
								<td width="40%">${comment.content}</td>
								<td>${comment.regDate}</td>
								
							
								<td><a href="${pageContext.request.contextPath }/jsp/boardcomment/commentmodify?commentNo=${comment.commentNo}">수정</a></td>
								<td><a href="${pageContext.request.contextPath }/jsp/boardcomment/commentdelete?commentNo=${comment.commentNo}">삭제</a></td>
							</tr>
						</c:forEach>
					</c:when>
					
					<c:otherwise>
						<c:forEach var="comment" items="${list}">
						<c:choose>
							<c:when test="${ commentNo eq comment.commentNo }">
								<form action="${pageContext.request.contextPath }/jsp/diary/diarycommentprocess?no=${diary.no}" method="post">							
									<tr>
										<input type="hidden" value="${comment.commentNo}" name="commentNo"/>
										<td>${comment.writer}</td>
										<td><input type="text" value="${comment.content}" class="form-control" name="content"></td>
										<td><button>등록</button></td>
									
										<td><a href="${pageContext.request.contextPath }/jsp/boardcomment/commentmodify?commentNo=${comment.commentNo}">수정</a></td>
										<td><a href="${pageContext.request.contextPath }/jsp/boardcomment/commentdelete?commentNo=${comment.commentNo}">삭제</a></td>
									</tr>
								</form>
							</c:when>
							<c:otherwise>
								<tr>
									<td>${comment.writer}</td>
									<td width="40%">${comment.content}</td>
									<td>${comment.regDate}</td>
									
								
									<td><a href="${pageContext.request.contextPath }/jsp/boardcomment/commentmodify?commentNo=${comment.commentNo}">수정</a></td>
									<td><a href="${pageContext.request.contextPath }/jsp/boardcomment/commentdelete?commentNo=${comment.commentNo}">삭제</a></td>
								</tr>
							</c:otherwise>
							</c:choose>
						</c:forEach>	
					</c:otherwise>
					</c:choose>
						</tbody>
						
					</table>
				<form action="${pageContext.request.contextPath }/jsp/diary/diarycommentprocess?no=${diary.no}" method="post">
					<table class="table">
						<tbody>
							<tr>
								<th>글쓴이</th>
								<td  width="20%"><input type="text" name="writer" class="form-control" value="${user.id}" readonly></td>
								<th>내용</th>
								<td width="45%"><input type="text" name="content" class="form-control"></td>
								<td><button class="btn btn-default">등록</button></td>
							</tr>
						</tbody>
					</table>
				</form>
						<!-- 페이징 적용할 부분  -->
						<navi:commentPage data="${pageResult}"/>
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