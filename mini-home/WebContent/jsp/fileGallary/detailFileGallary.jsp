<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:formatDate var="d" value="${board.regDate}" pattern="yyyy-MM-dd hh:mm:ss"/>
<%@ taglib prefix="navi" tagdir="/WEB-INF/tags" %>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
					<p class="h1">자료실</p>
						<table class="table">
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
								<th>작성일</th>
								<td>${d}</td>
							</tr>
							<tr>
								<th>첨부된 파일</th>
								<c:forEach var = "s" items="${fileList}" varStatus="loop">
									<td>
										<a href="${pageContext.request.contextPath}/common/down?sName=${s.systemName}&dName=${s.oriName}&filePath=${s.filePath}" >
										${s.oriName}
										</a>
									</td>
								</c:forEach>
							<tr>
								<th>내용</th>
								<td>${board.content}</td>
							</tr>
							<tr align="left" colspan="2">
								<td>
									<c:if test="${likeCheck == 0 && hateCheck == 0}">
										<a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/jsp/vote/votelikeboard
										?homepage_no=${homepage_no}
										&category_group_no=${category_group_no}
										&category_no=${category_no}
										&no=${board.no}
										&member_no=${member_no}
										&commentPageNo=${commentPageNo}">좋아요
										</a>
									</c:if>
									<c:if test="${likeCheck != 0}">
										이미 좋아한 글입니다.
									</c:if>
								</td>
								<td>
									<c:if test="${hateCheck == 0 && likeCheck == 0}">
										<a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/jsp/vote/votehateboard
										?homepage_no=${homepage_no}
										&category_group_no=${category_group_no}
										&category_no=${category_no}
										&no=${board.no}
										&member_no=${member_no}
										&commentPageNo=${commentPageNo}">싫어요
										</a>
									</c:if>
									<c:if test="${hateCheck != 0}">
										이미 싫어한 글입니다.
									</c:if>
								</td>							
							</tr>
							<tr>
								<td colspan="2"> ${likeCount}</td>
								<td colspan="2"> ${hateCount}</td>
							</tr>
						</tbody>	
						</table><br>
						
						
						
						<a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/jsp/filegallary/listfilegallary?homepage_no=${homepage_no}&category_group_no=${category_group_no}&category_no=${category_no}">목록</a>
						<a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/jsp/filegallary/modifyformfilegallary?homepage_no=${homepage_no}&category_group_no=${category_group_no}&category_no=${category_no}&no=${board.no}">수정</a>
						<a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/jsp/filegallary/deletefilegallary?homepage_no=${homepage_no}&category_group_no=${category_group_no}&category_no=${category_no}&no=${board.no}">삭제</a>
						<br>
						
					
						<form method="post" action="${pageContext.request.contextPath}/jsp/filegallary/writecomment?homepage_no=${homepage_no}&category_group_no=${category_group_no}&category_no=${category_no}&no=${board.no}&member_no=${member_no}">
						
						<table>
							<div> 총 댓글 수 : ${commentPageResult.count}</div>
							<tr><td>댓글</td></tr>
							<c:forEach var = "s" items="${commentList}" varStatus="loop">
								<tr>
									<td>${s.writer }</td>
									
									<c:choose>
										<c:when test="${commentNo != s.commentNo}">
											<td>${s.content}</td>
										</c:when>
										<c:when test="${commentNo == s.commentNo}">
											<td><input type="text" name="modifyComment" value="${s.content}"></td>
										</c:when>
									</c:choose>
									
									<td>${s.updateDate}</td>
									
									<c:choose>
										<c:when test="${commentNo != s.commentNo}">
											<td><a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/jsp/filegallary/detailfilegallary
											?homepage_no=${homepage_no}
											&category_group_no=${category_group_no}
											&category_no=${category_no}
											&no=${s.no}
											&commentPageNo=${commentPageNo}
											&commentNo=${s.commentNo}">
											수정</a>
											</td>
										</c:when>
										<c:when test="${commentNo == s.commentNo}">
											<td>
												<input type="hidden" name="commentNo" value="${s.commentNo}">
												<button class="btn btn-default">수정완료</button>
											</td>
										</c:when>
									</c:choose>
									<td><a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/jsp/filegallary/deletecomment
									?homepage_no=${homepage_no}
									&category_group_no=${category_group_no}
									&category_no=${category_no}
									&no=${board.no}
									&commentNo=${s.commentNo}">
									삭제</a>
									</td>
								</tr>
							</c:forEach>
							<tr><td><navi:page2 data="${commentPageResult}" /></td></tr>
							<tr><td colspan="5">------------------------------------------------------------------------------------------------
							</td></tr>
							<tr>
								<td><input type="hidden" name="no" value="${board.no}"/><br>
								<td><input type="hidden" name="writer" value="${writer}"/></td>
								<td><input type="hidden" name="member_no" value="${member_no}"/><br>
								<td><input type="text" name="content" /></td>
								<td><button class="btn btn-default">댓글등록</button></td>
							</tr>
						</table>
						</form>
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