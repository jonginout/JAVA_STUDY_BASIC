<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

<link href="${pageContext.request.contextPath}/dynatree-master/src/skin/ui.dynatree.css" rel="stylesheet" type="text/css" id="skinSheet">
<script src="${pageContext.request.contextPath}/dynatree-master/src/jquery.dynatree.js"></script>

<script type="text/javascript">
$(function(){
	 
    // --- Initialize sample trees
    $("#tree").dynatree({
      title: "Lazy loading sample",
      fx: { height: "toggle", duration: 200 },
      autoFocus: false, // Set focus to first child, when expanding or lazy-loading.
      // In real life we would call a URL on the server like this:
//          initAjax: {
//              url: "/getTopLevelNodesAsJson",
//              data: { mode: "funnyMode" }
//              },
      // .. but here we use a local file instead:
      initAjax: {
        url: "${pageContext.request.contextPath}/tree/list.json"
        },
 
      onActivate: function(node) {
        $("#echoActive").text("" + node + " (" + node.getKeyPath()+ ")");
      },
 
      onLazyRead: function(node){
        // In real life we would call something like this:
//              node.appendAjax({
//                  url: "/getChildrenAsJson",
//                data: {key: node.data.key,
//                       mode: "funnyMode"
//                         }
//              });
        // .. but here we use a local file instead:
        node.appendAjax({
          url: "${pageContext.request.contextPath}/tree/list.json",
          // We don't want the next line in production code:
          debugLazyDelay: 750
        });
      }
    });
    $("#btnReloadActive").click(function(){
      var node = $("#tree").dynatree("getActiveNode");
      if( node && node.isLazy() ){
        node.reloadChildren(function(node, isOk){
        });
      }else{
        alert("Please activate a lazy node first.");
      }
     });
    $("#btnLoadKeyPath").click(function(){
      var tree = $("#tree").dynatree("getTree");
      // Make sure that node #_27 is loaded, by traversing the parents.
      // The callback is executed for every node as we go:
      tree.loadKeyPath("/folder4/_23/_26/_27", function(node, status){
        if(status == "loaded") {
          // 'node' is a parent that was just traversed.
          // If we call expand() here, then all nodes will be expanded
          // as we go
          node.expand();
        }else if(status == "ok") {
          // 'node' is the end node of our path.
          // If we call activate() or makeVisible() here, then the
          // whole branch will be exoanded now
          node.activate();
        }
      });
     });
  });

</script>
</head>

<body>
	<div id="tree">
		<!-- dynatree 를 출력하는 곳입니다. -->
	</div>
	<div>
		<!-- dynatree 의 정보들을 출력하는 곳입니다. -->
		선택한 노드 : <span id="state">-</span>
	</div>
</body>
</html>
