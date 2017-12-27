<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 절대 삭제 금지  절대 삭제 금지  절대 삭제 금지  절대 삭제 금지  절대 삭제 금지  절대 삭제 금지  절대 삭제 금지  절대 삭제 금지  -->

<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/bootstrap/libs/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/libs/font-awesome/css/font-awesome.min.css" rel="stylesheet"><!-- Common Libs CSS -->
<link href="${pageContext.request.contextPath}/bootstrap/css/libs.common.min.css" rel="stylesheet"><!-- Page Libs CSS --><!-- Just Bootstrap Theme -->


<link href="${pageContext.request.contextPath}/bootstrap/css/jquery.fancybox.css" rel="stylesheet"> 
<link href="${pageContext.request.contextPath}/bootstrap/css/jquery.fancybox-thumbs.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/cropper.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-duallistbox.min.css" rel="stylesheet"><!-- Just Bootstrap Theme -->
<link href="${pageContext.request.contextPath}/bootstrap/css/just.min.css" rel="stylesheet">

<!-- 제이쿼리-->
<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>

<!-- UI 기본 js 파일  -->
<script src="${pageContext.request.contextPath}/bootstrap/js/libs.common.js"></script>

<!-- 제이쿼리 UI -->
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js" integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU=" crossorigin="anonymous"></script>

<!-- <script src="${pageContext.request.contextPath}/lib/mobile-touch/touch-punch.min.js"></script> -->

<!-- 웨잇미 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/waitme/waitMe.css">

<!-- 종인 정의 CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/include/basic-css.css">

<!-- 절대 삭제 금지  절대 삭제 금지  절대 삭제 금지  절대 삭제 금지  절대 삭제 금지  절대 삭제 금지  절대 삭제 금지  절대 삭제 금지  -->


<title>CLOUD OF Z</title>

<script type="text/javascript">
const nowHost = location.hostname.toLowerCase();
const projectURL = nowHost=='localhost' ? "/jongin-cloud" : "";

	function loadingAjax(text){
			$("body").waitMe({
			effect: 'bounce',
			text : text,
			bg: 'rgba(255,255,255,0.7)', //바탕화면 약간 어둡
			color: '#1276AA'
		});
	}
	function loadingStopAjax(){
		$("body").waitMe("hide");
	}

	// $(function () {
	// 	$(document)
	// 	.ajaxStart(function () {
	// 	    $("body").waitMe({
	// 	        effect: 'bounce',
	// 	        text : '로딩중 입니다..',
	// 	        bg: 'rgba(255,255,255,0.7)', //바탕화면 약간 어둡
	// 	        color: '#1276AA'
	// 	    });
	// 	})
	// 	.ajaxStop(function () {
	// 	    $("body").waitMe("hide");
	// 	})
	// });


	var userOri = "${user}"=="" ? null : "${user}"
	var userTmp = userOri.substring(userOri.indexOf("[")+1, userOri.lastIndexOf("]")) 
	userOri = {};
	userTmp = userTmp.split(", ")
	for(var u of userTmp){
		var tmp = u.split("=")
		userOri[tmp[0]]=tmp[1]
		if(tmp[0]=='profileImg' && tmp[1]==''){
			userOri[tmp[0]] = '/img/forum/default_profile.png'
		}
	}	
	const user = userOri;

	// $(function(){
	// 	$("#user-profile-img").css({
	// 		"background-image" : "url('${pageContext.request.contextPath}/common/down.do?ext=img&path="+projectURL+user.profileImg+"')"
	// 	})
	// })
</script>