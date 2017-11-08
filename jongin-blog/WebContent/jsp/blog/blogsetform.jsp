<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/jsp/include/basicInclude.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/blog/blogsetform.css">
</head>
<body>
		<div id="content">
			
			<div id="blogtitle">
				<a id="mainBtn" href="${pageContext.request.contextPath}/main/main.do">메인으로..</a>
				<a id="mainBtn" href="${pageContext.request.contextPath}/main/main.do">내 블로그 메인</a>
				<h1>
					<a href="${pageContext.request.contextPath}/blog/blog.do?blogNo=${blog.blogNo}">
						<i class="fa fa-smile-o" aria-hidden="true"></i>
						${blog.title}
					</a>
				</h1>
			</div>
			
			<div id="blogContent">
				<div id="main">
					<div id="setBtnBox">
						<button>기본설정</button>				
						<button>카테고리</button>				
						<button>글작성</button>				
						<button>블로그 삭제</button>				
					</div>
					<div id="formBox">
						<form action="" enctype="multipart/form-data">
							<p>
								블로그 제목 : 
								<input type="title" value="${blog.title}"/>
							</p>	
							<p>
								블로그 태그 : 
								<input type="tag" value="${blog.tag}"/>
							</p>
							<p>
								메인페이지 포스트 : 
								<select name="showNum">
									<c:forEach var="i" begin="1" end="5">
										<option value="${i}">${i}개씩</option>
									</c:forEach>
								</select>
							</p>
							<p>
								로고 이미지 : 
								<input name="logoImg" type="file" />
							</p>
							<p>
								현재 로고 이미지 :
								<img src="" alt="현재로고 이미지" />
							</p>	
							<p>
								<button type="button" onclick="clean()">폼 초기화</button>
								<button type="button" onclick="set()">블로그 수정</button>
							</p>			
						</form>
					</div>
				</div>
			</div>
	
		</div>
		
		
<script type="text/javascript">
	function clean() {
		location.href = 
			"${pageContext.request.contextPath}/blog/blog.do?blogNo=${blog.blogNo}";
	}
	function set() {
		location.href = 
			"${pageContext.request.contextPath}/blog/modifyblog.do?blogNo=${blog.blogNo}";
	}
</script>	
</body>
</html>
