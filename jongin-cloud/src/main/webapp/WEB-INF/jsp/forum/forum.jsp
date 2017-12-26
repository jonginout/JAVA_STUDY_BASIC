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

	// 기본 리스트 불러오기 및 무한스크롤 이벤트
	$(function(){
		loadForum(0,20)

		$(window).scroll(function() {
			
			if(!isLoading  && !lastFlag && $(window).scrollTop()+$(window).height()+100 >= $(document).height()) {
				isLoading = true;
				$("#loading-bar").show();
				loadForum(start,count);
			}else if (isLoading) {
				return;
			}
		})

	})


	// 20개씩 불러오는 에이젝스
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
								<span class="dateT"><span id="moment">'+forums[f].updateDate+'</span> | 댓글 '+forums[f].commentCount+' | <span id="author">'+forums[f].writerId+'</span>\
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
									<span><i class="fa fa-comment comment-i" aria-hidden="true"></i> '+forums[f].commentCount+'</span>\
									<span class="info-bar">|</span>\
									<span><i class="fa fa-heart like-i" aria-hidden="true"></i> <span class="like-count">'+forums[f].likeCount+'</span></span>\
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
								<center class="like-box">\
									<button class="btn btn-default btn-sm like-btn">\
										<i class="fa fa-heart like-i" aria-hidden="true"></i> 좋아요\
									</button>\
								</center>\
								<div class="input-group comment-input-box">\
									<textarea class="form-control" rows="2" style="resize:none"></textarea>\
									<span class="input-group-addon btn btn-primary">전송</span>\
								</div>\
								<div class="comment-box">\
									<center id="comment-loading-bar">\
										<img src="https://s3.ap-northeast-2.amazonaws.com/dankookie/static/loading.gif">\
									</center>\
									<span id="comments"><span>\
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
			}
		})

	}

	// 글 클릭하면 댓글 가져오는 이벤트
	$("body").on("click", "h4.panel-title", function(){
		var no = $(this).parents(".panel-default").attr("data-no")
		loadComment(no)
	})

	// 댓글 가져오는 함수
	function loadComment(no){
		$("#comment-loading-bar").show();		
		$(".comment-box>span").html("");

		$.ajax({
			type : "POST",
			url : projectURL+"/forum/comment.json",
			data : {
				forumNo : no
			},
			dataType : "JSON",
			success : function(data){

				var html = "";
				for (var c in data) {

					data[c].regDate = moment(data[c].regDate).fromNow();
					data[c].updateDate = moment(data[c].updateDate).fromNow();

					html += '<div class="comment-wrap">\
								<div class="photo">\
									<center>\
										<div class="avatar" style="background-image: url(\''+projectURL+'/img/forum/default_profile.png\')"></div>\
										<span>'+data[c].writerId+'</span>\
									</center>\
								</div>\
								<div class="comment-block">\
									<p class="comment-text">'+data[c].content+'</p>\
									<div class="bottom-comment">\
										<div class="comment-date">'+data[c].updateDate+'</div>\
										<ul class="comment-actions">\
											<li class="modify-comment">수정</li>\
											<li class="delete-comment">삭제</li>\
										</ul>\
									</div>\
								</div>\
							</div>'

				}

				$(".comment-box>span").html(html);
				$("#comment-loading-bar").hide();
				
			}
		})
	}

	// 좋아요 추가 이벤트
	$("body").on("click", ".like-btn", function(){
		var no = $(this).parents(".panel-default").attr("data-no")
		addLike(no);
	})
	
	// 좋아요 추가 함수
	function addLike(no){
		var parent = $(".panel-default[data-no="+no+"]");

		parent.find(".like-btn").css({
			"background": "tomato",
			"color": "white"
		})
		parent.find(".like-i").css({ 
			"color": "white"
		})
		$.ajax({
			type : "POST",
			url : projectURL+"/forum/addlike.json",
			data : {
				forumNo : no,
				liker : "${user.memberNo}"
			},
			dataType : "JSON",
			success : function(data){
				if(data.result){
					var nowLikeCnt = parseInt(parent.find(".like-count").text().trim())
					parent.find(".like-count").text(nowLikeCnt+1)
				}else{
					alert("이미 좋아요를 누른 '포럼'입니다.")
				}
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

