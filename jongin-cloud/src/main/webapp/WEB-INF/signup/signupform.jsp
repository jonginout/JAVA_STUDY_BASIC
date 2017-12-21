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

  <%@ include file="/WEB-INF/jsp/include/header.jsp" %>


		<div class="st-wrapper">
		<div class="st-wrapper">
			<div class="st-auth">
				<div class="st-auth__cont">
					<div class="st-panel">
						<div class="st-panel__cont">
							<div class="st-panel__header">
								<div class="fluid-cols">
									<div class="expand-col">
										<span class="st-panel__title">
											<span>Create Account</span>
										</span>
									</div>
								</div>
							</div>
							<div class="st-panel__content">
								<form id="signup-form" class="form" action="${pageContext.request.contextPath}/signup/signup.do" method="post" enctype="multipart/form-data">
								
									<div class="form-group has-error has-feedback" data-url="${pageContext.request.contextPath}">
	                                  <input class="form-control" id="id" name="id" placeholder="아이디">
	                                  <span class="glyphicon glyphicon-remove form-control-feedback"></span>
	                                </div>
									<div class="form-group">
										<input id="pass" name="pass" class="form-control" type="password" placeholder="비밀번호"/>
									</div>
									<div class="form-group">
										<input id="passChk" class="form-control" type="password" placeholder="비밀번호"/>
									</div>
									<div class="form-group">
										<input id="name" name="name" class="form-control" type="text" placeholder="이름"/>
									</div>
									<div class="form-group">
										<!-- <input id="name" name="name" class="form-control" type="text" placeholder="이름"/> -->
										<span class="gender-span">성별 : </span>
										<label class="radio-inline"><input name="gender" type="radio" value="m">남성</label>
										<label class="radio-inline"><input name="gender" type="radio" value="w">여성</label>
									</div>
									<div class="form-group">
										<div class="input-group">
											<input name="postCode" type="text" class="form-control" id="sample6_postcode" placeholder="우편번호">
											<span class="input-group-btn">
												<button class="btn btn-secondary" type="button" onclick="sample6_execDaumPostcode()">우편번호 찾기</button>
											</span>
										</div>
									</div>
									<div class="form-group">
										<input name="address1" type="text" class="form-control" id="sample6_address" placeholder="주소">
									</div>
									<div class="form-group">
										<input name="address2" type="text" class="form-control" id="sample6_address2" placeholder="상세주소">
									</div>
									<div class="form-group">
										<div class="row">
											<div class="form-group col-md-6">
												<input class="form-control" name="email1" id="email1" type="text" placeholder="이메일"/>
											</div>
											<div class="form-group col-md-1">@</div>
											<div class="form-group col-md-5">
												<input class="form-control" name="email2" id="email2" type="text" placeholder="도메인 주소" />
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<select name="curri" id="curri" class="form-control">
												<optgroup label="커리큘럼을 선택해주세요">
													<c:forEach var="i" items="${curriList}">
														<option value="${i.curriNo}">${i.name} (${i.startDateYear}-${i.startDateMonth}-${i.startDateDay} ~ ${i.endDateYear}-${i.endDateMonth}-${i.endDateDay})</option>
													</c:forEach>
												</optgroup>
											</select>
										</div>
									</div>
									<div class="form-group">
										<div class="col-md-12">
											<div class="panel panel-default">
												<div class="panel-heading">
													<strong>Profile Image</strong> <small> </small>
												</div>
												<div class="panel-body">
													<div class="input-group image-preview">
														<input placeholder="" type="text" name="profileInfo"
															class="form-control image-preview-filename" disabled="disabled">
														<span class="input-group-btn">
															<button type="button" class="btn btn-default image-preview-clear"
																style="display: none;">
																<span class="glyphicon glyphicon-remove"></span> Clear
															</button>
															<div class="btn btn-default image-preview-input">
																<span class="glyphicon glyphicon-folder-open"></span>
																<span class="image-preview-input-title">Browse</span>
																<input id="profileImg" name="profileImg" type="file" accept="image/png, image/jpeg, image/gif, image/jpg" />
															</div>
														</span>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="form-group text-center">
										<button id="signup-formBtn" type="button" class="btn btn-primary btn-lg">회원가입</button>
									</div>
								</form>
							</div>
						</div>
					</div>
					<div class="st-auth__back">
						<a class="btn btn-default" href="${pageContext.request.contextPath}/login/loginform.do">
							<i class="fa fa-angle-left"></i>
							&nbsp;&nbsp;Back to Login
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
    

<%@ include file="/WEB-INF/jsp/include/basic-js.jsp" %>

</body>
</html>