<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="robots" content="noindex">
	<%@ include file="/WEB-INF/jsp/include/basicinclude.jsp" %>
</head>

<body style="background: #1276AA; display: none">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index/index.css">

	<!-- <form id="form" method="post" action="${pageContext.request.contextPath}/login/init.json">
		<input type="hidden" id="hidden-id" name="id" value="chopo01">
		<input type="hidden" id="hidden-pw" name="pass" value="chvy918">
	</form> -->
	<div id="start-btn">
		<h1>CLOUD OF Z <i class="fa fa-cloud-download" aria-hidden="true"></i></h1>
		<div class="btn-container">
			<a class="btn">START</a>
		</div>
		<small id="copyright">Copyright © JONGIN All rights reserved.</small>
	</div>

</body>
</html>

<script>


	$(function(){

		$("body").fadeIn(1400)

		$("body").on("click", "#start-btn", function(){
			// $("#form").submit();
			location.href = "${pageContext.request.contextPath}/cloud/cloud.do"
		})
	})


</script>
