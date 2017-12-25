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
<body style="background: #1276AA;">
	<style>
		.content{
			position: relative;
			top : 38%;
			display: none;
		}
		#logo {
			color: white;
			text-align: center;
			margin: 0px;
			font-size: 45px;
			top: 50%;
		}
		button{
			margin-top : 15px;
			margin-bottom: 8px;
		}
		#copyright{
			color: white;
			font-size: 9px;
		}
	</style>
	<div class="col-xs-12 content">
		<h1 id="logo">
			<strong>
				CLOUD  Z <i class="fa fa-cloud-download" aria-hidden="true"></i>
			</strong>
		</h1>
		<div class="col-xs-12 col-md-4 col-md-push-4">
			<button type="button" class="btn btn-default btn-md btn-block">GETTING STARTED</button> 
			<small id="copyright">Copyright © JONGIN All rights reserved.</small>
		</div>		
	</div>
	
	<script type="text/javascript">
		$(function () {			
			$(".content").fadeIn(1400)
			
			
			var url = "${pageContext.request.contextPath}/login/loginform.do";
			if("${user}"){
				url = "${pageContext.request.contextPath}/cloud/cloud.do"
			}
			
			$("body").on("click", "button", function () {
				location.href = url;
			})
		})
	</script>
</body>
</html>



<!-- <h1>홈화면으로 리다이렉션 합니다.</h1> -->
<%
//response.sendRedirect("cloud/cloud.do");
%>