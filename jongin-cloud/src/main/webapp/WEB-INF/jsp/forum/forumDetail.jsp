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

				<div class="btn-group btn-group-justified " aria-label="...">
					<div class="btn-group btn-xs write-btn write-btn-group">
						 <a type="button" class="btn write-btn cloud-btn" href="${pageContext.request.contextPath}/cloud/cloud.do">
							<i class="fa fa-cloud-download" aria-hidden="true"></i> 
							클라우드
						 </a>
					</div>
					<div class="btn-group btn-xs write-btn write-btn-group">
						<a type="button" class="btn write-btn forum-btn" href="${pageContext.request.contextPath}/forum/forum.do">
						  <i class="fa fa-list-ul" aria-hidden="true"></i>
							전체글 보기 
						</a>
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

// 글 추가 
$(function(){
	
		var forumNo = parseInt("${param.forumNo}")
		var commentNo = parseInt("${param.commentNo}")

		console.log(forumNo, commentNo)

		$.ajax({
			type : "GET",
			url : projectURL+"/alarm/detailalarm.json",
			data : {
				forumNo : forumNo,
			},
			dataType : "JSON",
			success : function(data){

				var html = '\
					<div class="panel panel-default" data-no="'+data.forum.forumNo+'" data-comment="false">\
						<div class="panel-heading">\
						<h4 class="panel-title" data-toggle="collapse" data-parent="#accordion" href="#forum'+data.forum.forumNo+'">\
							<div class="category">'+data.forum.category+'</div>\
							<div class="contentT">\
								<span class="titleT">'+data.forum.title+'</span>\
								<span class="dateT"><span id="moment"> 지금</span> | 댓글 0 | <span id="author">'+data.forum.writer+'</span>\
							</div>\
						</h4>\
						</div>\
						<div id="forum'+data.forum.forumNo+'" class="panel-collapse collapse in">\
							<div class="panel-body">\
								<h5 class="info-box">\
									<span class="info-bar">|</span>\
									<span>'+data.forum.category+'</span>\
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
										<span>'+data.forum.writer+'</span>\
									</div>\
									<div class="button-box pull-right">\
									</div>\
								</h5>\
								<div class="content-box">\
									<span>'+data.forum.content+'</span>\
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
									<span class="comments"><span>\
								</div>\
							</div>\
						</div>\
					</div>';
					$("#buffer-box").prepend(html);

					///////

					var comments = data.comments; 

					for (var c in comments) {

						comments[c].regDate = moment(comments[c].regDate).fromNow();
						comments[c].updateDate = moment(comments[c].updateDate).fromNow();
	
						// profile URL
						var avatarURL = "";
						if(!comments[c].profileImg){
							avatarURL = projectURL+'/common/down.do?ext=img&path=data/profile/default_profile.png';
						}else{
							if(comments[c].type=='NORMAL'){
								avatarURL = projectURL+'/common/down.do?ext=img&path='+comments[c].profileImg;
							}else{
								avatarURL = comments[c].profileImg;
							}
						}
	
						var html = '<div class="comment-wrap" data-commentNo="'+comments[c].commentNo+'">\
										<div class="photo">\
											<center>\
												<div class="avatar" style="background-image: url(\''+avatarURL+'\')"></div>\
												<span>'+comments[c].writerId+'</span>\
											</center>\
										</div>\
										<div class="comment-block">\
											<p class="comment-text">\
												<span>'+comments[c].content+'</span>\
												<span></span>\
											</p>\
											<div class="bottom-comment">\
												<div class="comment-date">'+comments[c].updateDate+'</div>\
												<ul class="comment-actions">\
												</ul>\
											</div>\
										</div>\
									</div>';
						$(".panel-default").find(".comment-box>.comments").append(html);
						if(user.memberNo==comments[c].writer){
							var btnHtml = `<li class="modify-comment">수정</li>\
											<li class="modify-comment-submit">수정완료</li>\
											<li class="delete-comment">삭제</li>`;
							$(".comment-wrap[data-commentNo="+comments[c].commentNo+"]").find(".comment-actions").html(btnHtml)
	
							$(".comment-wrap[data-commentNo="+comments[c].commentNo+"]").find(".photo").remove();
							var photoHtml ='<div class="photo">\
												<center>\
													<div class="avatar" style="background-image: url(\''+avatarURL+'\')"></div>\
													<span>'+comments[c].writerId+'</span>\
												</center>\
											</div>';
							$(".comment-wrap[data-commentNo="+comments[c].commentNo+"]").append(photoHtml);
						}
					}
			}
		})
	})
</script>

</body>
</html>

