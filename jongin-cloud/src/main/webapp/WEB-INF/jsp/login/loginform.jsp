<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="robots" content="noindex">
<%@ include file="/WEB-INF/jsp/include/basicinclude.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login/login.css">
</head>
<body>

<div class="st-header" class="navbar-fixed-top">
  <div class="fluid-cols">

    <div class="col-xs-8 col-sm-6 header-title-btn">
      <div class="st-header__title">
      	<span><strong><a href="${pageContext.request.contextPath}/cloud/cloud.do">
			CLOUD  Z <i class="fa fa-cloud-download" aria-hidden="true"></i>
      	</a></strong></span>
      </div>
    </div>
    
  </div>
</div> 

	<div class="st-wrapper">
		<div class="st-wrapper">
			<div class="st-auth">
				<div class="st-auth__cont">
					<div class="st-panel">
						<div class="st-panel__cont">
							<div class="st-panel__header">
								<div class="fluid-cols">
									<div class="expand-col">
										<span class="st-panel__title"><span>Login</span></span>
									</div>
								</div>
							</div>
							<div class="st-panel__content">
								<form class="form" id="login-form" action="${pageContext.request.contextPath}/login/login.do" method="post">
									<div class="form-group has-error has-feedback">
										<input name="id" id="id" class="form-control" type="text" placeholder="아이디">
										<span class="glyphicon glyphicon-remove form-control-feedback"></span>
									</div>
									<div class="form-group has-error has-feedback">
										<input name="pass" id="pass" class="form-control" type="password" placeholder="비밀번호">
										<span class="glyphicon glyphicon-remove form-control-feedback"></span>
									</div>
									<div class="form-group">
										<input id="save" name="save" type="checkbox">
										<label for="save">로그인 유지</label> 
									</div>
									<div class="form-group text-center">
										<button id="login-formBtn" type="button" class="btn btn-primary btn-md">로그인</button>
										<button id="signup-formBtn" type="button" class="btn btn-primary btn-md">회원가입</button>
									</div>
								</form>
							</div>
						</div>
					</div>
					<center>
						<a id="naverIdLogin" type="button" class="btn btn-md">NAVER</a>
						<a id="custom-login-btn" href="javascript:loginWithKakao()" type="button" class="btn btn-md">KAKAO</a>
					</center>
				</div>
			</div>
		</div>
	</div>
    

<%@ include file="/WEB-INF/jsp/include/basic-js.jsp" %>

<script>

	$(function(){
		var hiddenId = '${member.id}';
		var hiddenPw = '${member.pass}';
		if(hiddenId && hiddenPw){
			console.log(hiddenId,hiddenPw)
			$.ajax({
				type : "POST",
				url : projectURL+"/login/login.json",
				data : {
					id : hiddenId,
					pass : hiddenPw,
					save : true
				},
				beforeSend : loadingAjax("비트 SNS로 로그인 접근중..."),
				success : function (data) {
					loadingStopAjax();
					if(!data.result){
						alert("일치하는 회원정보가 없습니다.")	
					}else {
						location.href = projectURL+"/cloud/cloud.do";
					}
				},
				error : function () {	
					loadingStopAjax();				
					alert("로그인 에러")	
				}
			})
		}
	})

</script>

<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src="${pageContext.request.contextPath}/js/login/kakaoLogin.js"></script>


<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/login/naverLogin.js"></script>


<script src="${pageContext.request.contextPath}/js/login/login.js"></script>


</body>
</html>