<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!-- 
		
		파일 업로드시 주의사항
		- 무조건 post 방식으로 전송
		- encType 속성의 값으로 multipart/form-data
		  : 속성이 지정되지 않으면 파일의 내용이 전송되지 않는다.
	
	 -->
	<form action="${pageContext.request.contextPath}/day06/test01" 
		method="post"
		enctype="multipart/form-data">
		
		<input type="text" name="msg" />
		<br />
		<input type="file" name="attachFile1"/>
		<br />
		<input type="file" name="attachFile2"/>
		<br />
		<button>전송</button>
		
	</form>
	
</body>
</html>