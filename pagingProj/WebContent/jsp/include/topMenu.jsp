<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">페이징 정리</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="${pageContext.request.contextPath}/board/list1.do">자유게시판1</a></li>
      <li><a href="${pageContext.request.contextPath}/board/list2.do">자유게시판2</a></li>
      <li><a href="${pageContext.request.contextPath}/board/list3.do">자유게시판3</a></li>
      <li><a href="${pageContext.request.contextPath}/board/list4.do">자유게시판4</a></li>
      <li><a href="${pageContext.request.contextPath}/board/list5.do">자유게시판5</a></li>
    </ul>
  </div>
</nav>