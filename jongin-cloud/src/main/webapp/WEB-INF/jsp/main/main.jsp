<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

<link
	href="${pageContext.request.contextPath}/dynatree-master/src/skin/ui.dynatree.css"
	rel="stylesheet" type="text/css" id="skinSheet">
<script
	src="${pageContext.request.contextPath}/dynatree-master/src/jquery.dynatree.js"></script>

<script type="text/javascript">
	function dirTree() {
		console.log("트리 로딩 완료..")
	    $("#tree").dynatree({
	      title: "김종인님의 클라우드",
	      fx: { height: "toggle", duration: 200 },
	      autoFocus: false,
	      initAjax: {
	        url: "${pageContext.request.contextPath}/tree/list.json"
	        },

	      onActivate: function(node) {
	    	console.log(node.data)
	        $("#echoActive").text("" + node + " (" + node.getKeyPath()+ ")");
	      },
	      onLazyRead: function(node){
	        node.appendAjax({
	          url: "${pageContext.request.contextPath}/tree/sublist.json",
	          data : { path : node.data.path },
	          debugLazyDelay: 200
	        });
	      },
	      onClick : function (node) {
	    	  if(!node.data.isFolder){	    		  
				alert(node.data.title)
	    	  }
		  },
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
	<h1>김종인님의 클라우드</h1>

	<div id="tree"></div>

	<div>
		Active node: <span id="echoActive">-</span>
	</div>

	<p>
		<button>reload</button>
	</p>
	
	<script type="text/javascript">
		$("button").click(function () {
			$("#tree").dynatree("destroy");
			dirTree();
		})
	</script>
</body>
</html>
