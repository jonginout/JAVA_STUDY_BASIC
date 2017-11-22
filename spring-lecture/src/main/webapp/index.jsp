<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="${pageContext.request.contextPath}/hello/hello.do">hello</a>
	<a href="${pageContext.request.contextPath}/method/method.do">method</a>
	<a href="${pageContext.request.contextPath}/form/joinForm.do">form</a>
	<a href="${pageContext.request.contextPath}/ajax/resBody.do">문자열 응답</a>
	<a href="${pageContext.request.contextPath}/ajax/resBody.json">json 응답</a>
	<a href="${pageContext.request.contextPath}/ajax/resVOBody.json">VO 응답</a>
	<a href="${pageContext.request.contextPath}/ajax/resStringListBody.json">List 응답</a>
	<a href="${pageContext.request.contextPath}/ajax/resVOListBody.json">VO_List 응답</a>
	
	<br /><br />
	<h3>rest</h3>

	<a href="${pageContext.request.contextPath}/rest/resBody.do">문자열 응답</a>
	<a href="${pageContext.request.contextPath}/rest/resBody.json">json 응답</a>
	<a href="${pageContext.request.contextPath}/rest/resVOBody.json">VO 응답</a>
	<a href="${pageContext.request.contextPath}/rest/resStringListBody.json">List 응답</a>
	<a href="${pageContext.request.contextPath}/rest/resVOListBody.json">VO_List 응답</a>
	
		
	<br /><br />
	<h3>rest</h3>
	
	<a href="${pageContext.request.contextPath}/file/uploadForm.do">VO_List 응답</a>
	<!-- 서블릿 없이 spring-mvc.xml 설정만으로 페이지 이동가능 -->

</body>
</html>