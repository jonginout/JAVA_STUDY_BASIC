<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<link href="${pageContext.request.contextPath}/bootstrap/css/just.css" rel="stylesheet">

<script src="https://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	crossorigin="anonymous"></script>
	
	
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/waitme/waitMe.css"> --%>
<!-- <script type="text/javascript">
	$(function () {
		$(document)
		.ajaxStart(function () {
		    $("body").waitMe({
		        effect: 'bounce',
		        text : '로딩중 입니다..',
		        bg: 'rgba(255,255,255,0.7)', //바탕화면 약간 어둡
		        color: '#000'
		    });
		})
		.ajaxStop(function () {
		    $("body").waitMe("hide");
		})
	});
	
	$(function () {
		$.ajax({
			global : false,
			url : "${pageContext.request.contextPath}/msg/msgcount.do",
			success : function (d) {
				$("#notReadMsgCount").text(d);
			}
		})
	})
	
</script> -->
