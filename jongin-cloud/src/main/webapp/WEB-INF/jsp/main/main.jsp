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
</style>

<script type="text/javascript">
	
	//현재 active된 경로
	var nowPath = "";
	var rename = "";

	function showChildList(node) {
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
		nowPath = node.data.path;
		console.log(nowPath)
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
	    	  if(!node.data.isFolder){	    	
	    		  	showChildList(node.parent);
	    		  	showFileDetail(node.data.key);
		    	  }else {
		    		$("#detailFile").html("");
					showChildList(node);
					showFileDetail(node.data.key);
				}
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
	        	  focusFile($(".file[data-key="+node.data.key+"]"));
	        	  
	        	  ///////////////////////////파일명수정
	        	  if(node.childList){	        		  
		        	  for (let ch of node.childList) {
		        		  	console.log(ch.data)
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
	            sourceNode.move(node, hitMode);
	            
	            console.log("옮길 노드",sourceNode.data.path);
	            console.log("옮길 곳",node.data.path);
	            moveFile(sourceNode.data, node.data.path)
	            
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
	function moveFile(moveNode, recFilePath) {
		$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/tree/filemove.json",
			data : {
				moveFilePath : moveNode.path,
				recFilePath : recFilePath
			},
			success : function (data) {
				if(data.result){
					var node =  $("#tree").dynatree("getTree").getNodeByKey(moveNode.key);
					// path 값 한번 초기화
					node.data.path = data.path;
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
		<p>
			<button id="reload">클라우드 새로고침</button>
			<button id="newFolder">현재위치에 새폴더</button>
		</p>
		
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
		
		$("#newFolder").click(function () {
			if(!nowPath){
				alert("선택된 폴더가 없습니다.");
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
					path : nowPath,
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
				html = '<div class="btn-group btn-group-justified" role="group">\
				  			  <div class="btn-group file-delete" role="group">\
						    	<button type="button" class="btn btn-default">\
						    		<i class="fa fa-trash" aria-hidden="true"></i> 삭제\
				  			    </button>\
						  	  </div>\
							  <div class="btn-group file-rename" role="group">\
							    <button type="button" class="btn btn-default">\
							    	<i class="fa fa-pencil-square-o" aria-hidden="true"></i> 이름 바꾸기\
								</button>\
							  </div>\
							  <div class="btn-group file-move" role="group" data-toggle="modal" data-target="#moveFile">\
							    <button type="button" class="btn btn-default">\
							    	<i class="fa fa-arrows" aria-hidden="true"></i> 이동\
								</button>\
							  </div>\
							 </div>';
			};
			
						
			html += "<h1>\
						파일명 : "+selectNode.title+"\
					</h1>\
						수정한 날짜 : "+dateFormat(selectNode.updateDate)+"";
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
		
		//파일 이동
		$("body").on("click", ".file-move", function () {
			var key = $(this).parents("#detailFile").attr("data-key");
			var node = $("#tree").dynatree("getTree").getNodeByKey(key);
			
// 			$.ajax({
// 				type : "POST",
// 				url : "${pageContext.request.contextPath}/tree/filerename.json",
// 				data : {
// 					path : path,
// 					rename : rename.trim()
// 					},
// 				success : function (data) {
// 					if(data.result){
// 						alert("파일명 변경 성공");
// 						lazyReloadParent(parentKey)
// 					}else {
// 						alert("파일명 변경 실패!!");
// 					}
// 				}
// 			})
			
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
