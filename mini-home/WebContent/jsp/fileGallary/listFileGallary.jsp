<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
					<p class="h1">자료실</p>
					<div> 자료실 게시물 수 : ${pageResult.count}</div>
					<table class="table table-hover">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
						</tr>
					</thead>	
					<tbody>
					
					<c:forEach var = "s" items="${list}" varStatus="loop">
						<tr style="cursor: pointer;"
							onclick="detail(${homepage_no}, ${category_group_no}, ${category_no}, ${s.no}, ${member_no})">
							<td>${s.no }</td>
							<td>
							${s.title }
							</td>

							<td>${s.writer }</td>
							
							<fmt:formatDate var="d" value="${s.updateDate}" pattern="yyyy/MM/dd HH:mm"/>
							<td>${d}</td>
						</tr>
					</c:forEach>
					
					<c:if test="${empty list}">
						<tr>
							<td colspan='4'>입력된 게시물이 없습니다.</td>
						</tr>
					</c:if>
						<tr><td><navi:page2 data="${pageResult}" /></td></tr>
					</tbody>
					</table>
					<a style="cursor: pointer;" 
					onclick="move(${homepage_no}, ${category_group_no}, ${category_no}, 'writeformfilegallary')">
					글쓰기
					</a>
					<div>
					<a style="cursor: pointer;" 
					onclick="move(${homepage_no}, ${category_group_no}, ${category_no}, 'listfilegallarycategory')">
					뒤로
					</a>
					
					</div>						
								</center>
								
							</div>
						</div>

		<div class="footer">
			<c:import url="/jsp/include/footer.jsp" />
		</div>
		
	</div>
	
	
	<script type="text/javascript">

 		
 		function move(homepageNo, categoryGrpNo, categoryNo, page) {
 			var url = "${pageContext.request.contextPath}/jsp/filegallary/"+page
 			+ "?homepage_no=" + homepageNo + "&category_group_no=" + categoryGrpNo
 			+ "&category_no=" + categoryNo;
 			
 			location.href=url;
 		}
 		
 		function detail(homepageNo, categoryGrpNo, 
 				categoryNo, no, memberNo) {
			var url = "${pageContext.request.contextPath}/jsp/filegallary/detailfilegallary?homepage_no=" 
					+ homepageNo + "&category_group_no="
					+ categoryGrpNo + "&category_no="
					+ categoryNo + "&no="
					+ no + "&member_no="
					+ memberNo + "&commentPageNo=1";
					
			location.href=url;
		}
	</script> 
</body>
</html>