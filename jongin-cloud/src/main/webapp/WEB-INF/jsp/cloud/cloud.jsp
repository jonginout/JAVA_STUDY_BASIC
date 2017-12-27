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

	<!-- dynatree -->
	<link href="${pageContext.request.contextPath}/lib/dynatree-master/src/skin/ui.dynatree.css" rel="stylesheet" type="text/css" id="skinSheet">
	<script src="${pageContext.request.contextPath}/lib/dynatree-master/src/jquery.dynatree.js"></script>

	<!-- 코드미러 -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/codeMirror/codemirror.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/codeMirror/ambiance.css">
	<script src="${pageContext.request.contextPath}/lib/codeMirror/codemirror.js"></script>
	<script src="${pageContext.request.contextPath}/lib/codeMirror/javascript.js"></script>
	<script src="${pageContext.request.contextPath}/lib/codeMirror/active-line.js"></script>
	<script src="${pageContext.request.contextPath}/lib/codeMirror/matchbrackets.js"></script>
	
	<!-- pdf view -->
	<script src="${pageContext.request.contextPath}/lib/pdfobject/pdfobject.min.js"></script>
	
	<!-- 클라우드 CSS -->
	<link href="${pageContext.request.contextPath}/css/cloud/cloud.css" rel="stylesheet" type="text/css" id="skinSheet">
	

</head>
<body>

  	<%@ include file="/WEB-INF/jsp/include/header.jsp" %>

	<!-- 트리 로드 -->
	<script src="${pageContext.request.contextPath}/js/cloud/cloud.js"></script>
	
              <!------------------------------------------------------------>
              <!------------------------------------------------------------>
    				
                	<div class="container" id="cloud-content">
	    				<c:if test="${!empty user}">
							<div class="button-box">
								<button class="btn btn-default btn-md" id="reload">
									<i class="fa fa-refresh" aria-hidden="true"></i> 
									Reload
								</button>
								<button class="btn btn-default btn-md" id="newFolder">
									<i class="fa fa-folder-o" aria-hidden="true"></i>
									새 폴더
								</button>
								<button class="btn btn-default btn-md" id="newCode">
									<i class="fa fa-file-o" aria-hidden="true"></i>
									새 파일
								</button>			
								<button class="btn btn-default btn-md" data-toggle="modal" data-target="#addVolume">
									<i class="fa fa-hdd-o" aria-hidden="true"></i> 
									Add Volume
								</button>	
								<!-- <button class="btn btn-default btn-md" id="gear">
									<i class="fa fa-gear" aria-hidden="true"></i> 
									확장 프로그램
								</button> -->
								<button class="btn btn-default btn-md" id="gear" onclick="location.href='${pageContext.request.contextPath}/forum/forum.do'">
									<i class="fa fa-gear" aria-hidden="true"></i> 
									개발자 포럼
								</button>		
							</div>
							<form id="uploadForm" method="post" enctype="multipart/form-data">
								<input type="hidden" name="uploadPath" value=""/>
								<input type="file" name="files" multiple="multiple">
								<button type="button">업로드</button>
							</form>
							
							<div id="tree"></div>

							<div class="progress">
								<div class="progress-bar progress-bar-striped active progress-bar-success" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="${user.maxVolume/1024/1024}" style="width: 0%;">
									<span id="percent">0%</span>
								</div>
							</div>
					
							<div class="row detail-box">
								<div class="hidden-xs col-sm-4 file-list">
									<div id="nowList"></div>
								</div>
								<div class="col-xs-12 col-sm-8 file-detail">
									<div id="detailFile" data-key=""></div>
								</div>
							</div>
	    				</c:if>
					</div>
              <!------------------------------------------------------------>
              <!------------------------------------------------------------>

		  <%@ include file="/WEB-INF/jsp/include/basic-js.jsp" %>
		  
<!-- 클립보드 -->
<script src="${pageContext.request.contextPath}/lib/clipboard/clipboard.min.js"></script>
<!-- 클라우드 제어 -->
<script src="${pageContext.request.contextPath}/js/cloud/cloud-set.js"></script>
<!-- url 단축  -->
<script src="https://apis.google.com/js/client.js?onload=load"></script>
<!-- 모달 -->
<%@ include file="/WEB-INF/jsp/cloud/modal/cloud-modal.jsp" %>
</body>
</html>

