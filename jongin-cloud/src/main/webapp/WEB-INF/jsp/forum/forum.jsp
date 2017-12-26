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

				
				<div class="panel-group forum-list" id="accordion" role="tablist" aria-multiselectable="true" data-toggle="tooltip">
				</div>
				<center id="loading-bar"> 
					<img src="https://s3.ap-northeast-2.amazonaws.com/dankookie/static/loading.gif" alt="">
				</center>
				
              <!------------------------------------------------------------>
              <!------------------------------------------------------------>
		  	</div>			

		  <%@ include file="/WEB-INF/jsp/include/basic-js.jsp" %>

<script type="text/javascript">

	var start = 0;
	var count = 20;
	var lastFlag = false;
	var isLoading = false;

	$(function(){
		loadForum(start,count)

		$(window).scroll(function() {
			console.log(isLoading)
			if(!isLoading  && !lastFlag && $(window).scrollTop()+$(window).height()+100 >= $(document).height()) {
				isLoading = true;
				$("#loading-bar").show();
				loadForum(start,count);
			}else if (isLoading) {
				return;
			}
		})

	})


	function loadForum(st, co){

		$.ajax({
			type : "POST",
			url : projectURL+"/forum/forum.json",
			data : {
				start : st,
				count : co
			},
			dataType : "JSON",
			success : function(data){
				
				if(!data.result){
					lastFlag = true;
					$("#loading-bar").hide();
					var html = `
					<center>
						<div class="alert alert-danger no-data" role="alert">
							<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
							<span class="sr-only">Error:</span>
							더이상 로드할 포럼이 없습니다.
						</div>
					</center>
					`;
					$(".forum-list").append(html)
					console.log("끝!")
					return;
				}

				var forums = JSON.parse(data.result);
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
								<h5 class="info-box">\
									<span class="info-bar">|</span>\
									<span>'+forums[f].category+'</span>\
									<span class="info-bar">|</span>\
									<span><i class="fa fa-calculator" aria-hidden="true"></i> '+forums[f].updateDate+'</span>\
									<span class="info-bar">|</span>\
									<span><i class="fa fa-comment-o" aria-hidden="true"></i> 2</span>\
									<span class="info-bar">|</span>\
									<span><i class="fa fa-heart-o" aria-hidden="true"></i> <span id="vote_length">0</span></span>\
									<span class="info-bar">|</span>\
									<span><i class="fa fa-eye" aria-hidden="true"></i> </i>'+forums[f].view+'</span>\
								</h5>\
								<h5 class="writer-box" style="margin:0; margin-bottom: 10px; font-weight: normal;">\
									<i class="fa fa-user-o" aria-hidden="true"></i>\
									<div class="writer">\
										<span>'+forums[f].writerId+'</span>\
									</div>\
									<div class="button-box pull-right">\
										<button class="btn btn-default btn-xs">\
											삭제\
										</button>\
										<button class="btn btn-default btn-xs">\
											수정\
										</button>\
									</div>\
								</h5>\
								<div class="content-box">\
									'+forums[f].content+'\
								</div>\
								<div class="input-group comment-input-box">\
									<textarea class="form-control" rows="2" style="resize:none"></textarea>\
									<span class="input-group-addon btn btn-primary">전송</span>\
								</div>\
								<div class="comment-box">\
									<div class="comment-content" style="backgroud:gray">\
										<div class="comment-img"></div>\
										<div class="contentT" style="left:64px;">\
											<span class="titleT">dadasds</span>\
											<span class="dateT"><span id="moment">3시간 전</span> | <span id="author">cv613</span> | <a id="author">수정</a> | <a id="author">삭제</a></span>\
										</div>\
									</div>\
									<div class="comment-content">\
										<div class="comment-img"></div>\
										<div class="contentT" style="left:64px;">\
											<span class="titleT">dadasds</span>\
											<span class="dateT"><span id="moment">3시간 전</span> | <span id="author">cv613</span> | <a id="author">수정</a> | <a id="author">삭제</a></span>\
										</div>\
									</div>\
								</div>\
							</div>\
						</div>\
					</div>';
				}
				$(".forum-list").append(html)
				$("#loading-bar").hide();
				// console.log(html)
				start = start+count;
				console.log(start)
				isLoading = false;
			},
			error : function(){
				alert("서버에러 개발자에게 문의하세요!")
			}
		})

	}

	// 자동 코멘트 높이 조정
	$('body').on('keyup', '.comment-input-box>textarea' , function () {
		$(this).height(1).height( $(this).prop('scrollHeight')+8 );				
	});
</script>

</body>
</html>

