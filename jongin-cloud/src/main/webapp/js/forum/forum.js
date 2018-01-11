
	var start = 0;
	var count = 20;
	var lastFlag = false;
	var isLoading = false;

	var param = {
		forumNo : null,
		commentNo : null
	};

	// 알람 파라미터
	if(location.search && location.search.indexOf("&")!=-1){
		param = ((location.search).substring(1)).split("&");
		param = {
			forumNo : parseInt(param[0].split("=")[1]),
			commentNo : parseInt(param[1].split("=")[1])
		}
	}else{
		param = ((location.search).substring(1)).split(":");
		param = {
			forumNo : parseInt(param[0].split("=")[1])
		}
	}

	var forumNo = parseInt("${param.forumNo}")
	var commentNo = parseInt("${param.commentNo}")

	// 활성화된 포럼의 부모 엘리먼트
	function parent(no){
		return $(".panel-default[data-no="+no+"]");
	}

	// 기본 리스트 불러오기 및 무한스크롤 이벤트
	$(function(){
		
		loadForum(0,20, true)

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
		$("#select-category").modal("show");
	})

	// 자동 높이 조절
	$("body").on('keydown keyup click', '.write-forum-input,.forum-edit-input,.comment-edit-input', function () {
		$(this).height(1).height( $(this).prop('scrollHeight')+7 );
	});
		

	$("body").on("click","#post-forum", function(){

		var content = $(".write-forum-input").val().trim();

		if(content.length<3){
			swal("Error", "포럼을 3글자 이상 입력하세요.", "error")
			$("#select-category").modal("hide");
			$(".write-forum-input").focus();
			return;
        }

        var title = content.substring(0,30);
        var category = $("#categorys").val();
        
        var data = {}
        if(attachCloudFile){
            data = {
                title : title,
                content : content,
                category : category,
                fileName : attachCloudFile.title,
                path : attachCloudFile.path,
                ext : attachCloudFile.ext
            }
        }else{
            data = {
                title : title,
                content : content,
                category : category,
            }
		}
		$("#select-category").modal("hide");
		
		$.ajax({
			type : "POST",
			url : projectURL+"/forum/addforum.json",
			data : data,
			success : function(data){

				var html = '\
					<div class="panel panel-default" data-no="'+data+'" style="display: none">\
						<div class="panel-heading">\
						<h4 class="panel-title" data-toggle="collapse" data-parent="#accordion" href="#forum'+data+'">\
							<div class="category" data-category="'+category+'">'+category+'</div>\
							<div class="contentT">\
								<span class="titleT">'+title+'</span>\
								<span class="dateT"><span id="moment"> 지금</span> | 댓글 <span class="comment-count">0</span> | <span id="author">'+user.id+'</span>\
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
									<span><i class="fa fa-comment comment-i" aria-hidden="true"></i> <span class="comment-count">0</sapn></span>\
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
										<button class="btn btn-default btn-xs forum-delete">\
											삭제\
										</button>\
										<button class="btn btn-default btn-xs forum-edit">\
											수정\
										</button>\
										<button class="btn btn-default btn-xs forum-edit-submit">\
											수정완료!\
										</button>\
									</div>\
								</h5>\
								<div class="content-box">\
									<span>'+content+'</span>\
									<span></span>\
									<span></span>\
                                </div>\
                                <div class="file-box pull-right">\
                                </div><br><br>\
								<center class="like-box">\
									<button class="btn btn-default btn-sm like-btn">\
										<i class="fa fa-heart like-i" aria-hidden="true"></i> 좋아요\
									</button>\
									<button class="btn btn-default btn-sm kakao-share-btn">\
										<i class="fa fa-share-alt" aria-hidden="true"></i> 카톡 공유\
									</button>\
								</center>\
								<div class="input-group comment-input-box">\
									<textarea class="form-control comment-content" rows="2"></textarea>\
									<span class="input-group-addon btn btn-primary comment-btn">전송</span>\
								</div>\
								<div class="comment-box">\
									<center class="comment-loading-bar">\
									</center>\
									<span class="comments"><span>\
								</div>\
							</div>\
						</div>\
					</div>';
					$(".write-forum-input").val("");
					$("#buffer-box").prepend(html);
                    $("#buffer-box>.panel-default[data-no="+data+"]").show(300);
                    
					attachCloudFile = null;
					$("#attach-file").html("")
			}
		})
	})

	// 20개씩 불러오는 에이젝스
	function loadForum(st, co, init){

		if(init){
			loadingAjax("화면 구성중...")
		}

		$(window).scrollTop($(document).height()); 
		
		// loadingAjax(text, ele)/loadingStopAjax(ele)
		loadingAjax(null, $("#loading-bar"))

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

					if(param.forumNo==forums[f].forumNo){
						continue;
						// 알람 때문에 최상으로 갔으면 스킵
					}
					var adminStyle = "";
					if(forums[f].writerStatus=='ADMIN'){
						forums[f].writerId = forums[f].writerId+" [운영자]"
						adminStyle = "style=\"background:#f8f9ee !important\"";
					}

					forums[f].regDate = moment(forums[f].regDate).fromNow();
					forums[f].updateDate = moment(forums[f].updateDate).fromNow();
					html = '\
					<div class="panel panel-default" data-no="'+forums[f].forumNo+'">\
						<div class="panel-heading" '+adminStyle+'>\
						<h4 class="panel-title" data-toggle="collapse" data-parent="#accordion" href="#forum'+forums[f].forumNo+'">\
							<div class="category" data-category="'+forums[f].category+'">'+forums[f].category+'</div>\
							<div class="contentT">\
								<span class="titleT">'+forums[f].title+'</span>\
								<span class="dateT"><span id="moment">'+forums[f].updateDate+'</span> | 댓글 <span class="comment-count">'+forums[f].commentCount+'</span> | <span id="author">'+forums[f].writerId+'</span>\
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
									<span><i class="fa fa-comment comment-i" aria-hidden="true"></i> <span class="comment-count">'+forums[f].commentCount+'</span></span>\
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
									<span></span>\
                                </div>\
                                <div class="file-box pull-right">\
                                </div><br><br>\
								<center class="like-box">\
									<button class="btn btn-default btn-sm like-btn">\
										<i class="fa fa-heart like-i" aria-hidden="true"></i> 좋아요\
									</button>\
									<button class="btn btn-default btn-sm kakao-share-btn">\
										<i class="fa fa-share-alt" aria-hidden="true"></i> 카톡 공유\
									</button>\
								</center>\
								<div class="input-group comment-input-box">\
									<textarea class="form-control comment-content" rows="2"></textarea>\
									<span class="input-group-addon btn btn-primary comment-btn">전송</span>\
								</div>\
								<div class="comment-box">\
									<center class="comment-loading-bar">\
									</center>\
									<span class="comments"><span>\
								</div>\
							</div>\
						</div>\
					</div>';
					$(".forum-list").append(html)
					// 작성자만 글삭제 수정버튼
					if(user.memberNo==forums[f].writer || user.status == 'ADMIN'){
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
				isLoading = false;

				loadingStopAjax($("#loading-bar"))
				if(init){
					loadingStopAjax()
				}	
			},
			error : function(){
				loadingStopAjax($("#loading-bar"))	
				if(init){
					loadingStopAjax()
				}								
			}
		})

	}

	// 포럼 수정 삭제
	$("body").on("click", ".forum-delete,.forum-edit", function(){
		var no = $(this).parents(".panel-default").attr("data-no")
		if($(this).hasClass("forum-delete")){
			swal({
				title: "Delete",
				text: "정말로 삭제하시겠습니까?",
				type: "warning",
				showCancelButton: true,
				closeOnConfirm: false
			  },
			  function(isConfirm) {
				if (isConfirm) {
					deleteForum(no)
					swal.close()
				}else{
					return false;
				}
			  });	
			// if(confirm("정말로 삭제하시겠습니까?")){
			// 	deleteForum(no)
			// 	return
			// }
		}else if($(this).hasClass("forum-edit")){
			parent(no).find(".forum-edit-submit").show();
			
			$(this).text("수정취소").addClass("cancel-edit");
			var content = parent(no).find(".content-box>span:eq(0)").text().trim();
			parent(no).find(".content-box>span:eq(0)").hide();
			var html = '<textarea class="form-control forum-edit-input">'+content+'</textarea>';
			parent(no).find(".content-box>span:eq(2)").html(html);
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
		parent(no).find(".content-box>span:eq(2)").html("");
	}

	// 포럼 수정 버튼
	$("body").on("click", ".forum-edit-submit", function(){
		var no = $(this).parents(".panel-default").attr("data-no");
		editForum(no, this)
	})

	// 포럼 수정 함수
	function editForum(no, thisE){
		var content = parent(no).find(".forum-edit-input").val();
		var title = content.substring(0,30);

		$.ajax({
				type : "POST",
				url : projectURL+"/forum/editforum.json",
				data : {
					forumNo : no,
					title : title,
					content : content
				},
				success : function(data){
					cancelForumEidt(thisE);
					if(data.result){
						parent(no).find(".titleT").html(title)						
						parent(no).find(".content-box>span:eq(0)").html(content)
					}else{
						swal("Error", "수정 불가!", "error")
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
						swal("Error", "삭제 불가!", "error")
					}
				}
			})
	}

	// 글 클릭하면 댓글 가져오는 이벤트
	$("body").on("click", "h4.panel-title", function(){

		var heading = $(this).parents(".panel-default");
		var no = heading.attr("data-no")
		addView(no)

		if(!heading.find(".panel-collapse").hasClass("in")){
            loadForumFile(no);
			loadComment(no)
		}

		// if($("#forum-content-detail.hidden-xs").css("display")!="none"){
		// }
	})

	// 파일 불러오기
    function loadForumFile(no){
        $.ajax({
            type : "GET",
            url : projectURL+"/forum/forumfile.json",
            data : {
                forumNo : no
            },
            success : function(data){
                if(data.result){
                    var files = data.forumFiles;
                    var viewHtml = "";
                    var downHtml = "";
                    for(var f in files){

                        var path = (files[f].path).replace(/\\/gi, "/");
                        var viewUrl = projectURL+"/common/down.do?ext="+files[f].ext+"&path="+path;
						var downUrl = viewUrl+"&title="+files[f].fileName;
						
						// 이미지 뷰 사용가능 확장자
						var imgFileArr = ["png","jpg","jpeg","gif","png","bmp"];
						if(imgFileArr.indexOf((files[f].ext).toLowerCase())!=-1){
							viewHtml += '<br><br><img src="'+viewUrl+'" width="100%"/>'
						}

                        downHtml += '<a href="'+downUrl+'">'+files[f].fileName+'</a>';

					}
                    parent(no).find(".content-box>span:eq(1)").html(viewHtml)
                    parent(no).find(".file-box").html(downHtml)
                }
                
            }
        })
    }

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
		loadingAjax(null, parent(no).find(".comment-loading-bar"))

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

					// profile URL
					var avatarURL = "";
					if(!data[c].profileImg){
						avatarURL = projectURL+'/common/down.do?ext=img&path=data/profile/default_profile.png';
					}else{
						if(data[c].type=='NORMAL'){
							avatarURL = projectURL+'/common/down.do?ext=img&path='+data[c].profileImg;
						}else{
							avatarURL = data[c].profileImg;
						}
					}

					var owner = ""
					var adminStyle = ""
					if(data[c].writer==data[c].forumWriter){			
						owner = "[글쓴이]"			
					}
					if(data[c].status == 'ADMIN'){
						owner += " [운영자]"
						adminStyle = "style=\"background:#f8f9ee !important\"";
					}

					var html = '<div class="comment-wrap" data-commentNo="'+data[c].commentNo+'">\
									<div class="photo">\
										<center>\
											<div class="avatar" style="background-image: url(\''+avatarURL+'\')"></div>\
											<span>'+data[c].writerId+'</span>\
										</center>\
									</div>\
									<div class="comment-block" '+adminStyle+'>\
										<p class="comment-text">\
											<span>'+data[c].content+'</span>\
											<span></span>\
										</p>\
										<div class="bottom-comment">\
											<div class="comment-date">'+data[c].updateDate+' '+owner+'</div>\
											<ul class="comment-actions">\
											</ul>\
										</div>\
									</div>\
								</div>';
					parent(no).find(".comment-box>.comments").append(html);

					if(user.status == 'ADMIN'){
						var btnHtml = `<li class="modify-comment">수정</li>\
										<li class="modify-comment-submit">수정완료</li>\
										<li class="delete-comment">삭제</li>`;
						$(".comment-wrap[data-commentNo="+data[c].commentNo+"]").find(".comment-actions").html(btnHtml)
					}

					if(data[c].writer==user.memberNo ){						

						$(".comment-wrap[data-commentNo="+data[c].commentNo+"]").find(".photo").remove();
						var photoHtml ='<div class="photo">\
											<center>\
												<div class="avatar" style="background-image: url(\''+avatarURL+'\')"></div>\
												<span>'+data[c].writerId+'</span>\
											</center>\
										</div>';
						$(".comment-wrap[data-commentNo="+data[c].commentNo+"]").append(photoHtml);
					}
				}
 
				parent(no).find(".comment-loading-bar").hide();
				parent(no).attr("data-comment", "true")

				loadingStopAjax(parent(no).find(".comment-loading-bar"))
			},
			error : function(){
				loadingStopAjax(parent(no).find(".comment-loading-bar"))
			}
		})
	}

	// 댓글 삭제 / 수정 이벤트
	$("body").on("click", ".delete-comment,.modify-comment", function(){
		var commentParent = $(this).parents(".comment-wrap");
		var commentNo = commentParent.attr("data-commentNo")

		if($(this).hasClass("delete-comment")){
			swal({
				title: "Delete",
				text: "정말로 삭제하시겠습니까?",
				type: "warning",
				showCancelButton: true,
				closeOnConfirm: false
			  },
			  function(isConfirm) {
				if (isConfirm) {
					deleteComment(commentNo, commentParent)
					swal.close()
				}else{
					return false;
				}
			  });	
			// if(confirm("정말로 삭제하시겠습니까?")){
			// 	deleteComment(commentNo, commentParent)
			// 	return
			// }
		}else if($(this).hasClass("modify-comment")){
			commentParent.find(".modify-comment-submit").css({"display":"inline"});
			
			$(this).text("수정취소").addClass("cancel-edit-comment");
			var content = commentParent.find(".comment-text>span:eq(0)").text().trim();
			commentParent.find(".comment-text>span:eq(0)").hide();
			var html = '<textarea class="form-control comment-edit-input">'+content+'</textarea>';
			console.log(html)
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
					swal("Error", "수정 불가!", "error")
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
					swal("Error", "삭제 불가!", "error")
				}
			}
		})
	}

	// 댓글 추가 이벤트
	$("body").on("click", ".comment-btn", function(){
		if($(this).prev().val().length<1){
			swal("Error", "댓글을 입력하세요.", "error")
			return;
		}
		var no = $(this).parents(".panel-default").attr("data-no")
		addComment(no, $(this));	
		loadingAjax("", $(this))
		$(this).attr("disabled", true);
	})

	// 댓글 추가 함수
	function addComment(no, ele){
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

				// profile URL
				var avatarURL = "";
				if(user.type=="NORMAL"){
					avatarURL = projectURL+'/common/down.do?ext=img&path='+user.profileImg;
				}else{
					avatarURL = user.profileImg;
				}
				
				var html = '<div class="comment-wrap" data-commentNo="'+newCommentNo+'">\
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
								<div class="photo">\
									<center>\
										<div class="avatar" style="background-image: url(\''+avatarURL+'\')"></div>\
										<span>'+user.id+'</span>\
									</center>\
								</div>\
							</div>'; 
				parent(no).find(".comments").append(html);
				$(".comment-content").val("");

				var nowCommentCnt = parseInt(parent(no).find(".comment-count:eq(0)").text().trim());
				parent(no).find(".comment-count").html("").html(nowCommentCnt+1)

				ele.removeAttr("disabled")
				loadingStopAjax(ele)
			},
			error : function(){
				loadingStopAjax(ele)
			}
		})
	}

	// 좋아요 추가 이벤트
	$("body").on("click", ".like-btn", function(){
		var no = $(this).parents(".panel-default").attr("data-no")
		addLike(no, $(this));
		loadingAjax("", $(this))		
		$(this).attr("disabled", true);
	})
	
	// 좋아요 추가 함수
	function addLike(no, ele){

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
					swal("Error", "이미 좋아요를 누른 포럼입니다.", "error")
				}
				ele.removeAttr("disabled")
				loadingStopAjax(ele)
			},
			error : function(){
				loadingStopAjax(ele)
			}
		})
	}

	// 자동 코멘트 높이 조정
	$('body').on('keyup', '.comment-input-box>textarea, .forum-edit-input', '.comment-edit-input' , function () {
		$(this).height(1).height( $(this).prop('scrollHeight')+8 );				
	});



	///////////////////////////////////////
	/// 알람 부분


	if(param.forumNo){
		console.log(param.forumNo , param.commentNo)
		detail(param.forumNo, param.commentNo)
	}

	function detail(forumNo, commentNo){

		$.ajax({
			type : "GET",
			url : projectURL+"/alarm/detailalarm.json",
			data : {
				forumNo : forumNo,
			},
			dataType : "JSON",
			success : function(data){

				if(data.forum==null){
					swal("Error", "존재하지않는 포럼 입니다.", "error")
					return;
				}

				data.forum.updateDate = moment(data.forum.updateDate).fromNow();

				var adminStyle = "";
				if(data.forum.writerStatus=='ADMIN'){
					data.forum.writerId = data.forum.writerId+" [운영자]"
					adminStyle = "style=\"background:#f8f9ee !important\"";
				}

				var html = '\
					<div class="panel panel-default" data-no="'+data.forum.forumNo+'">\
						<div class="panel-heading alarm-panel-heading">\
						<h4 class="panel-title" data-toggle="collapse" data-parent="#accordion" href="#forum'+data.forum.forumNo+'">\
							<div class="category" data-category="'+data.forum.category+'">'+data.forum.category+'</div>\
							<div class="contentT">\
								<span class="titleT">'+data.forum.title+'</span>\
								<span class="dateT"><span id="moment"> '+data.forum.updateDate+'</span> | 댓글 <span class="comment-count">'+data.forum.commentCount+'</span> | <span id="author">'+data.forum.writerId+'</span>\
							</div>\
						</h4>\
						</div>\
						<div id="forum'+data.forum.forumNo+'" class="panel-collapse collapse in">\
							<div class="panel-body">\
								<h5 class="info-box">\
									<span class="info-bar">|</span>\
									<span>'+data.forum.category+'</span>\
									<span class="info-bar">|</span>\
									<span><i class="fa fa-calculator" aria-hidden="true"></i> '+data.forum.updateDate+'</span>\
									<span class="info-bar">|</span>\
									<span><i class="fa fa-comment comment-i" aria-hidden="true"></i> <span class="comment-count">'+data.forum.commentCount+'</span></span>\
									<span class="info-bar">|</span>\
									<span><i class="fa fa-heart like-i" aria-hidden="true"></i> <span class="like-count">'+data.forum.likeCount+'</span></span>\
									<span class="info-bar">|</span>\
									<span><i class="fa fa-eye" aria-hidden="true"></i> </i><span class="view-count">'+data.forum.view+'</span></span>\
								</h5>\
								<h5 class="writer-box" style="margin:0; margin-bottom: 10px; font-weight: normal;">\
									<i class="fa fa-user-o" aria-hidden="true"></i>\
									<div class="writer">\
										<span>'+data.forum.writerId+'</span>\
									</div>\
									<div class="button-box pull-right">\
									</div>\
								</h5>\
								<div class="content-box">\
									<span>'+data.forum.content+'</span>\
									<span></span>\
									<span></span>\
                                </div>\
                                <div class="file-box pull-right">\
                                </div><br><br>\
								<center class="like-box">\
									<button class="btn btn-default btn-sm like-btn">\
										<i class="fa fa-heart like-i" aria-hidden="true"></i> 좋아요\
									</button>\
									<button class="btn btn-default btn-sm kakao-share-btn">\
										<i class="fa fa-share-alt" aria-hidden="true"></i> 카톡 공유\
									</button>\
								</center>\
								<div class="input-group comment-input-box">\
									<textarea class="form-control comment-content" rows="2"></textarea>\
									<span class="input-group-addon btn btn-primary comment-btn">전송</span>\
								</div>\
								<div class="comment-box">\
									<center class="comment-loading-bar" style="display:none">\
									</center>\
									<span class="comments"><span>\
								</div>\
							</div>\
						</div>\
					</div>';
					$(".forum-list").prepend(html);
					// 작성자만 글삭제 수정버튼
					if(user.memberNo==data.forum.writer || user.status == 'ADMIN'){
						var btnHtml = ` <button class="btn btn-default btn-xs forum-delete">\
										삭제\
									</button>\
									<button class="btn btn-default btn-xs forum-edit">\
										수정\
									</button>
									<button class="btn btn-default btn-xs forum-edit-submit">\
										수정완료!\
									</button>`;
						parent(data.forum.forumNo).find(".button-box").html(btnHtml)
					}
						
					loadForumFile(forumNo)

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

						
						var owner = ""
						var adminStyle = "";
						
						if(comments[c].writer==comments[c].forumWriter){			
							owner = "[글쓴이]"			
						}
						if(comments[c].status == 'ADMIN'){
							owner += " [운영자]"
							adminStyle = "style=\"background:#f8f9ee !important\"";
						}
	
						var html = '<div class="comment-wrap" data-commentNo="'+comments[c].commentNo+'">\
										<div class="photo">\
											<center>\
												<div class="avatar" style="background-image: url(\''+avatarURL+'\')"></div>\
												<span>'+comments[c].writerId+'</span>\
											</center>\
										</div>\
										<div class="comment-block" '+adminStyle+'>\
											<p class="comment-text">\
												<span>'+comments[c].content+'</span>\
												<span></span>\
											</p>\
											<div class="bottom-comment">\
												<div class="comment-date">'+comments[c].updateDate+' '+owner+'</div>\
												<ul class="comment-actions">\
												</ul>\
											</div>\
										</div>\
									</div>';
						$(".panel-default").find(".comment-box>.comments").append(html);

						if(comments[c].writer==user.memberNo){
	
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

					if(param.commentNo){
						// 앵커!
						var anchorEle = $(".comment-wrap[data-commentNo="+commentNo+"]");
						anchorEle.find(".comment-block").animate({ backgroundColor: "rgb(255, 232, 225)" }, "slow");
						var offset = anchorEle.offset().top; 
						$(window).scrollTop(offset-70); 
	
						loadForumFile(param.forumNo)
					}
					
			}
		})
	}



	// // 사용할 앱의 JavaScript 키를 설정해 주세요.
	Kakao.init('d97a3b08e5637c89bd3b046a6e13e268');
	// // 카카오링크 버튼을 생성합니다. 처음 한번만 호출하면 됩니다.

	// 카카오톡 공유링크 버튼
	$("body").on("click", ".kakao-share-btn", function(){

		var ele = $(this);

		loadingAjax("", ele)

		ele.attr("disabled", "disabled")

		var forumNo = ele.parents(".panel-default").data("no");

		$.ajax({
			type : "GET",
			url : projectURL+"/alarm/detailalarm.json",
			data : {
				forumNo : forumNo,
			},
			dataType : "JSON",
			success : function(data){

				var forumInfo = data;

				$.ajax({
					type : "GET",
					url : projectURL+"/forum/forumfile.json",
					data : {
						forumNo : forumNo
					},
					success : function(data){
						if(data.result){
							forumInfo.forum.img = data.forumFiles;
						}


						sendLink(forumInfo);

					},
					error : function(){
						ele.removeAttr("disabled")	
						loadingStopAjax(ele)
					}
				})

			}
		})



		
		function sendLink(data) {

			ele.removeAttr("disabled")
			loadingStopAjax(ele)

			var viewUrl = "http://postfiles14.naver.net/MjAxODAxMDdfMjI5/MDAxNTE1MzM0MjI3NTc5.O1aFNsRAa-SQzrMNjUmKXVktv00NhDpzWecKgWuFey8g.cGOHY4o96sxhES1UJKr4hJMuEVhsJagabO64DeZ1tFUg.PNG.jonginout/coz.png?type=w2";
			if(data.forum.img){
				var path = (data.forum.img[0].path).replace(/\\/gi, "/");
				var viewUrl = "http://jongin.pe.kr/common/down.do?ext="+data.forum.img[0].ext+"&path="+path;
			}

			viewUrl = encodeURI(viewUrl)
			console.log(viewUrl)


			Kakao.Link.sendDefault({
				objectType: 'feed',
				content: {
					title: data.forum.title,
					description: data.forum.content,
					imageUrl: viewUrl,
					link: {
						webUrl: "http://jongin.pe.kr/forum/forum.do?forumNo="+data.forum.forumNo,
						mobileWebUrl : "http://jongin.pe.kr/forum/forum.do?forumNo="+data.forum.forumNo
					}
				},
				social: {
					likeCount: data.forum.likeCount,
					commentCount: data.comments.length,
					viewCount: data.forum.view
				},
				buttons: [
					{
						title: 'COZ 홈',
						link: {
							webUrl: "http://jongin.pe.kr",
							mobileWebUrl: "http://jongin.pe.kr"
						}
					},
					{
						title: '웹으로 보기',
						link: {
							webUrl: "http://jongin.pe.kr/forum/forum.do?forumNo="+data.forum.forumNo,
							mobileWebUrl: "http://jongin.pe.kr/forum/forum.do?forumNo="+data.forum.forumNo
						}
					}
				]
			});
		}

	})