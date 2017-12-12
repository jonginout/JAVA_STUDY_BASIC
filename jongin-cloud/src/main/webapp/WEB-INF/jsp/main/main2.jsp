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
	$(document).ready(function() {
		$("#tree").dynatree({
			//아래와 배열과 같이 목록을 만들어줍니다. (isFolder : 폴더아이콘사용여부)
// 			children : 
// 				[
// 					{
// 						title: "Folder 2",
// 						isFolder: true,
// 						children: [
// 							{
// 								title: "Sub-item 2.1"
// 							},
// 							{
// 								title: "Folder 2",
// 								isFolder: true,
// 								children: [
// 									{
// 										title: "Sub-item 2.1"
// 									},
// 									{
// 										title: "Sub-item 2.2"
// 									}
// 								]
// 							}
// 						]
// 					}
// 				]
// 				,
			initAjax: {
			        url : "${pageContext.request.contextPath}/tree/list.json"
			        },
		
			//항목을 선택하면 해당 아이템의 이름을 state에 출력합니다.
			onActivate : function(node) {
				console.log(node.data)
				$("#state").html(node.data.title);
			}
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
