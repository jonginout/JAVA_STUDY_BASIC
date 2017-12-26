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
	<!-- 코드미러 -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/codeMirror/codemirror.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/codeMirror/ambiance.css">
	<script src="${pageContext.request.contextPath}/lib/codeMirror/codemirror.js"></script>
	<script src="${pageContext.request.contextPath}/lib/codeMirror/javascript.js"></script>
	<script src="${pageContext.request.contextPath}/lib/codeMirror/active-line.js"></script>
	<script src="${pageContext.request.contextPath}/lib/codeMirror/matchbrackets.js"></script>
	
	<!-- 클라우드 CSS -->
	<link href="${pageContext.request.contextPath}/css/forum/forum.css" rel="stylesheet" type="text/css" id="skinSheet">
	

</head>
<body>

  	<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
		  	<div id="forum-content" class="col-xs-12 col-sm-8 col-sm-push-2">
              <!------------------------------------------------------------>
              <!------------------------------------------------------------>
				<div class="container write-box"> 
					<textarea class="form-control" rows="2"></textarea>
				</div>	
				<button class="btn btn-block btn-xs write-btn">
					<i class="fa fa-pencil" aria-hidden="true"></i>
					글 작성
				</button>

				
				<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
					<c:forEach var="f" items="${forums}">
						<div class="panel panel-default" data-no="${f.forumNo}">
						  <div class="panel-heading">
							<h4 class="panel-title" data-toggle="collapse" data-parent="#accordion" href="#forum${f.forumNo}">
								<div class="profile">${f.category}</div>
								<div class="contentT">
									<span class="titleT">${f.title}</span>
									<span class="dateT"><span id="moment"></span> | 댓글 2 | <span id="author">${f.writerId}</span>
								</div>
							</h4>
						  </div>
						  <div id="forum${f.forumNo}" class="panel-collapse collapse">
							<div class="panel-body">
							  첫번째 글 입니당
							</div>
						  </div>
						</div>
					</c:forEach>
				  </div>
              <!------------------------------------------------------------>
              <!------------------------------------------------------------>
		  	</div>			

		  <%@ include file="/WEB-INF/jsp/include/basic-js.jsp" %>

<script type="text/javascript">
	$(function(){

		console.log("?")
		var start = 0;
		var count = 20;
		$.ajax({
			type : "POST",
			url : projectURL+"/forum/forum.json",
			data : {
				start : start,
				count : count
			},
			dataType : "JSON",
			success : function(data){
				var forums = JSON.parse(data);
				moment.locale('ko');

				var html = "";
				for (var f in forums) {
					forums[f].regDate = moment(forums[f].regDate).fromNow();
					forums[f].updateDate = moment(forums[f].updateDate).fromNow();
					html += '\
					<div class="panel panel-default" data-no="'+forums[f].forumNo+'">\
						<div class="panel-heading">\
						<h4 class="panel-title" data-toggle="collapse" data-parent="#accordion" href="#forum'+forums[f].forumNo+'">\
							<div class="category">'+forums[f].category+'</div>\
							<div class="contentT">\
								<span class="titleT">'+forums[f].title+'</span>\
								<span class="dateT"><span id="moment">'+forums[f].updateDate+'</span> | 댓글 2 | <span id="author">'+forums[f].writerId+'</span>\
							</div>\
						</h4>\
						</div>\
						<div id="forum'+forums[f].forumNo+'" class="panel-collapse collapse">\
						<div class="panel-body">\
								'+forums[f].content+'\
						</div>\
						</div>\
					</div>';
				}
				console.log(html)
			},
			error : function(){

			}
		})




	})


</script>

</body>
</html>

