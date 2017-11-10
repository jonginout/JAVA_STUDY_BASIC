<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/jsp/include/basicInclude.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/blog/categorysetform.css">
</head>
<body>
		<div id="content">
			
			<div id="blogtitle">
				<button onclick="goLink('main')" id="mainBtn1">메인</button>
				<button onclick="goLink('myBlog')" id="mainBtn2">내 블로그</button>
				<h1>
					<a href="javascript:goLink('myBlog');">
						<i class="fa fa-smile-o" aria-hidden="true"></i>
						${blog.title}
					</a>
				</h1>
			</div>
			
			<div id="blogContent">
				<div id="main">
					<div id="setBtnBox">
						<button onclick="goLink('clear');">기본설정</button>				
						<button onclick="goLink('category');">카테고리</button>				
						<button onclick="goLink('post');">글작성</button>				
						<button onclick="goLink('blogDel');">블로그 삭제</button>				
					</div>
					<div id="formBox">
						<form name="setForm" enctype="multipart/form-data" method="post">
							<p>
								블로그 제목 : 
								<input name="title" type="text" value="${blog.title}"/>
							</p>	
							<p>
								블로그 태그 : 
								<input name="tag" type="text" value="${blog.tag}"/>
							</p>
							<p>
								메인페이지 포스트 : 
								<select name="showNum">
									<c:forEach var="i" begin="1" end="5">
										<option value="${i}">${i}개씩</option>
									</c:forEach>
								</select>
							</p>
							<c:choose>
								<c:when test="${blog.logoNo==0}">
									<p>
										로고 이미지 : 
										<input name="logoImg" type="file" />
									</p>
								</c:when>
								<c:otherwise>
									<p>
										현재 로고 이미지 :
										<a href="javascript:delImg();">
											<img src="${pageContext.request.contextPath}/common/down.do?filePath=${logo.filePath}&systemName=${logo.systemName}" 
											alt="현재 로고 이미지" style="max-height: 150px;"/>
										</a>
										<a href="javascript:goLink('imgDown');">
											${logo.oriName}
										</a>								
									</p>	
								</c:otherwise>
							</c:choose>
							<p>
								<button type="button" onclick="goLink('clear')">폼 초기화</button>
								<button onclick="modifyBlog()">블로그 수정</button>
							</p>			
						</form>
					</div>
				</div>
			</div>
	
		</div>
		
		
	<script type="text/javascript">
		$("select[name=showNum]").val("${blog.showNum}");
		
		function goLink(type) {
			var uri = "${pageContext.request.contextPath}";
			
			var linkArr = {
				"clear" : "/blog/blogsetform.do?blogNo=${blog.blogNo}",
				"category" : "/blog/setcategory.do?blogNo=${blog.blogNo}",
				"post" : "/blog/post.do?blogNo=${blog.blogNo}",
				"blogDel" : "/blog/blogdelete.do?blogNo=${blog.blogNo}",
				"main" : "/main/main.do",
				"myBlog" : "/blog/blog.do?blogNo=${blog.blogNo}",
				"imgDown" : "/common/down.do?filePath=${logo.filePath}&systemName=${logo.systemName}&oriName=${logo.oriName}"
			};
			
			
			for ( var link in linkArr) {
				if (link==type) {		
					location.href = uri+linkArr[link];
					return;
				}
			}
		}
		
		
		function modifyBlog() {
			var f = document.setForm;
			if(confirm('정말로 수정하시겠습니까?')){		
				f.action = "${pageContext.request.contextPath}/blog/modifyblog.do?blogNo=${blog.blogNo}";
				f.submit();
			}
		}
		
		function delImg() {			
			var uri = "${pageContext.request.contextPath}";
			if(confirm('로고를 정말로 삭제하시겠습니까?')){
				location.href = uri + "/blog/deletelogo.do?blogNo=${blog.blogNo}";
			}			
		}
	</script>
	
</body>
</html>

