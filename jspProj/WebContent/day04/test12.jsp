<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 

-->
<%
	List<String> list = new ArrayList<>();
	list.add("야구");
	list.add("축구");
	list.add("배드민턴");
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 
	
		for(int i = 1; i <=10; i++){
			System.out.println(i);
		}
		
	--%>
										<!-- step은 기본값이 1 -->
	<c:forEach var="i" begin="1" end="10">
		${ i }<br>
	</c:forEach>
	
	<select>
		<c:forEach var="i" begin="1970" end="2010">
			<option >${ i }</option>
		</c:forEach>
	</select>
	
		<%-- 
	
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("a");
		list.add("a");

		for(String s : list){
			System.out.println(s);
		}
		
	--%>
	<br />
	
<%-- 
	<c:set var="cnt" value="1" /> 
	<c:forEach var="s" items="${list}">
		<c:if test="${cnt != 1}">, </c:if>${ s }
		<c:set var="cnt" value="${cnt + 1}"/>
	</c:forEach>
--%>

	<c:forEach var="s" items="${ list }" varStatus="loop">
		${ loop.first }-${ loop.last }-${ loop.count }-${ s } <br />
<!-- 처음인지 물어봄 / 마지막인지 물어봄 / 반복횟수를 물어봄 -->
	</c:forEach>
	
	

	<c:forEach var="s" items="${ list }" varStatus="loop">
		<c:if test="${ loop.first == false }">, </c:if>${ s }
	</c:forEach>
	
	
</body>
</html>