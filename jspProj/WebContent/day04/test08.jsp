<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
사용자 정의 태그 (custom tag)를 활용하기 위해서는
태그에 대한 라이브러리를 참조할 수 있도록 지시자를 설정한다
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- 
	
		공유영역을 설정을 대체 위해 나온 태그
		pageContext.setAttribute("a","b"); 
		바뀔수 있는 부분 : setAttribute 빼고 다
		
		그래서 set 태그는 -> setAttribute
		var -> a
		value -> b 
		scope -> pageContext
		( page, request, session, application )
		
	--%>
	
	<%-- <c:set var ="a" value="b" scope = "page" /> --%>
	<%-- scope 기본값이 page --%>

	<c:set var ="cnt" value="1" />
	<c:set var ="cnt" value="${cnt+1}" />
	${cnt}
	
	 <br />
	 
	<c:set var ="cnt" value="10" scope="request"/>
	${requestScope.cnt}
	
</body>
</html>