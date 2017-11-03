<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>네이버 검색하기</h2>

	<form action="" method="post">
			
			검색어 : <input type="text" name="word"/>
			출력수 : <input type="text" name="showNum"/>
			
			<button>검색</button>
						
	</form>

	<c:forEach var="i" items="${list}">
		<h3><a href="${i.link}">${i.title}</a></h3>
	</c:forEach>

</body>
</html>