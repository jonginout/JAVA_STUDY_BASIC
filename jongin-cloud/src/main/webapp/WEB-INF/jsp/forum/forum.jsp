<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="robots" content="noindex">
	
	<%@ include file="/WEB-INF/jsp/include/basicinclude.jsp" %>

	<!-- 클라우드 CSS -->
	<link href="${pageContext.request.contextPath}/css/forum/forum.css" rel="stylesheet" type="text/css" id="skinSheet">
	

</head>
<body style="background: #123142">

  	<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
			<!------------------------------------------------------------>
			<!------------------------------------------------------------>
			<div id="forum-content" class="col-xs-12 col-sm-push-3 col-sm-6">
				<div class="container write-box">
					<div id="attach-file"></div>
					<textarea class="form-control write-forum-input" rows="2"></textarea>
				</div>	

				<div class="btn-group btn-group-justified " aria-label="...">
					<div class="btn-group btn-xs write-btn write-btn-group">
					  <button type="button" class="btn write-btn file-forum" data-toggle="modal" data-target="#file-modal">
						<i class="fa fa-cloud-download" aria-hidden="true"></i> 
						클라우드 첨부 
					  </button>
					</div>
					<div class="btn-group btn-xs write-btn write-btn-group">
						<button type="button" class="btn write-btn write-forum">
						  <i class="fa fa-pencil" aria-hidden="true"></i>
						  글 작성
						</button>
					</div>
				</div>

				
				<div class="panel-group forum-list" id="accordion" role="tablist" aria-multiselectable="true" data-toggle="tooltip">
					<span id="buffer-box"></span>
				</div>
				<center id="loading-bar">
				</center>
				
			</div>
              <!------------------------------------------------------------>
              <!------------------------------------------------------------>

		  <%@ include file="/WEB-INF/jsp/include/basic-js.jsp" %>
		  
			<%@ include file="/WEB-INF/jsp/forum/modal/forum-modal.jsp" %>
			<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
		  <script src="${pageContext.request.contextPath}/js/forum/forum.js"></script>

		</body>
</html>

