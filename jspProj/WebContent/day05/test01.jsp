<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h2>쿠키 추출</h2>
	
	<%
		
		Cookie [] cookies = request.getCookies();
		for( Cookie c : cookies ){
			String name = URLDecoder.decode(
						c.getName(), "utf-8"
					); 
			String value = URLDecoder.decode(
					c.getValue(), "utf-8"
				); 
	%>
			<%=name %>, <%=value %> <br>
	<%
		}
	
	%>
	
	
	
	<h2>쿠키 설정</h2>
	
	<form action="${pageContext.request.contextPath}/day05/test01">
	
		쿠키 이름 : <input type="text" name="name"/>
		쿠키 값 : <input type="text" name="value"/>
		<button>쿠키 생성</button>
	
	</form>
	
	${ c.name }
	
</body>
</html>