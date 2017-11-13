<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/jsp/include/basicInclude.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main/main.css">
</head>
<body>
		<div id="content">
			
			<h1 id="mainLogo">
				<a href="">
					<i class="fa fa-home" aria-hidden="true"></i>
					Jongin Blog
				</a>
			</h1>
			
			<h3>
				<c:choose>
					<c:when test="${empty user}">		
						<button class="btn" id="login">로그인</button>			
						<button class="btn" id="signin">회원가입</button>			
					</c:when>
					<c:otherwise>	
						<button class="btn" id="logout">로그아웃</button>
						<c:choose>
							<c:when test="${myBlog}">
								<button class="btn" id="blogBtn">내 블로그</button>
							</c:when>
							<c:otherwise>
								<button class="btn" id="makeBlog">블로그 생성</button>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</h3>
			
			<form action="">
				<div id="searchBox">
					<select id="searchSelect" name="column">
						<option value="title">블로그 제목</option>
						<option value="memberNo">블로거</option>
						<option value="tag">태그</option>
					</select> 
					<input id="searchText" type="text" name="word"/>
					<button id="searchBtn">검색</button>
				</div>
			</form>	
			
		
		
				<table id="blogList" border="1" width="400">
					<tr>
						<td>번호</td>
						<td>블로그명</td>
						<td>블로거</td>
						<td>로고</td>
					</tr>
					<c:forEach var="blog" items="${blogList}">
						<tr class="goBlog" data-blogno="${blog.blogNo}">
							<td>${blog.blogNo}</td>
							<td>${blog.title}</td>
							<td>${blog.id}</td>
							<td>${blog.logoNo}</td>
						</tr>
					</c:forEach>
				</table>
			
		</div>
		
</body>
</html>

<script type="text/javascript">
	
	if("${param.column}") $("#searchSelect").val("${param.column}");
	if("${param.word}") $("#searchText").val("${param.word}");
	
	//메인화면 버튼
	var mainBtn = document.querySelector("#mainLogo")
	mainBtn.addEventListener("click", function(e){
		location.href=		
			"${pageContext.request.contextPath}/main/main.do";
	})
	
	// 로그인 여부와 관련있는 버튼
	var btn = document.querySelectorAll(".btn");
	for(b of btn){
		b.addEventListener("click", function(e) {		
			switch (e.target.id) {
			case "login":
				location.href=		
					"${pageContext.request.contextPath}/login/loginform.do";
				break;
			case "signin":
				location.href=		
					"${pageContext.request.contextPath}/signin/signinform.do";
				break;
			case "logout":
				location.href=		
					"${pageContext.request.contextPath}/login/logout.do";
				break;
			case "blogBtn":
				location.href=		
					"${pageContext.request.contextPath}/blog/myblog.do?memberNo=${user.memberNo}";
				break;
			case "makeBlog":
				if (confirm('나만의 블로그를 생성하겠습니까?')) {			
					location.href=		
					"${pageContext.request.contextPath}/blog/bloginsert.do";
				}
				break;
			}
		}, false);
	}

	// 블로그 가기 버튼
	var goBlog = document.querySelectorAll(".goBlog");
	for(g of goBlog){		
		g.addEventListener("click", function(e){
			var blogNo = e.target.parentNode.getAttribute("data-blogno"); /*자식은 chileNode*/
			location.href=		
				"${pageContext.request.contextPath}/blog/blog.do?blogNo="+blogNo;
		}, false);
	}
	
</script>