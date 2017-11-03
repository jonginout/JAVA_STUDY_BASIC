<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	1 : <%= 1%> <br />
	1 : ${1} <br />
	5 == 5 : <%= 5 == 5 %> <br />
	5 == 5 : ${ 5 == 5 } <br />
	5 == 5 : ${ 5 eq 5 } <br />
	5 == 5 : ${ 5 != 5 } <br />
	<%-- 5 == 5 : ${ 5 ne 5 } <br /> --%>
	empty "a" : ${ empty "a" } <br />
	empty "" : ${ empty "" } <br />	
	empty str : ${ empty str } <br />	
</body>
</html>