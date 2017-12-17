<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>종인 클라우드</title>

<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<link href="${pageContext.request.contextPath}/dynatree-master/src/skin/ui.dynatree.css" rel="stylesheet" type="text/css" id="skinSheet">
<script src="${pageContext.request.contextPath}/dynatree-master/src/jquery.dynatree.js"></script>
<script src="https://use.fontawesome.com/0d0f160dde.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/date.format.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.32.0/codemirror.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.32.0/codemirror.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.32.0/theme/ambiance.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.32.0/mode/javascript/javascript.min.js"></script>







<style>
	.file-list, .file-detail{
		min-height: 300px !important;
		overflow: hidden;
		word-break: break-word;
	}
	.detail-box{
		margin-top: 30px;
	}
	.file:hover {
		font-weight: bold;
		color:tomato;
		cursor: pointer;
	}
	.selected{
		font-weight: bold;
		color:tomato;
	}
	.button-box{
		margin: 20px; 
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
</style>

<script type="text/javascript">
	
	//코드 뷰 사용가능 확장자
	var codeFileArr = ["java","js","html","css","jsp","php","txt"];
	//이미지 뷰 사용가능 확장자
	var imgFileArr = ["png","jpg","jpeg","gif","png","bmp"];
	
	
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
					<h1 class='file' data-key='"+node.data.key+"'>\
						<i class='fa fa-folder-open' aria-hidden='true'></i>\
						"+node.data.title+"\
					</h1>";
		
		
		childs && (childs = childs.length < 1 ? null : childs);
		if(childs){	
			for ( var key in childs) {
		    	 if(!childs[key].data.isFolder){
					var icon = '<i class="fa fa-file-o" aria-hidden="true"></i>';
					
					if(imgFileArr.indexOf(childs[key].data.ext)!=-1){
						icon = '<i class="fa fa-file-image-o" aria-hidden="true"></i>';
					}else if(codeFileArr.indexOf(childs[key].data.ext)!=-1){
						icon = '<i class="fa fa-file-code-o" aria-hidden="true"></i>';
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
	        data : {user:"종인"}
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
	            	if(node.data.title=="종인"){
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
					lazyReloadParent(recNode.key)
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

	<div class="container">

		<h1>
			<a href="">
				CLoud of Jongin <i class="fa fa-cloud-download" aria-hidden="true"></i>
			</a>
		</h1>
		<div class="button-box">
			<button id="reload">클라우드 새로고침</button>
			<button id="newFolder">현재위치에 새폴더</button>
			<button id="newCode">현재위치에 텍스트 파일</button>			
		</div>
		<form id="uploadForm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="uploadPath" value=""/>
			<label>현재위치에 파일 업로드</label>
			<input type="file" name="files" multiple="multiple">
			<button type="button">업로드</button>
		</form>
		
		<div id="tree"></div>

		<div class="row detail-box">
			<div class="col-xs-4 file-list">
				<div id="nowList"></div>
			</div>
			<div class="col-xs-8 file-detail">
				<div id="detailFile" data-key=""></div>
			</div>
		</div>

	</div>

	<!-- ========================================================================== -->
	<!-- ========================================================================== -->
	

	
	<script type="text/javascript">
		
		//파일 선택시 빨강 포커스
		function focusFile(ele) {
			$(".file").removeClass("selected");
			ele.addClass("selected");
		}
		
		// 파일 선택시 빨강 포커스
		$("body").on("click", ".file", function () {
			var key = $(this).attr("data-key");
			$("#tree").dynatree("getTree").getNodeByKey(key).activate(); // 강제 엑티브
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
					lazyReload();
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
					lazyReload();
				}
			})
		})

		//액티브된 노드 레이지 리로드
		function lazyReload() {
			var node = $("#tree").dynatree("getActiveNode");
			if (node && node.isLazy()) {
				node.reloadChildren(function(node, isOk) {
				});
			} else {
				alert("Please activate a lazy node first.");
			}
			
		};
		
		//부모 노드를 리로드
		function lazyReloadParent(parentKey) {
			var node =  $("#tree").dynatree("getTree").getNodeByKey(parentKey);
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
			
			if(selectNode.title!="종인"){
				html = '<div class="btn-group btn-group-justified">\
				  			  <div class="btn-group file-delete">\
						    	<button type="button" class="btn btn-default">\
						    		<i class="fa fa-trash" aria-hidden="true"></i> 삭제\
				  			    </button>\
						  	  </div>\
							  <div class="btn-group file-rename">\
							    <button type="button" class="btn btn-default">\
							    	<i class="fa fa-pencil-square-o" aria-hidden="true"></i> 이름 바꾸기\
								</button>\
							  </div>\
							  <div class="btn-group file-down">\
							    <button type="button" class="btn btn-default">\
									<i class="fa fa-download" aria-hidden="true"></i> 파일 다운로드\
								</button>\
							  </div>\
							 </div>';
			};
						
			html += "<h1>\
						파일명 : <span id='fileName'>"+selectNode.title+"</span>\
					</h1>\
						<span>수정한 날짜 : <span id='updateDate'>"+dateFormat(selectNode.updateDate)+"</span></span>";
			
			///확장자 구분
			
			// 뷰어 버튼
			if(!selectNode.isFolder){

				var ext = selectNode.ext.toLowerCase();
				if(codeFileArr.indexOf(ext)!=-1){		
					html += `
						<div class="codeBtn-box">
							<button type="button" class="code-view">
								<i class="fa fa-file-code-o" aria-hidden="true"></i> 코드 보기
							</button>
							<button type="button" class="code-refresh">
								<i class="fa fa-refresh" aria-hidden="true"></i> 코드 새로고침
							</button>
						</div>
						<div class="code-content">
						</div>
						`;
				}else if(imgFileArr.indexOf(ext)!=-1){
					html += `
						<div class="imageBtn-box">
							<button type="button" class="image-view" data-toggle='modal' data-target='#image'>
								<i class="fa fa-file-image-o" aria-hidden="true"></i> 이미지 보기
							</button>
						</div>
						`;
				}
			}
			html += `<br>
			<form id="form-comment">
				<div class="form-group">
					<label for="comment-file"><i class="fa fa-comments-o" aria-hidden="true"></i> 이 파일에 대한 코멘트</label>
					<textarea style="overflow:visible;" name="comment" class="form-control" id="comment-file"></textarea>
				</div>
				<button type="button" class="btn btn-block">코멘트 수정 완료</button>
			</form>
			`;
			
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
					lazyReloadParent(parentKey);
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
						lazyReloadParent(parentKey)
					}else {
						alert("파일명 변경 실패!!");
					}
				}
			})
		})
		
		//파일 업로드 우측하단 버튼
		$("body").on("click", ".file-down", function () {
			var key = $(this).parents("#detailFile").attr("data-key");
			var node = $("#tree").dynatree("getTree").getNodeByKey(key);

			var path = (node.data.path).replace(/\\/gi, "/");

			location.href = "${pageContext.request.contextPath}/common/img.do?path="+path+"&title="+node.data.title;
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
					lazyReload();
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

					var buttonHtml = '<button type="button" id="codeEidtBtn" data-key="'+key+'">\
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
		
		// 이미지 보기
		$("body").on("click",".image-view",function(){

			var key = $(this).parents("#detailFile").attr("data-key");
			var node = $("#tree").dynatree("getTree").getNodeByKey(key);

			var path = (node.data.path).replace(/\\/gi, "/");

			var viewUrl = "${pageContext.request.contextPath}/common/img.do?path="+path;
			var downUrl = viewUrl+"&title="+node.data.title;

			$(".image-content>img").attr("src", viewUrl);
			$("#image-down-link>a").attr("href", downUrl).html(node.data.title)

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
						alert(msg);
						lazyReload();
					},
					error : function () {
						alert("파일 업로드 실패!!");
					}
				})
			}


			
		});

	</script>
	

	<!-- Modal -->
	<div class="modal modal-middle fade scale-out" id="image" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-body image-content">
					<img style="width:100%;height:100%" src="" alt="" />
				</div>
				<div class="modal-footer">
					<div class="pull-left" id="image-down-link">
						<a style="font-size:22px" href=""></a>
					</div>
					<button class="btn btn-default" type="button" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
