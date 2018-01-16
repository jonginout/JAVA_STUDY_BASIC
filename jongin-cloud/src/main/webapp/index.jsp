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

	<div id="start-btn">
		<h1>CLOUD OF Z <i class="fa fa-cloud-download" aria-hidden="true"></i></h1>
		<div class="btn-container">
			<a class="btn">START</a>
		</div>
		<small id="copyright">Copyright © JONGIN All rights reserved.</small>
	</div>

</body>
</html>

<script type="text/javascript" src="${pageContext.request.contextPath}/lib/sweetalert/sweetalert.min.js"></script>

<script>


	$(function(){

		$("body").fadeIn(1400)

		$("body").on("click", "#start-btn", function(){
			var id = "${param.id}"
			if(id){
				swal({
					title: '클라우드 보안!',
					icon: "warning",
					text: id+"님의 비밀번호를 작성하세요:",
					type: "input",
					inputType : "password",
					showCancelButton: true,
					// cancelButtonText: "",
					closeOnConfirm: false,
					animation: "slide-from-top",
					showLoaderOnConfirm: true,
					inputPlaceholder: "비밀번호를 작성하세요."
				  }, function (inputValue) {
					if (inputValue === false) return false;
					if (inputValue === "") {
					  swal.showInputError("비밀번호를 작성하세요.");
					  return false
					}
					$.ajax({
						method : "post",
						url : projectURL+"/login/login.json",
						data : {
							id : id,
							pass : inputValue,
							save : true
						},
						success : function (data) {
							if(!data.result){
								swal({
									title: "Sorry!",
									text: "일치하는 회원정보가 없습니다.\n일반 로그인 하시겠습니까?",
									type: "warning",
									showCancelButton: true
								},
								function(){
									location.href = projectURL+"/login/loginform.do";
								})
								return
							}else {
								swal({
									title: "Nice!",
									text: "인증완료",
									type: "success",
									showCancelButton: true
								},
								function(){
									location.href = projectURL+"/cloud/cloud.do";
								});
								return
							}
						},
						error : function () {	
							swal("Sorry!", "서버에러 관리자에게 문의하세요", "warning");
							return						
						}
					})
				  }
				);
			}else{
				location.href = "${pageContext.request.contextPath}/cloud/cloud.do"
			}
		})
	})


</script>
