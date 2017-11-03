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
		/common/down : 서블릿 패스경로
		filePath : 다운로드할 파일이 들어있는 디렉토리
		sName : 실제 서버에 저장된 파일명
		dName : 사용자 컴퓨터에 다운로드할 이름 
	-->
	<a href="${pageContext.request.contextPath}/common/down?filePath=/2017/10/17/14&sName=8c230fbd-f826-4851-a54f-f98c6f6189df.jpg&dName=213.jpg">213.jpg</a><br>
	<a href="${pageContext.request.contextPath}/common/down?filePath=/2017/10/17/14&sName=8c230fbd-f826-4851-a54f-f98c6f6189df.jpg">213.jpg</a>
	<img src="${pageContext.request.contextPath}/common/down?filePath=/2017/10/17/14&sName=8c230fbd-f826-4851-a54f-f98c6f6189df.jpg" />
	
	
</body>
</html>