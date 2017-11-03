<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
	#homePageLi:hover { 
		cursor:pointer;
    	background-color: skyblue;
	}
</style>
<br />
<table class="table">
	<thead>
		<th>미니홈피명</th>
		<th>ID</th>
	</thead>
	<c:forEach var="hp" items="${homePageList}">
		<tr id="homePageLi" onclick="homepageDetail(${hp.homepageNo})">
			<td>${hp.title}</td>
			<td>${hp.id}</td>
		</tr>
	</c:forEach>
</table>

<script type="text/javascript">
	function homepageDetail(homepageNo){
		location.href = "${pageContext.request.contextPath}/jsp/homepage/homepage?homepage_no="+homepageNo;
	};
</script>