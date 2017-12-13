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


<script type="text/javascript">

	var nowPath = "";

	function showChildList(node) {
		var childs = node.childList;
		var html = "<h1><i class='fa fa-folder-open' aria-hidden='true'></i> "+node.data.title+"</h1>";
		
		
		childs && (childs = childs.length < 1 ? null : childs);
		if(childs){	
			for ( var key in childs) {
		    	 if(!childs[key].data.isFolder){	
					html += "<h3><i class='fa fa-file-o' aria-hidden='true'></i> "+childs[key].data.title+"</h3>";
			      }else {
					html += "<h3><i class='fa fa-folder-o' aria-hidden='true'></i> "+childs[key].data.title+"</h3>";
				}
			}
		}else {
			html += "<h3><i class='fa fa-exclamation-circle' aria-hidden='true'></i> 파일이 없습니다.</h3>";
		}
		$("#echoActive").html(html);
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
					alert(node.data.title)
		    	  }else {
					showChildList(node)
				}	      
	    	},
	      onLazyRead: function(node){
	        node.appendAjax({
	          url: "${pageContext.request.contextPath}/tree/sublist.json",
	          data : { path : node.data.path },
	          debugLazyDelay: 200,
	          success : function (node) {
	        	  console.log(node)
	        	  showChildList(node)
				}
	        });
	      },
// 	      onClick : function (node) {
// 		  },
	      minExpandLevel: 2,
	      debugLevel: 0
	    });
	}


	$(function(){
		dirTree();
    });
</script>
</head>

<body class="example">
	<h1>뭐먹지조 클라우드</h1>
	<p>
		<button id="reload">클라우드 새로고침</button>
		<button id="newFolder">현재위치에 새폴더</button>
	</p>
	
	<div id="tree"></div>

	<div id="echoActive">
		
	</div>


	
	<script type="text/javascript">
		$("#reload").click(function () {
			$("#echoActive").html("");
			$("#tree").dynatree("destroy");
			dirTree();
		})
		
		$("#newFolder").click(function () {
			if(!nowPath){
				alert("선택된 폴더가 없습니다.");
				return false;
			}
			var newFolderName = prompt("새폴더 이름을 작성하세요.");
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
					$("#echoActive").html("");
					lazyReload();
				}
			})
		})

		function lazyReload() {
			var node = $("#tree").dynatree("getActiveNode");
			if (node && node.isLazy()) {
				node.reloadChildren(function(node, isOk) {
				});
			} else {
				alert("Please activate a lazy node first.");
			}
		};
		
	</script>
</body>
</html>
