<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
	}
	.detail-box{
		margin-top: 30px;
	}
	.file:hover {
		color:tomato;
		cursor: pointer;
	}
	.selected{
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
</style>

<script type="text/javascript">
	
	//코드 뷰 사용가능 확장자
	var codeFileArr = ["java", "js", "html", "css", "jsp", "php", "txt"];
	
	
	//현재 active된 경로
	var nowNode = "";
	//리네임 
	var rename = "";

	var editor;
	

	function showChildList(node) {
		var activeNode = node;
		
		if(node.data.isFolder){
			$("#detailFile").html("");
		}else {
			node = node.parent;
		}
		
		var childs = node.childList;
				
		var html = "<h1 class='file' data-key='"+node.data.key+"'>\
						<i class='fa fa-folder-open' aria-hidden='true'></i>\
						"+node.data.title+"\
					</h1>";
		
		
		childs && (childs = childs.length < 1 ? null : childs);
		if(childs){	
			for ( var key in childs) {
		    	 if(!childs[key].data.isFolder){
					html += "<h3 class='file' data-key='"+childs[key].data.key+"'>\
								<i class='fa fa-file-o' aria-hidden='true'></i>\
								"+childs[key].data.title+"\
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

	<div class="container">

		<h1>뭐먹지조 클라우드</h1>
		<div class="button-box">
			<button id="reload">클라우드 새로고침</button>
			<button id="newFolder">현재위치에 새폴더</button>
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


	
	<script type="text/javascript">
		
		//파일 선택시 빨강 포커스
		function focusFile(ele) {
			$(".file").removeClass("selected");
			ele.addClass("selected");
		}
		
		// 파일 선택시 빨강 포커스
		$("body").on("click", ".file", function () {
			focusFile($(this));
			showFileDetail($(this).attr("data-key"));
		})
	
		//전체 새로고침
		$("#reload").click(function () {
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
							  <div class="btn-group file-upload">\
							    <button type="button" class="btn btn-default">\
							    <i class="fa fa-paperclip" aria-hidden="true"></i></i> 파일 업로드\
								</button>\
							  </div>\
							 </div>';
			};
			
						
			html += "<h1>\
						파일명 : "+selectNode.title+"\
					</h1>\
						수정한 날짜 : "+dateFormat(selectNode.updateDate)+"";
			
			if(codeFileArr.indexOf(selectNode.ext)!=-1){		
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
			}
			
			$("#detailFile").html(html);
		}
		
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
		$("body").on("click", ".file-upload", function () {
			var key = $(this).parents("#detailFile").attr("data-key");
			var node = $("#tree").dynatree("getTree").getNodeByKey(key);
			if(!node.data || !node.data.isFolder){
				alert("폴더를 선택하지 않았거나, 선택하신 폴더가 없습니다.");
				return false;
			}
			$("#uploadForm>input[type=file]").trigger("click");
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

					$(".code-content").html(data.code)

					var html ='<form class="CodeMirror">\
								<textarea id="code" name="code">'+data.code+'</textarea>\
							  </form>';
					$(".code-content").html(html);
					loadEditor();

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
		function loadEditor(){
			editor = CodeMirror.fromTextArea(document.getElementById("code"), {
						lineNumbers: true,
						styleActiveLine: true,
						matchBrackets: true,
						theme : "ambiance"	
					  });
		}

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

				},
				error : function(){
					alert("코드 수정 실패!!");
				}
			})
			
		})
		
	</script>
	

	<!-- Modal -->
	<div class="modal fade" id="moveFile" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h3 class="modal-title" id="modalLabel"></h3>
	      </div>
	      <div class="modal-body">
	        ...
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">창닫기</button>
	        <button type="button" class="btn btn-primary">이동하기</button>
	      </div>
	    </div>
	  </div>
	</div>

</body>
</html>
