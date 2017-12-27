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
					<textarea class="form-control write-forum-input" rows="2"></textarea>
				</div>	

				<div class="btn-group btn-group-justified " aria-label="...">
					<div class="btn-group btn-xs write-btn write-btn-group">
					  <button type="button" class="btn write-btn file-forum">
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

	// 활성화된 포럼의 부모 엘리먼트
	function parent(no){
		return $(".panel-default[data-no="+no+"]");
	}

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

	// 글 추가 
	$("body").on("click",".write-forum", function(){
		var content = $(".write-forum-input").val().trim();
		var title = content.substring(0,30);
		var category = '자유';
		$.ajax({
			type : "POST",
			url : projectURL+"/forum/addforum.json",
			data : {
				title : title,
				content : content,
				category : category
			},
			success : function(data){
				var html = '\
					<div class="panel panel-default" data-no="'+data+'" data-comment="false" style="display: none">\
						<div class="panel-heading">\
						<h4 class="panel-title" data-toggle="collapse" data-parent="#accordion" href="#forum'+data+'">\
							<div class="category">'+category+'</div>\
							<div class="contentT">\
								<span class="titleT">'+title+'</span>\
								<span class="dateT"><span id="moment"> 지금</span> | 댓글 0 | <span id="author">'+user.id+'</span>\
							</div>\
						</h4>\
						</div>\
						<div id="forum'+data+'" class="panel-collapse collapse">\
							<div class="panel-body">\
								<h5 class="info-box">\
									<span class="info-bar">|</span>\
									<span>'+category+'</span>\
									<span class="info-bar">|</span>\
									<span><i class="fa fa-calculator" aria-hidden="true"></i> 지금</span>\
									<span class="info-bar">|</span>\
									<span><i class="fa fa-comment comment-i" aria-hidden="true"></i> 0</span>\
									<span class="info-bar">|</span>\
									<span><i class="fa fa-heart like-i" aria-hidden="true"></i> <span class="like-count">0</span></span>\
									<span class="info-bar">|</span>\
									<span><i class="fa fa-eye" aria-hidden="true"></i> </i><span class="view-count">0</span></span>\
								</h5>\
								<h5 class="writer-box" style="margin:0; margin-bottom: 10px; font-weight: normal;">\
									<i class="fa fa-user-o" aria-hidden="true"></i>\
									<div class="writer">\
										<span>'+user.id+'</span>\
									</div>\
									<div class="button-box pull-right">\
									</div>\
								</h5>\
								<div class="content-box">\
									<span>'+content+'</span>\
									<span></span>\
								</div>\
								<center class="like-box">\
									<button class="btn btn-default btn-sm like-btn">\
										<i class="fa fa-heart like-i" aria-hidden="true"></i> 좋아요\
									</button>\
								</center>\
								<div class="input-group comment-input-box">\
									<textarea class="form-control comment-content" rows="2" style="resize:none"></textarea>\
									<span class="input-group-addon btn btn-primary comment-btn">전송</span>\
								</div>\
								<div class="comment-box">\
									<center class="comment-loading-bar">\
										<img src="https://s3.ap-northeast-2.amazonaws.com/dankookie/static/loading.gif">\
									</center>\
									<span class="comments"><span>\
								</div>\
							</div>\
						</div>\
					</div>';
					$(".write-forum-input").val("");
					$("#buffer-box").prepend(html);
					$("#buffer-box>.panel-default[data-no="+data+"]").show(300);
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

				for (var f in forums) {
					forums[f].regDate = moment(forums[f].regDate).fromNow();
					forums[f].updateDate = moment(forums[f].updateDate).fromNow();
					html = '\
					<div class="panel panel-default" data-no="'+forums[f].forumNo+'" data-comment="false">\
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
									<span><i class="fa fa-eye" aria-hidden="true"></i> </i><span class="view-count">'+forums[f].view+'</span></span>\
								</h5>\
								<h5 class="writer-box" style="margin:0; margin-bottom: 10px; font-weight: normal;">\
									<i class="fa fa-user-o" aria-hidden="true"></i>\
									<div class="writer">\
										<span>'+forums[f].writerId+'</span>\
									</div>\
									<div class="button-box pull-right">\
									</div>\
								</h5>\
								<div class="content-box">\
									<span>'+forums[f].content+'</span>\
									<span></span>\
								</div>\
								<center class="like-box">\
									<button class="btn btn-default btn-sm like-btn">\
										<i class="fa fa-heart like-i" aria-hidden="true"></i> 좋아요\
									</button>\
								</center>\
								<div class="input-group comment-input-box">\
									<textarea class="form-control comment-content" rows="2" style="resize:none"></textarea>\
									<span class="input-group-addon btn btn-primary comment-btn">전송</span>\
								</div>\
								<div class="comment-box">\
									<center class="comment-loading-bar">\
										<img src="https://s3.ap-northeast-2.amazonaws.com/dankookie/static/loading.gif">\
									</center>\
									<span class="comments"><span>\
								</div>\
							</div>\
						</div>\
					</div>';
					$(".forum-list").append(html)
					// 작성자만 글삭제 수정버튼
					if(user.memberNo==forums[f].writer){
						var btnHtml = ` <button class="btn btn-default btn-xs forum-delete">\
											삭제\
										</button>\
										<button class="btn btn-default btn-xs forum-edit">\
											수정\
										</button>
										<button class="btn btn-default btn-xs forum-edit-submit">\
											수정완료!\
										</button>`;
						parent(forums[f].forumNo).find(".button-box").html(btnHtml)
					}
					// console.log(html)
				}
				$("#loading-bar").hide();
				start = start+count;
				console.log(start)
				isLoading = false;
			}
		})

	}

	// 포럼 수정 삭제
	$("body").on("click", ".forum-delete,.forum-edit", function(){
		var no = $(this).parents(".panel-default").attr("data-no")
		if($(this).hasClass("forum-delete")){
			if(confirm("정말로 삭제하시겠습니까?")){
				deleteForum(no)
				return
			}
		}else if($(this).hasClass("forum-edit")){
			parent(no).find(".forum-edit-submit").show();
			
			$(this).text("수정취소").addClass("cancel-edit");
			var content = parent(no).find(".content-box>span:eq(0)").text().trim();
			parent(no).find(".content-box>span:eq(0)").hide();
			var html = '<textarea class="form-control forum-edit-input" style="resize:none;">'+content+'</textarea>';
			parent(no).find(".content-box>span:eq(1)").html(html);
		}
		
	})

	// 수정 취소 버튼
	$("body").on("click", ".cancel-edit", function(){
		cancelForumEidt(this);
	})

	// 수정취소 함수
	function cancelForumEidt(thisE){
		var no = $(thisE).parents(".panel-default").attr("data-no");
		parent(no).find(".forum-edit-submit").hide();

		parent(no).find(".cancel-edit").text("수정").removeClass("cancel-edit");
		parent(no).find(".content-box>span:eq(0)").show();
		parent(no).find(".content-box>span:eq(1)").html("");
	}

	// 포럼 수정 버튼
	$("body").on("click", ".forum-edit-submit", function(){
		var no = $(this).parents(".panel-default").attr("data-no");
		editForum(no, this)
	})

	// 포럼 수정 함수
	function editForum(no, thisE){
		var content = parent(no).find(".forum-edit-input").val();

		$.ajax({
				type : "POST",
				url : projectURL+"/forum/editforum.json",
				data : {
					forumNo : no,
					content : content
				},
				success : function(data){
					cancelForumEidt(thisE);
					if(data.result){
						parent(no).find(".content-box>span:eq(0)").html(content)
					}else{
						alert("수정 불가능!")
					}
				}
			})
	}

	//포럼 삭제 함수
	function deleteForum(no){
		//세션체크 서버서도 한번
		$.ajax({
				type : "POST",
				url : projectURL+"/forum/deleteforum.json",
				data : {
					forumNo : no
				},
				success : function(data){
					if(data.result){
						parent(no).fadeOut(300, function(){ parent(no).remove();});
					}else{
						alert("삭제 불가능!")
					}
				}
			})
	}

	// 글 클릭하면 댓글 가져오는 이벤트
	$("body").on("click", "h4.panel-title", function(){
		var no = $(this).parents(".panel-default").attr("data-no")
		addView(no)
		var loadCommentFlag = $(this).parents(".panel-default").attr("data-comment")
		if(loadCommentFlag=="false"){
			loadComment(no)
		}
	})

	//조회수 상승
	function addView(no){
		if(!parent(no).find(".panel-collapse").hasClass("in")){
			$.ajax({
				type : "GET",
				url : projectURL+"/forum/addview.json",
				data : {
					forumNo : no
				},
				success : function(){
					var nowViewCnt = parseInt(parent(no).find(".view-count").text().trim())
					parent(no).find(".view-count").text(nowViewCnt+1)
				}
			})
		}
	}

	// 댓글 가져오는 함수
	function loadComment(no){
		parent(no).find(".comment-loading-bar").show();
		parent(no).find(".comment-box>span").html("");

		$.ajax({
			type : "GET",
			url : projectURL+"/forum/comment.json",
			data : {
				forumNo : no
			},
			dataType : "JSON",
			success : function(data){

				for (var c in data) {

					data[c].regDate = moment(data[c].regDate).fromNow();
					data[c].updateDate = moment(data[c].updateDate).fromNow();

					var html = '<div class="comment-wrap" data-commentNo="'+data[c].commentNo+'">\
									<div class="photo">\
										<center>\
											<div class="avatar" style="background-image: url(\''+projectURL+'/img/forum/default_profile.png\')"></div>\
											<span>'+data[c].writerId+'</span>\
										</center>\
									</div>\
									<div class="comment-block">\
										<p class="comment-text">\
											<span>'+data[c].content+'</span>\
											<span></span>\
										</p>\
										<div class="bottom-comment">\
											<div class="comment-date">'+data[c].updateDate+'</div>\
											<ul class="comment-actions">\
											</ul>\
										</div>\
									</div>\
								</div>';
					parent(no).find(".comment-box>.comments").append(html);
					if(user.memberNo==data[c].writer){
						var btnHtml = `<li class="modify-comment">수정</li>\
										<li class="modify-comment-submit">수정완료</li>\
										<li class="delete-comment">삭제</li>`;
						$(".comment-wrap[data-commentNo="+data[c].commentNo+"]").find(".comment-actions").html(btnHtml)
					}
				}
 
				parent(no).find(".comment-loading-bar").hide();
				parent(no).attr("data-comment", "true")
			}
		})
	}

	// 댓글 삭제 / 수정 이벤트
	$("body").on("click", ".delete-comment,.modify-comment", function(){
		var commentParent = $(this).parents(".comment-wrap");
		var commentNo = commentParent.attr("data-commentNo")

		if($(this).hasClass("delete-comment")){
			if(confirm("정말로 삭제하시겠습니까?")){
				deleteComment(commentNo, commentParent)
				return
			}
		}else if($(this).hasClass("modify-comment")){
			commentParent.find(".modify-comment-submit").css({"display":"inline"});
			
			$(this).text("수정취소").addClass("cancel-edit-comment");
			var content = commentParent.find(".comment-text>span:eq(0)").text().trim();
			commentParent.find(".comment-text>span:eq(0)").hide();
			var html = '<textarea class="form-control comment-edit-input" style="resize:none;">'+content+'</textarea>';
			commentParent.find(".comment-text>span:eq(1)").html(html);
		}
	})

	// 댓글 수정 취소
	function cancelCommentEidt(thisE){
		var commentParent = $(thisE).parents(".comment-wrap");
		commentParent.find(".modify-comment-submit").hide();

		commentParent.find(".cancel-edit-comment").text("수정").removeClass("cancel-edit-comment");
		commentParent.find(".comment-text>span:eq(0)").show();
		commentParent.find(".comment-text>span:eq(1)").html("");
	}
	
	// 댓글 수정 취소 이벤트
	$("body").on("click", ".cancel-edit-comment", function(){
		cancelCommentEidt(this)
	})

	// 댓글 수정 이벤트
	$("body").on("click", ".modify-comment-submit", function(){
		var commentNo = $(this).parents(".comment-wrap").attr("data-commentNo");
		editComment(commentNo, this)
	})

	// 댓글 수정 함수
	function editComment(commentNo, thisE){
		var content = $(".comment-wrap[data-commentNo="+commentNo+"]").find(".comment-edit-input").val();
		$.ajax({
			type : "POST",
			url : projectURL+"/forum/editcomment.json",
			data : {
				commentNo : commentNo,
				content : content
			},
			success : function(data){
				cancelCommentEidt(thisE);
				if(data.result){
					$(".comment-wrap[data-commentNo="+commentNo+"]").find(".comment-text>span:eq(0)").html(content)
				}else{
					alert("수정 불가능!")
				}
			}
		})
	}

	// 댓글 삭제 함수
	function deleteComment(commentNo, commentParent){
			//세션체크 서버서도 한번
		$.ajax({
			type : "POST",
			url : projectURL+"/forum/deletecomment.json",
			data : {
				commentNo : commentNo,
			},
			success : function(data){
				if(data.result){
					commentParent.fadeOut(300, function(){ commentParent.remove();});
				}else{
					alert("삭제 불가능!")
				}
			}
		})
	}

	// 댓글 추가 이벤트
	$("body").on("click", ".comment-btn", function(){
		if($(this).prev().val().length<1){
			alert("댓글을 입력하세요.")
			return;
		}
		var no = $(this).parents(".panel-default").attr("data-no")
		addComment(no);	
	})

	// 댓글 추가 함수
	function addComment(no){
		var content = parent(no).find(".comment-content").val();

		$.ajax({
			type : "POST",
			url : projectURL+"/forum/addcommetn.json",
			data : {
				forumNo : no,
				writerId : user.id,
				writer : user.memberNo,
				content : content
			},
			success : function(newCommentNo){
				var html = '<div class="comment-wrap" data-commentNo="'+newCommentNo+'">\
						<div class="photo">\
							<center>\
								<div class="avatar" style="background-image: url(\''+projectURL+'/img/forum/default_profile.png\')"></div>\
								<span>'+user.id+'</span>\
							</center>\
						</div>\
						<div class="comment-block">\
							<p class="comment-text">\
								<span>'+content+'</span>\
								<span></span>\
							</p>\
							<div class="bottom-comment">\
								<div class="comment-date">지금</div>\
								<ul class="comment-actions">\
									<li class="modify-comment">수정</li>\
									<li class="modify-comment-submit">수정완료</li>\
									<li class="delete-comment">삭제</li>\
								</ul>\
							</div>\
						</div>\
					</div>';
				parent(no).find(".comments").append(html);
				$(".comment-content").val("");
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

		parent(no).find(".like-btn").css({
			"background": "tomato",
			"color": "white"
		})
		parent(no).find(".like-btn>.like-i").css({ 
			"color": "white"
		})
		$.ajax({
			type : "POST",
			url : projectURL+"/forum/addlike.json",
			data : {
				forumNo : no,
				liker : user.memberNo
			},
			dataType : "JSON",
			success : function(data){
				if(data.result){
					var nowLikeCnt = parseInt(parent(no).find(".like-count").text().trim())
					parent(no).find(".like-count").text(nowLikeCnt+1)
				}else{
					alert("이미 좋아요를 누른 '포럼'입니다.")
				}
			}
		})
	}

	// 자동 코멘트 높이 조정
	$('body').on('keyup', '.comment-input-box>textarea, .forum-edit-input', '.comment-edit-input' , function () {
		$(this).height(1).height( $(this).prop('scrollHeight')+8 );				
	});

</script>

</body>
</html>

