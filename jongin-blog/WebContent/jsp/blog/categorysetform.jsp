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
					
					<div id="categoryList">
						<h1>카테고리 설정</h1>
						<table id="blogTable" border="1">
							<tr>
								<td>번호</td>
								<td>카테고리명</td>
								<td>보이기 유형</td>
								<td>포스트 수</td>
								<td>설명</td>
								<td>수정</td>
								<td>삭제</td>
							</tr>
							<c:set var="i" value="0"/>
							<c:forEach var="category" items="${categoryList}">
								<tr>
									<td>${i = i + 1}</td>
									<td>${category.name}</td>
									<td>${category.showType}</td>
									<td>${category.showNum}</td>
									<td>${category.description}</td>
									<td><button>수정</button></td>
									<td><button>삭제</button></td>
								</tr>
							</c:forEach>
						</table>
					</div>
					
					<div id="setCategoryBox">
						<h2>새 카테고리 추가</h2>
						<form name="categoryForm" method="post">
							<p>
								카테고리명 : 
								<input type="text" name="name"/>
							</p>
							<p>
								보이기 유형 : 
								<label for="title">타이틀</label>
								<input type="radio" name="showType" id="title"/>
								<label for="all">글전체</label>
								<input type="radio" name="showType" id="all"/>
							</p>
							<p>
								포스트 수 : 
								<input type="text" name="showNum"/>
								개 (1~20)
							</p>
							<p>
								설명 :
								<input type="text" name="description"/>
							</p>
							<p>
								<button>카테고리 추가</button>
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
				"category" : "/blog/categorysetform.do?blogNo=${blog.blogNo}",
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

