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
<%@ include file="/WEB-INF/jsp/include/basicinclude.jsp"%>
<script src="${pageContext.request.contextPath}/js/signup/daum-address-api.js"></script>
<script src="${pageContext.request.contextPath}/js/signup/signup.js" type="text/javascript"></script>

</head>
<body>

	<%@ include file="/WEB-INF/jsp/include/header.jsp"%>

	<center>
		<div class="st-panel" style="margin: 68px 10px 20px 10px !important">
			<div class="st-panel__cont text-center" style="min-height: 590px !important; max-width: 360px !important;">
				<div class="st-panel__header">
					<div class="fluid-cols">
						<div class="expand-col">
							<span class="st-panel__title"> <span>Create Account</span>
							</span>
						</div>
					</div>
				</div>
				<div class="st-panel__content">
					<form id="signup-form" enctype="multipart/form-data">
	
						<div class="form-group has-error has-feedback" >
							<input class="form-control" id="id" name="id" placeholder="아이디">
							<span class="glyphicon glyphicon-remove form-control-feedback"></span>
						</div>
						<div class="form-group">
							<input id="pass" name="pass" class="form-control" type="password"
								placeholder="비밀번호" />
						</div>
						<div class="form-group">
							<input id="passChk" class="form-control" type="password"
								placeholder="비밀번호 확인" />
						</div>
						<div class="form-group">
							<input id="name" name="name" class="form-control" type="text"
								placeholder="이름" />
						</div>
						<div class="form-group">
							<div class="input-group">
								<input name="postCode" type="text" class="form-control"
									id="sample6_postcode" placeholder="우편번호"> <span
									class="input-group-btn">
									<button class="btn btn-secondary" type="button"
										onclick="sample6_execDaumPostcode()">우편번호 찾기</button>
								</span>
							</div>
						</div>
						<div class="form-group">
							<input name="address1" type="text" class="form-control"
								id="sample6_address" placeholder="주소">
						</div>
						<div class="form-group">
							<input name="address2" type="text" class="form-control"
								id="sample6_address2" placeholder="상세주소">
						</div>
						<div class="form-group">
							<div class="row">
								<div class="form-group col-xs-6">
									<input class="form-control" name="email1" id="email1"
										type="text" placeholder="이메일" />
								</div>
								<div class="form-group col-xs-1">@</div>
								<div class="form-group col-xs-5">
									<input class="form-control" name="email2" id="email2"
										type="text" placeholder="도메인 주소" />
								</div>
							</div>
						</div>
						<div class="form-group tel-input">
							<div class="input-group">
								<input name="tel" type="text" class="form-control" 
								id="tel" placeholder="휴대폰 번호 '-'포함! ">
								<span class="input-group-btn">
									<button class="btn btn-secondary" type="button" 
									id="sendSMSBtn">인증키 전송</button>
								</span>
							</div>
						</div>
						<div class="form-group auth-input" style="display:none;">
								<div class="input-group">
									<input name="key" type="text" class="form-control" 
									id="key" placeholder="인증키 입력">
									<span class="input-group-btn">
										<button class="btn btn-secondary" type="button" 
										id="authKeyBtn">키 인증</button>
									</span>
								</div>
							</div>
						<div class="form-group">
							<label class="pull-left">프로필 사진</label>
							<input id="images" name="images" type="file" accept="image/png, image/jpeg, image/gif, image/jpg" />							
						</div>
						<br />
						<div class="form-group text-center">
							<div class="col-md-12">
								<a id="signup-formBtn" class="btn btn-primary btn-md btn-block">회원가입</a>
							</div>
						</div>
					</form>
				</div>
			</div>
			<br>
				<div class="form-group text-center">
					<a class="btn btn-default"
						href="${pageContext.request.contextPath}/login/loginform.do"> <i
						class="fa fa-angle-left"></i> &nbsp;&nbsp;Back to Login
					</a>
				</div>
			</div>
	</center>

	<%@ include file="/WEB-INF/jsp/include/basic-js.jsp"%>

</body>
</html>