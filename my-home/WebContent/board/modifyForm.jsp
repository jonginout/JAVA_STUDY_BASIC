<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

	<script type="text/javascript">
	function mod()
	{
		if(confirm('수정하시겠습니까?')){
			return true;
		}else {
			return false;
		}
	}
	function fileDel(no, fileNo) {
		if(confirm('정말로 파일을 삭제 하시겠습니까?')){
			location.href = "${pageContext.request.contextPath}/board/filedelete?no="+no+"&fileno="+fileNo;
			return false;			
		}else {
			return false;			
		}
	}
	</script>
	<style type="text/css">
		#submit{
			width: 577px;
			margin-bottom: 20px;
		}
	</style>
	<body>
	<div class="container">

		<div class="header">
			<c:import url="/include/topMenu.jsp" />
		</div>

		<div class="content">

			<h1>글 작성</h1>

			<form method="post"
				action="${pageContext.request.contextPath}/board/modify?no=${detail.no}"
				enctype="multipart/form-data"
				>

				<div>
					제목 : <input type="text" name="title" value="${detail.title}">
					작성자 : <input type="text" name="writer" value="${detail.writer}" readonly="readonly">
				</div>
				<br />
				
				<c:choose>
					<c:when test="${empty listFile}">
						<input type="file" name="attachFile1"/>
						<input type="file" name="attachFile2"/>
					</c:when>
					<c:otherwise>
						<c:forEach var="file" items="${listFile}">
							<c:if test="${listFileSize==1}">
								<input type="file" name="attachFile1"/>				
							</c:if>
							${file.oriName} 
							<button onclick="return fileDel(${detail.no},${file.fileNo})">파일삭제</button>
							<br>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				
				<br /><br />
				<div>
					<textarea rows="6" cols="80" name="content">${detail.content}</textarea>
				</div>
				<div>
					<button onclick="return mod();" id="submit">수정</button>
				</div>
			</form>

			<a href="detail?no=${detail.no}">수정취소</a> / <a href="list">글 목록</a>
		</div>


		<div class="footer">
			<c:import url="/include/footer.jsp" />
		</div>

	</div>
</body>
</html>


<%-- <c:forEach var="file" items="${listFile}"> --%>
<%-- 	<img src="${pageContext.request.contextPath}/common/down?filePath=${file.filePath}&sName=${file.systemName}" /> --%>
<%-- 	<a href="${pageContext.request.contextPath}/common/down?filePath=${file.filePath}&sName=${file.systemName}&dName=${file.oriName}">${file.oriName}</a> --%>
<%-- </c:forEach> --%>





