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
									<div class="form-group text-center">
										<button id="login-formBtn" type="button" class="btn btn-primary btn-md">로그인</button>
										<button id="signup-formBtn" type="button" class="btn btn-primary btn-md">회원가입</button>
										
										<br /><br />
										<div id="naverIdLogin"></div>
<!-- 										<div id="naver_id_login"></div> -->

									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
    

<%@ include file="/WEB-INF/jsp/include/basic-js.jsp" %>

<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>

<script type="text/javascript">
	var localCallbackUrl = "http://localhost:8080/jongin-cloud/login/naverLogin.do";
	var Cafe24CallbackUrl = "http://chopo01.cafe24.com/login/naverLogin.do";
	const callbackUrl = nowHost=='localhost' ? localCallbackUrl : Cafe24CallbackUrl;

	var naverLogin = new naver.LoginWithNaverId(
		{
			clientId: "lbeEftb6cSDwLJdxPMJW",
			callbackUrl: callbackUrl,
			loginButton: {color: "green", type: 2, height: 40} /* 로그인 버튼의 타입을 지정 */
		}
	);
	
   /* 설정정보를 초기화하고 연동을 준비 */
	naverLogin.init();
	
</script>


<script>
	
	var isNull = true;
	$("#id,#pass").on("keyup", function () {
		var len = $(this).val().length;
		if(len>0){
			$(this).parent().removeClass("has-error").addClass("has-success");
			$(this).next().removeClass("glyphicon-remove").addClass("glyphicon-ok");
			isNull = false;
		}else {
			$(this).parent().removeClass("has-success").addClass("has-error");			
			$(this).next().removeClass("glyphicon-ok").addClass("glyphicon-remove");
			isNull = true;
		}
	})
	
	$("#login-formBtn").click(function () {
		if(!isNull){
			$.ajax({
				type : "POST",
				url : projectURL+"/login/login.json",
				data : {
					id : $("#id").val(),
					pass : $("#pass").val()
				},
				beforeSend : loadingAjax("로그인 중.."),
				success : function (data) {
					loadingStopAjax();
					if(!data.result){
						alert("일치하는 회원정보가 없습니다.")	
					}else {
						location.href = projectURL+"/cloud/cloud.do";
					}
				},
				error : function () {					
					alert("로그인 에러")	
				}
			})
		}
	})
	
	$("#signup-formBtn").click(function(){
		location.href = projectURL+"/login/signupform.do";
	})

</script>

</body>
</html>