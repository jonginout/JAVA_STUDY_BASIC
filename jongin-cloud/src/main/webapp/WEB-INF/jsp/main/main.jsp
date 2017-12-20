<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>종인 클라우드</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- 제이쿼리, 제이쿼리-ui -->	
<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

<!-- dynatree -->
<link href="${pageContext.request.contextPath}/dynatree-master/src/skin/ui.dynatree.css" rel="stylesheet" type="text/css" id="skinSheet">
<script src="${pageContext.request.contextPath}/dynatree-master/src/jquery.dynatree.js"></script>

<!-- 부트스트랩 -->
<script src="https://use.fontawesome.com/0d0f160dde.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- 김종인 데이트 라이브러리 -->
<script src="${pageContext.request.contextPath}/js/date.format.js"></script>

<!-- 코드미러 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.32.0/codemirror.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.32.0/codemirror.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.32.0/theme/ambiance.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.32.0/mode/javascript/javascript.min.js"></script>


<script src="${pageContext.request.contextPath}/pdfobject/pdfobject.min.js"></script>


<style>
	
	body{
		background: whitesmoke;
	}
	.file-list, .file-detail{
		min-height: 300px !important;
		overflow: hidden;
		word-break: break-word;
	}
	.detail-box{
		margin-top: 30px;
	}
	#fileName{
		font-weight: bold;
	}
	.file:hover {
		font-weight: bold;
		color:tomato;
		cursor: pointer;
	}
	#updateDate{
		margin-bottom: 15px;
	}
	.selected{
		font-weight: bold;
		color:tomato;
	}
	.button-box{
		margin-top : 20px; 
		margin-bottom : 20px;
	}
	.button-box>button{	
		margin-top : 5px; 
		margin-bottom : 5px;
	}
	input[type=file]{
		width : 200px;
		display: inline-block;
	}
	.CodeMirror {
		border: 1px solid #eee;
		height: auto;
	}
	#image-down-link{
		overflow: hidden;
		word-break: break-word;
	}
	.drag-over{
		font-weight: bold;
		font-size: 30px;
		color: cornflowerblue;
	}
	.container{
		margin: auto !important;
		margin-top: 5px !important;
		margin-bottom : 30px !important;
	}
	.comment-btn{
		opacity: 0.9;
	}
	#kPay-btn:hover{
		cursor: pointer;
		opacity: 0.8;
	}
	.form-err{
		border: 0.1em solid tomato !important;
	}
	.form-success{
		border: 0.1em solid darkgreen !important;
	}
	.form-control:focus {
		border-color: none !important;
	}
</style>

<script type="text/javascript">

	function fileExtChecker(ext){

		var chk = "";

		if(imgFileArr.indexOf(ext)!=-1){
			chk = "img"
		}else if(codeFileArr.indexOf(ext)!=-1){
			chk = "code"
		}else if(ext=='pdf'){
			chk = "pdf"
		}else if(audioFileArr.indexOf(ext)!=-1){
			chk = "audio"
		}else if(movieFileArr.indexOf(ext)!=-1){
			chk = "movie"
		}else if(zipFileArr.indexOf(ext)!=-1){
			chk = "zip"
		}else{
			chk = "ect"
		}

		return chk;
	}
	
	//코드 뷰 사용가능 확장자
	var codeFileArr = ["java","js","html","css","jsp","php","txt"];
	//이미지 뷰 사용가능 확장자
	var imgFileArr = ["png","jpg","jpeg","gif","png","bmp"];
	//음악 사용가능 확장자
	var audioFileArr = ["mp3", "ogg", "wav", "ACC"];
	//동영상파일 사용가능 확장자
	var movieFileArr = ["mp4", "avi", "mkv", "wmv"];
	//압축파일 사용가능 확장자
	var zipFileArr = ["zip", "alz", "egg"];
	
	//현재 active된 경로
	var nowNode = "";
	//리네임 
	var rename = "";

	var editor;
	
	// 왼쪽 파일 폴더 리스트
	function showChildList(node) {
		var activeNode = node;
		
		if(node.data.isFolder){
			$("#detailFile").html("");
		}else {
			node = node.parent;
		}
		
		var childs = node.childList;
				
		var html = "<div class='alert alert-danger alert-dismissible' role='alert'>\
						<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>\
						<strong>폴더에</strong> 파일을 드래그 앤 드롭으로 업로드 가능!\
					</div>\
					<h2 class='file' data-key='"+node.data.key+"'>\
						<i class='fa fa-folder-open' aria-hidden='true'></i>\
						"+node.data.title+"\
					</h2>";
		
		
		childs && (childs = childs.length < 1 ? null : childs);
		if(childs){	
			for ( var key in childs) {
		    	 if(!childs[key].data.isFolder){
					var icon = '<i class="fa fa-file-o" aria-hidden="true"></i>';
					var extNow = childs[key].data.ext.toLowerCase();

					var fc = fileExtChecker(extNow);

					if(fc=='img'){
						icon = '<i class="fa fa-file-image-o" aria-hidden="true"></i>';
					}else if(fc=='code'){
						icon = '<i class="fa fa-file-code-o" aria-hidden="true"></i>';
					}else if(fc=='pdf'){
						icon = '<i class="fa fa-file-pdf-o" aria-hidden="true"></i>';
					}else if(fc=='audio'){
						icon = '<i class="fa fa-file-audio-o" aria-hidden="true"></i>';
					}else if(fc=='movie'){
						icon = '<i class="fa fa-file-video-o" aria-hidden="true"></i>';
					}else if(fc=='zip'){
						icon = '<i class="fa fa-file-archive-o" aria-hidden="true"></i>';
					}

					html += "<h3 class='file' data-key='"+childs[key].data.key+"'>\
								"+icon+" "+childs[key].data.title+"\
							</h3>";
			      }else {
					html += "<h3 class='file' data-key='"+childs[key].data.key+"'>\
								<i class='fa fa-folder-o' aria-hidden='true'></i> \
								"+childs[key].data.title+"\
							</h3>";
				}
			}
		}else {
			html += "<h3><i class='fa fa-exclamation-circle' aria-hidden='true'></i> 파일이 없습니다.</h3>";
		}
		$("#nowList").html(html);
		nowNode = activeNode.data;
		console.log("활성화된 노드 nowNode",nowNode)
	}

	function dirTree() {
		console.log("트리 로딩 완료..")
	    $("#tree").dynatree({
	      title: "김종인님의 클라우드",
	      fx: { height: "toggle", duration: 200 },
	      autoFocus: false,
	      initAjax: {
	        url: "${pageContext.request.contextPath}/tree/list.json",
	        data : {user:"jongin"}
	        },
// 	        onPostInit: function(isReloading, isError) {
// 	      	  alert(isReloading)
// 	        },
	      onActivate: function(node) {
	    	console.log(node)
	    	showChildList(node);
	    	showFileDetail(node.data.key);
			focusFile($(".file[data-key="+node.data.key+"]"));
	    	},
	      onLazyRead: function(node){
	        node.appendAjax({
	          url: "${pageContext.request.contextPath}/tree/sublist.json",
	          data : { path : node.data.path },
	          debugLazyDelay: 200, 
	          success : function (node) {
	        	  console.log(node)
	        	  showChildList(node)
	        	  showFileDetail(node.data.key)
	        	  focusFile($(".file[data-key="+node.data.key+"]"));
	        	  
	        	  ///////////////////////////파일명수정
	        	  if(node.childList){	        		  
		        	  for (let ch of node.childList) {
		        		  	var ext = "";
		        		  	if(ch.data.ext){
		        		  		ext = "."+ch.data.ext;
		        		  	}
							if(ch.data.title==rename+ext){
								showFileDetail(ch.data.key)
								focusFile($(".file[data-key="+ch.data.key+"]"));
							}
					  }
		        	  rename = "";

	        	  }
	        	  ////////////////////////////
	        	  
				}
	        });
	      },
	      
	      ////////////////드래그앤드롭////////////////드래그앤드롭
	      dnd: {
	          onDragStart: function(node) {
	            return true;
	          },
	          autoExpandMS: 1000,
	          preventVoidMoves: true,
	          onDragEnter: function(node, sourceNode) {
	            return true;
	          },
	          onDragOver: function(node, sourceNode, hitMode) {
	            //옮기는 과정에서 위에 가져다 대기만 한 상황
	            
	            if(node.isDescendantOf(sourceNode)){
	              return false;
	            }
	            // Prohibit creating childs in non-folders (only sorting allowed)
	            if( !node.data.isFolder && hitMode === "over" ){
	              return "after";
	            }
	          },
	          onDrop: function(node, sourceNode, hitMode, ui, draggable) {
	            //노드 실게 그곳에 두어서 옮기기
	            
	            // 힛모드 이동인지 체크
	            var hitModeChk = false;
	            
	            if(hitMode=="before" || hitMode=="after"){
	            	if(node.data.title=="jongin"){
	            		alert("루트 폴더 밖으로는 옮길 수 없습니다.")
	            		return false;
	            	}
	            	hitModeChk = true;
// 	            	node.data.path = node.data.path.substring(0, node.data.path.lastIndexOf("\\"));
	            }
	            
	            //노드 (겉모습만 이동)
	            sourceNode.move(node, hitMode);
	            
	            console.log("옮길 것",sourceNode.data.path);
	            console.log("옮겨질 곳",node.data.path);

	            moveFile(sourceNode.data, node.data, hitModeChk)
	            
	          }
	        },
			////////////////드래그앤드롭////////////////드래그앤드롭
			onKeydown: function(node, event) {
				switch( event.which ) {
					case 113: // [F2]
						$(".file-rename").trigger("click")
						return false;
				}
			},
	      
// 	      onClick : function (node) {
// 		  },
	      minExpandLevel: 2,
	      debugLevel: 0
	    });
	}


	// 파일 드래그 앤 드롭 이동
	function moveFile(moveNode, recNode, hitModeChk) {
		
		// hitMode 였을때는 path를 부모경로로 잠시 바꿔준다.
		var recPath = recNode.path;
		if(hitModeChk){
			recPath = recNode.path.substring(0, recNode.path.lastIndexOf("\\"));
		}
		
		$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/tree/filemove.json",
			data : {
				moveFilePath : moveNode.path,
				recFilePath : recPath
			},
			success : function (data) {
				if(data.result){
					var node =  $("#tree").dynatree("getTree").getNodeByKey(moveNode.key);
					// path 값을 한번 초기화
					node.data.path = data.path;
					
					// 옮겨진 곳을 한번 리로드
					lazyReloadTarget(recNode.key)
				}else {
					alert("파일이동 실패!!");					
				}
			}
		})
	}
	
	// 트리 불러오기
	$(function(){
		dirTree();
    });
	
</script>
</head>

<body>

	<!-- ========================================================================== -->	
	<!-- ========================================================================== -->
	<!-- ========================================================================== -->
	
	<div class="container">
		<h2>
			<a href="">
				CLOUD OF JONGIN <i class="fa fa-cloud-download" aria-hidden="true"></i>
			</a>
		</h2>
		<div class="button-box">
			<button class="btn btn-default" id="reload">
				<i class="fa fa-refresh" aria-hidden="true"></i> 
				클라우드 새로고침
			</button>
			<button class="btn btn-default" id="newFolder">
				<i class="fa fa-folder-o" aria-hidden="true"></i>
				현재위치에 새폴더
			</button>
			<button class="btn btn-default" id="newCode">
				<i class="fa fa-file-o" aria-hidden="true"></i>
				현재위치에 텍스트 파일
			</button>			
			<button class="btn btn-default" data-toggle="modal" data-target="#addVolume">
				<i class="fa fa-hdd-o" aria-hidden="true"></i> 
				용량추가
			</button>	
		</div>
		<form id="uploadForm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="uploadPath" value=""/>
			<input type="file" name="files" multiple="multiple">
			<button type="button">업로드</button>
		</form>
		
		<div id="tree"></div>

		<div class="row detail-box">
			<div class="hidden-xs col-sm-4 file-list">
				<div id="nowList"></div>
			</div>
			<div class="col-xs-12 col-sm-8 file-detail">
				<div id="detailFile" data-key=""></div>
			</div>
		</div>

	</div>

	<!-- ========================================================================== -->
	<!-- ========================================================================== -->
	<!-- ========================================================================== -->
	

	 
	<script type="text/javascript">

		$("#addVolume").click(function(){

		})
		
		//파일 선택시 빨강 포커스
		function focusFile(ele) {
			$(".file").removeClass("selected");
			ele.addClass("selected");
		}
		
		// 파일 선택시 빨강 포커스
		$("body").on("click", ".file", function () {
			var key = $(this).attr("data-key");
			var node = $("#tree").dynatree("getTree").getNodeByKey(key);
			node.activate(); // 강제 엑티브
			node.expand(); // 강제 오픈
			focusFile($(this));
			showFileDetail(key);
		})
	
		//전체 새로고침
		$("#reload").click(function () {
			nowNode = null;
			$("#nowList").html("");
			$("#detailFile").html("");
			$("#tree").dynatree("destroy");
			dirTree();
		})
		
		// 새폴더
		$("#newFolder").click(function () {
			if(!nowNode || !nowNode.isFolder){
				alert("폴더를 선택하지 않았거나, 선택하신 폴더가 없습니다.");
				return false;
			}
			var newFolderName = prompt("새폴더 이름을 작성하세요.");
			if(!newFolderName){
				return false;
			}
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/tree/newfolder.json",
				data : {
					path : nowNode.path,
					name : newFolderName
				},
				dataType : "json",
				success : function (data) {
					if(data.dup){
						alert("똑같은 이름의 폴더가 존재합니다.");
						return false;
					}
					$("#nowList").html("");
					lazyReloadActive();
				}
			})
		})

				
		// 새코드
		$("#newCode").click(function () {
			if(!nowNode || !nowNode.isFolder){
				alert("폴더를 선택하지 않았거나, 선택하신 폴더가 없습니다.");
				return false;
			}
			var newCodeFile = prompt("새 코드파일 이름을 작성하세요.(확장자 까지 포함)\n-권장 파일 형식 [java, js, html, css, jsp, php, txt]");
			if(!newCodeFile){
				alert("파일명을 입력하세요.");
				return false;
			}else{
				var newExt = newCodeFile.substring(newCodeFile.lastIndexOf(".")+1);
			}
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/tree/newCode.json",
				data : {
					path : nowNode.path,
					name : newCodeFile
				},
				dataType : "json",
				success : function (data) {
					if(data.dup){
						alert("똑같은 이름의 파일이 존재합니다.");
						return false;
					}
					$("#nowList").html("");
					lazyReloadActive();
				}
			})
		})

		//액티브된 노드 레이지 리로드
		function lazyReloadActive() {
			var node = $("#tree").dynatree("getActiveNode");
			if (node && node.isLazy()) {
				node.reloadChildren(function(node, isOk) {
				});
			} else {
				alert("Please activate a lazy node first.");
			}
			
		};
		
		//특정 노드를 리로드
		function lazyReloadTarget(key) {
			var node =  $("#tree").dynatree("getTree").getNodeByKey(key);
			if (node && node.isLazy()) {
				node.reloadChildren(function(node, isOk) {
				});
			} else {
				alert("Please activate a lazy node first.");
			}
		};
		
		
		// 우측 디테일 부분 
		function showFileDetail(key) {
			$("#detailFile").attr({"data-key":key})
			
			var selectNode = $("#tree").dynatree("getTree").getNodeByKey(key).data;
			
			var html = "";
			
			if(selectNode.title!="jongin"){
				html = '<div class="btn-group btn-group-justified">\
				  			  <div class="btn-group file-delete">\
						    	<button type="button" class="btn btn-default">\
						    		<i class="fa fa-trash" aria-hidden="true"></i> 삭제\
				  			    </button>\
						  	  </div>\
							  <div class="btn-group file-rename">\
							    <button type="button" class="btn btn-default">\
							    	<i class="fa fa-pencil-square-o" aria-hidden="true"></i> 이름 변경\
								</button>\
							  </div>\
							  <div class="btn-group file-down">\
							    <button type="button" class="btn btn-default">\
									<i class="fa fa-download" aria-hidden="true"></i> 다운로드\
								</button>\
							  </div>\
							 </div>';
			};
						
			// 파일명
			html += "<h1>\
						<span id='fileName'>"+selectNode.title+"</span>\
					</h1>\
						<div id='updateDate'>수정한 날짜 : <span id='updateDate'>"+dateFormat(selectNode.updateDate)+"</span></div>";
			
			///확장자 구분
			
			// 뷰어 버튼
			if(!selectNode.isFolder){

				var ext = selectNode.ext.toLowerCase();
				var fc = fileExtChecker(ext);

				if(fc=='code'){		
					html += `
						<div class="codeBtn-box">
							<button type="button" class="code-view btn btn-default btn-sm">
								<i class="fa fa-file-code-o" aria-hidden="true"></i> 코드 보기
							</button>
							<button type="button" class="code-refresh btn btn-default btn-sm">
								<i class="fa fa-refresh" aria-hidden="true"></i> 코드 새로고침
							</button>
						</div>
						<div class="code-content">
						</div>
						`;
				}else if(fc=='img' || fc == 'pdf' || fc == 'audio' || fc == 'movie'){

					var icon = {
						'img' : '<i class="fa fa-file-image-o" aria-hidden="true"></i> 이미지 보기',
						'pdf' : '<i class="fa fa-file-pdf-o" aria-hidden="true"></i> PDF 보기',
						'audio' : '<i class="fa fa-file-audio-o" aria-hidden="true"></i> 오디오 듣기',
						'movie' : '<i class="fa fa-file-video-o" aria-hidden="true"></i> 동영상 재생',
					}

					html += '<div class="imageBtn-box">\
								<button type="button" class="file-view-btn btn btn-default btn-sm" data-toggle="modal" data-target="#file-modal">\
									'+icon[fc]+'\
								</button>\
							</div>';
				}
			}

			if(selectNode.title!="jongin"){
				html += `<br>
				<form id="form-comment">
					<div class="form-group" style="margin-bottom:-1px;">
						<label for="comment-file"><i class="fa fa-comments-o" aria-hidden="true"></i> 이 파일에 대한 코멘트</label>
						<textarea style="overflow:visible;" name="comment" class="form-control" id="comment-file"></textarea>
					</div>
					<button type="button" class="btn btn-block btn btn-danger btn-sm comment-btn">
						<i class="fa fa-comments-o" aria-hidden="true"></i> 
						코멘트 수정 완료
					</button>
				</form>
				`;
			}else{
				html += '<br><h1>Root 디렉토리</h1>'
			}
			
			$("#detailFile").html(html);

			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/tree/commentview.json",
				data : {
					title : selectNode.title,
					path : selectNode.path.substring(0,(selectNode.path.lastIndexOf(selectNode.title))-1)
				},
				success : function(data){
					$("#comment-file").val(data);
					$("#comment-file").height(1).height( $("#comment-file").prop('scrollHeight')+12 );
				}
			})

		}

		// 닫기 버튼
		$("body").on("click", ".file-view-close", function(){
			$(".file-view-content").html("")
		})
		
		// 자동 코멘트 높이 조정
		$('body').on('keydown keyup', '#comment-file' , function () {
			$(this).height(1).height( $(this).prop('scrollHeight')+12 );				
		});

		// 코멘트 저장하기
		$("body").on("click", "#form-comment>button", function(){
			console.log(nowNode.title)
			var comment = $("#comment-file").val();
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/tree/filecomment.json",
				data : {
					comment: comment,
					title : nowNode.title,
					path : nowNode.path.substring(0,(nowNode.path.lastIndexOf(nowNode.title))-1)
				},
				success : function(){
					alert("코멘트 저장 완료");
				},
				error : function(){
					alert("코멘트 저장 실패");
				}
			})
		})

		// 파일 삭제
		$("body").on("click", ".file-delete", function () {
			if(!confirm("정말로 이 파일을 삭제하시겠습니까?\n※ 추후 복구불가")){
				return false;
			}
			var key = $(this).parents("#detailFile").attr("data-key");
			var parentKey = $("#tree").dynatree("getTree").getNodeByKey(key).parent.data.key;
			var path = $("#tree").dynatree("getTree").getNodeByKey(key).data.path;
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/tree/filedelete.json",
				data : {path:path},
				success : function () {
					alert("삭제 성공");
					lazyReloadTarget(parentKey);
					showFileDetail(parentKey)
				},
				error : function () {
					alert("삭제 실패!!");
				}
			})
		})
		
		// 파일 리네임
		$("body").on("click", ".file-rename", function () {
			var key = $(this).parents("#detailFile").attr("data-key");
			var node = $("#tree").dynatree("getTree").getNodeByKey(key);
			var parentKey = node.parent.data.key;
			var path = node.data.path;
			var title = node.data.title;
			var ext = node.data.ext;
			var isFolder = node.data.isFolder;
			
			if(isFolder){
				rename = prompt("변경할 이름을 작성하세요. (확장자 제외)", title);
			}else {				
				rename = prompt("변경할 이름을 작성하세요. (확장자 제외)", title.substring(0,title.lastIndexOf(".")));
			}
			if(!rename){
				return false;
			}
			
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/tree/filerename.json",
				data : {
					path : path,
					rename : rename.trim()
					},
				success : function (data) {
					if(data.result){
						alert("파일명 변경 성공");
						lazyReloadTarget(parentKey)
					}else {
						alert("파일명 변경 실패!!");
					}
				}
			})
		})
		
		//파일 다운로드 우측하단 버튼
		$("body").on("click", ".file-down", function () {
			var key = $(this).parents("#detailFile").attr("data-key");
			var node = $("#tree").dynatree("getTree").getNodeByKey(key);

			var path = (node.data.path).replace(/\\/gi, "/");

			location.href = "${pageContext.request.contextPath}/common/down.do?ext="+node.data.ext+"&path="+path+"&title="+node.data.title;
		})
		
		// 상단 파일 첨부 버튼
		$("#uploadForm>input[type=file]").click(function () {
			$("#uploadForm>input[name=uploadPath]").val("");
			if(!nowNode || !nowNode.isFolder){
				alert("폴더를 선택하지 않았거나, 선택하신 폴더가 없습니다.");
				return false;
			}
			$("#uploadForm>input[name=uploadPath]").val(nowNode.path)			
		})
		
		// 업로드 버튼
		$("#uploadForm>button").click(function () {
			if(!$("#uploadForm>input[name=uploadPath]").val()){
				alert("파일을 선택하세요.")
				return false;
			}
			var fd = new FormData($("#uploadForm")[0]);
			console.log(fd)
			
			$.ajax({
				type : "post",
				url : "${pageContext.request.contextPath}/tree/fileupload.json",
				data : fd,
				processData : false,
				contentType : false,
				success : function (data) {
					var msg = "파일 업로드 성공!";
					if(data.dup){
						msg = "파일 업로드 성공!\n※ 중복된 파일명은 자동 변경되어 업로드 되었습니다. ※"
					}
					alert(msg);
					$("#uploadForm")[0].reset();
					$("#uploadForm>input[name=uploadPath]").val("");
					lazyReloadActive();
				},
				error : function () {
					alert("파일 업로드 실패!!");
				}
			})
			
		})

		// 코드 보기
		$("body").on("click", ".code-view", function(){
			var key = $(this).parents("#detailFile").attr("data-key");
			var node = $("#tree").dynatree("getTree").getNodeByKey(key);

			$.ajax({
				type : "post",
				url : "${pageContext.request.contextPath}/tree/codeview.json",
				data : {path : node.data.path},
				success : function (data) {

					var html ='<form class="CodeMirror">\
								<textarea id="code" name="code"></textarea>\
							  </form>';
					$(".code-content").html(html);
					loadEditor(data.code);

					var buttonHtml = '<button class="btn btn-default" type="button" id="codeEidtBtn" data-key="'+key+'">\
										<i class="fa fa-floppy-o" aria-hidden="true"></i> 수정하기\
									  </button>';
					$("#codeEidtBtn").remove();
					$(".codeBtn-box").append(buttonHtml);

				},
				error : function () {
					alert("코드 보기 실패!!");
				}
			});
		})

		// 코드 에디터 로드하기
		function loadEditor(code){
			$("#plzSave").remove();
			editor = CodeMirror.fromTextArea(document.getElementById("code"), {
						lineNumbers: true,
						styleActiveLine: true,
						matchBrackets: true, 
						theme : "ambiance",
					  });
			editor.setValue(code)

			editor.on("change", function(){
				var plzSave = `
					<span id="plzSave" style='color:red'> 
						<i class='fa fa-dot-circle-o' aria-hidden='true'></i>
					</span>
				`;
				$("#fileName").html(nowNode.title+plzSave);
			})
		}

		// 코드 새로고침
		$("body").on("click", ".code-refresh", function(){
			$(".code-view").trigger("click");
		})
		

		// 코드 수정하기
		$("body").on("click", "#codeEidtBtn", function(){
			var node = $("#tree").dynatree("getTree").getNodeByKey($(this).attr("data-key"));

			$.ajax({
				type : "post",
				url : "${pageContext.request.contextPath}/tree/codechange.json",
				data : {
					changeCode : editor.getValue(),
					path : node.data.path
					},
				success : function(data){
					alert("코드 저장 성공!");
					$(".code-view").trigger("click");
					$("#updateDate").html(dateFormat(Date.now()))
				},
				error : function(){
					alert("코드 수정 실패!!");
				}
			})
			
		})
		
		// 이미지 PDF 동영상 음악 뷰어
		$("body").on("click",".file-view-btn",function(){

			var key = $(this).parents("#detailFile").attr("data-key");
			var node = $("#tree").dynatree("getTree").getNodeByKey(key);

			var path = (node.data.path).replace(/\\/gi, "/");
			var ext = node.data.ext.toLowerCase();

			var viewUrl = "${pageContext.request.contextPath}/common/down.do?ext="+ext+"&path="+path;
			var downUrl = viewUrl+"&title="+node.data.title;

			var fc = fileExtChecker(ext);
			var wh100 = 'style="width:100%;height:100%"';

			var viweHtml = {
				'img' : '<img '+wh100+' src="'+viewUrl+'" alt="'+node.data.title+'" />',
				'pdf' : '<object data="'+viewUrl+'" type="application/pdf" width="100%" height="600px"></object>',
				'audio' : '<div '+wh100+'><audio controls><source src="'+viewUrl+'" type="audio/mpeg"></audio></div>',
				'movie' : '<video '+wh100+' controls><source src="'+viewUrl+'" type="video/mp4"></video>',
			}

			$(".file-view-content").html("").html(viweHtml[fc]);
			$("#file-down-link>a").attr("href", downUrl).html(node.data.title)

		})
		

		// 드래그 & 드롭으로 파일 업로드
		$("body").on('dragover', '.file', function (e) {
				e.stopPropagation();
				e.preventDefault();
				// console.log(e);
				$(this).addClass("drag-over")
		});
		
		$("body").on('dragleave', '.file', function (e) {
				e.stopPropagation();
				e.preventDefault();
				// console.log(e);
				$(this).removeClass("drag-over")
		});
		
		$("body").on("drop", '.file', function (e){
			e.stopPropagation();
			e.preventDefault();
			// console.log(e);

			$(this).removeClass("drag-over")
			var key = $(this).attr("data-key");
			var node = $("#tree").dynatree("getTree").getNodeByKey(key);

			if(!node.data.isFolder){
				alert("폴더에만 파일을 업로드 할 수 있습니다.");
				return false;
			}

			console.log(node.data)

			var files = e.originalEvent.dataTransfer.files;
			if(files.length<1){
				return false;
			}else{
				var fd = new FormData();
				fd.append('uploadPath',node.data.path)
        		for (var i = 0; i < files.length; i++) {
            		fd.append('files', files[i]);
         		}
				$.ajax({
					type : "post",
					url : "${pageContext.request.contextPath}/tree/fileupload.json",
					data : fd,
					processData : false,
					contentType : false,
					success : function (data) {
						var msg = "파일 업로드 성공!";
						if(data.dup){
							msg = "파일 업로드 성공!\n※ 중복된 파일명은 자동 변경되어 업로드 되었습니다. ※"
						}
						
						node.activate(); // 강제 엑티브
						node.expand(); // 강제 오픈
						focusFile($(this));
						showFileDetail(key);

						alert(msg);
						lazyReloadTarget(key);
					},
					error : function () {
						alert("파일 업로드 실패!!");
					}
				})
			}


			
		});

	</script>
	
	
	<!-- Modal -->
	<div class="modal modal-middle fade scale-out" id="file-modal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-body file-view-content">
					<object data="" type="application/pdf" width="100%" height="600px"></object>
				</div>
				<div class="modal-footer">
					<div class="pull-left" id="file-down-link">
						<a style="font-size:22px" href=""></a>
					</div>
					<button class="btn btn-default file-view-close" type="button" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 결제 Modal -->
	<div class="modal modal-middle fade scale-out" id="addVolume" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 style="padding:0px;margin:0px;"><strong><i class="fa fa-hdd-o" aria-hidden="true"></i> 드라이브 용량 추가</strong></h3>
				</div>
				<div class="modal-body pay-content">
					
						<form class="form-horizontal pay-info">
							<div class="form-group">
								<label class="col-sm-2 control-label">성명</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="name" placeholder="이름">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">이메일</label>
								<div class="col-sm-10">
									<input type="email" class="form-control" name="email" placeholder="E-mail">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">추가용량</label>				
								<div class="col-sm-10">
									<select class="form-control" name="volume">
										<optgroup label="용량선택">
											<option value="100">+100MB</option>
											<option value="300">+300MB</option>
											<option value="500">+500MB</option>
											<option value="800">+800MB</option>
											<option value="1000">+1GB</option>
										</optgroup>
									</select>
								</div>					
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">전화번호</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="tel" placeholder="전화번호 - 제외">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10 pull-right">
									<img src="${pageContext.request.contextPath}/img/kpay-btn.png" alt="카카오페이로 결제" id="kPay-btn">
									<!-- <button type="submit" class="btn btn-info btn-block">카카오페이</button> -->
								</div>
							</div>
						</form>


				</div>
				<div class="modal-footer">
					<button class="btn btn-default pay-close" type="button" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>

<!-- 카카오 페이 -->
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script>
	$("body").on("keyup", ".pay-info input[name=tel]", function(){
		var telPattern = /^([0-9]){9,12}$/;
		var tel = $(this).val();
		if(!telPattern.test(tel)){
			$(this).removeClass("form-success").addClass("form-err");
		}else{
			console.log("??")
			$(this).removeClass("form-err").addClass("form-success");			
		}
	})

	$("body").on("click", "#kPay-btn", function(){

		var fd = {
			name : $(".pay-info input[name=name]").val(),
			email : $(".pay-info input[name=email]").val(),
			volume : $(".pay-info select[name=volume]").val(),
			tel : $(".pay-info input[name=tel]").val()
		}	

		for (var key in fd) {
			console.log(fd[key].length<0)
			if(fd[key].length<1){
				alert("결제정보를 옳바르게 입력하시오.");
				return;
			}
		}

		$(".pay-close").trigger("click");

			IMP.init('imp32572105'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
			IMP.request_pay({
			pg : 'kakao', // version 1.1.0부터 지원.
			pay_method : 'card',
			merchant_uid : 'merchant_' + new Date().getTime(),
			name : '종인 클라우드 ['+fd.volume+'MB] 용량추가',
			amount : 1000,
			buyer_email : fd.email,
			buyer_name : fd.name,
			buyer_tel : fd.tel,
			m_redirect_url : '/'
		}, function(rsp) {
			if ( rsp.success ) {
				var msg = '결제가 완료되었습니다.';
				msg += '고유ID : ' + rsp.imp_uid;
				msg += '상점 거래ID : ' + rsp.merchant_uid;
				msg += '결제 금액 : ' + rsp.paid_amount;
				msg += '카드 승인번호 : ' + rsp.apply_num;
			} else {
				var msg = '결제에 실패하였습니다.';
				msg += '에러내용 : ' + rsp.error_msg;
			}
			$(".tf_kakaopay").val(fd.tel);
			alert(msg);
		});
	})
</script>