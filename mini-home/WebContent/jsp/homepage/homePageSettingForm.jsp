<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/jsp/include/basicInclude.jsp"%>
</head>
<body>



	<div class="row justify-content-center">

		<div class="header">
			<c:import url="/jsp/include/header.jsp" />
		</div>

		<div class="content row">
			<div class="container">
				<center>
					<p class="h1">홈페이지 설정</p>

					<c:if test="${error!=null}">
						<div class="alert alert-danger col-md-6" role="alert">${ error }</div>
					</c:if>
				</center>

				<!-- 홈피 셋팅 -->

				<form
					action="${pageContext.request.contextPath}/jsp/homepage/homepagesetting?homepage_no=${homePage.homepageNo}"
					method="post" name="form" enctype="multipart/form-data">


					<div class="form-group col-md-12" style="margin-bottom: 10px">
						<div class="form-check form-check-inline">
						  <label class="form-check-label">
						    <input class="form-check-input" type="radio" name="open_range" id="inlineRadio1" value="PUBLIC">
						    	전체공개
						  </label>
						</div>
						<div class="form-check form-check-inline">
						  <label class="form-check-label">
						    <input class="form-check-input" type="radio" name="open_range" id="inlineRadio2" value="FRIEND">
						    	친구공개
						  </label>
						</div>
						<div class="form-check form-check-inline">
						  <label class="form-check-label">
						    <input class="form-check-input" type="radio" name="open_range" id="inlineRadio3" value="PRIVATE">
						    	나만보기
						  </label>
						</div>
					</div>



					<div class="form-group col-md-12">
						<label>홈피 이름</label> 
						<input type="text" name="title" 
						class="form-control" value="${homePage.title}">
					</div>
					<div class="form-group col-md-12">
						<label>홈피 소개글</label>
						<textarea name="introduce" cols="30" rows="5" class="form-control">${homePage.introduce}</textarea>
					</div>
<!--파일부분파일부분파일부분파일부분파일부분파일부분파일부분 -->
					<div class="form-group col-md-12">
						<label>홈피 사진</label>
						<c:choose>
							<c:when test="${empty homePage.profile}">
								<input type="file" name="profile" class="form-control">
							</c:when>
							<c:otherwise>
								${profile}
								<button type="button" class="btn btn-sm btn-secondary" onclick="fileDel('profile')">삭제</button>
								<input type="hidden" value="${homePage.profile}" name="tmpProfile"/>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="form-group col-md-12">
						<label>홈피 배경 사진</label>
						<c:choose>
							<c:when test="${empty homePage.backgroundImg}">
								<input type="file" name="backgroundImg" class="form-control">
							</c:when>
							<c:otherwise>
								${backgroundImg}
								<button type="button" class="btn btn-sm btn-secondary" onclick="fileDel('background_img')">삭제</button>
								<input type="hidden" value="${homePage.backgroundImg}" name="tmpBackgroundImg"/>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="form-group col-md-12">
						<label>홈피 배경음악</label>
						<c:choose>
							<c:when test="${empty homePage.bgm}">
								<input type="file" name="bgm" class="form-control">
							</c:when>
							<c:otherwise>
								${bgm}
								<button type="button" class="btn btn-sm btn-secondary" onclick="fileDel('bgm')">삭제</button>
								<input type="hidden" value="${homePage.bgm}" name="tmpBgm"/>
							</c:otherwise>
						</c:choose>
					</div>

<!--파일부분파일부분파일부분파일부분파일부분파일부분파일부분 -->
					<div class="form-row col-md-12">
						<div class="form-group col-md-3">
							<label class="custom-control custom-checkbox"> 
								<input type="checkbox" class="custom-control-input" value="Y" 
								name="photoGallaryUseYn" id="photoGallaryUseYn">
								<span class="custom-control-indicator"></span> 
								<span class="custom-control-description">사진 갤러리 사용여부</span>
							</label>
						</div>
						<div class="form-group col-md-3">
							<label class="custom-control custom-checkbox"> 
								<input type="checkbox" class="custom-control-input" value="Y"
								name="fileGallaryUseYn" id="fileGallaryUseYn">
								<span class="custom-control-indicator"></span>
								<span class="custom-control-description">파일 갤러리 사용여부</span>
							</label>
						</div>
						<div class="form-group col-md-3">
							<label class="custom-control custom-checkbox">
								<input type="checkbox" class="custom-control-input" value="Y"
								name="guestBookUseYn" id="guestBookUseYn"> 
								<span class="custom-control-indicator"></span>
								<span class="custom-control-description">방명록 사용여부</span>
							</label>
						</div>
						<div class="form-group col-md-3">
							<label class="custom-control custom-checkbox">
								<input type="checkbox" class="custom-control-input" value="Y"
								name="diaryUseYn" id="diaryUseYn">
								<span class="custom-control-indicator"></span>
								<span class="custom-control-description">다이어리 사용여부</span>
							</label>
						</div>
					</div>
					<div class="form-row col-md-12">
						<div class="form-group col-md-3">
							<button type="button" onclick="reset();chkBox()"
							 class="btn btn-primary btn-lg btn-block">
								초기화
							</button>
						</div>
						<div class="form-group col-md-3">
							<button type="button" onclick="back()"
							class="btn btn-primary btn-lg btn-block">
								뒤로
							</button>
						</div>
						<div class="form-group col-md-6">
							<button type="button" onclick="setting()"
							class="btn btn-warning btn-lg btn-block">
								변경사항 저장하기
							</button>
						</div>
						<button class="btn btn-danger btn-lg btn-block" type="button" 
						style="margin-top:30px;margin-bottom: 20px" onclick="destroyHomePage()">
							홈페이지 삭제하기
						</button>
					</div>
				</form>

				<!-- 홈피 셋팅 -->

			</div>
		</div>

		<div class="footer">
			<c:import url="/jsp/include/footer.jsp" />
		</div>

	</div>

	<script type="text/javascript">		
		function opneHome() {
			var openRange = '${homePage.openRange}';
			if(openRange=='PRIVATE'){
				$('input:radio[name="open_range"][value="PRIVATE"]').prop('checked', true);
			}else if (openRange=='FRIEND') {
				$('input:radio[name="open_range"][value="FRIEND"]').prop('checked', true);
			}else {
				$('input:radio[name="open_range"][value="PUBLIC"]').prop('checked', true);
			}
		}
		function chkBox() {
			var myUseYnArr = [  ${homePage.photoGallaryUseYn},
								${homePage.fileGallaryUseYn},
								${homePage.guestBookUseYn},
								${homePage.diaryUseYn} ];
			var checkBoxArr = [ "photoGallaryUseYn",
								"fileGallaryUseYn",
								"guestBookUseYn",
								"diaryUseYn"];
			for (var i = 0; i < myUseYnArr.length; i++) {
				var checkBox = checkBoxArr[i];
				var myUseYn = myUseYnArr[i];
				$("input:checkbox[name="+checkBox+"]").each(function() {
					if (myUseYn) { 
						this.checked = true; 
					}
				});
			}
		}
		chkBox();
		opneHome();
		$("ul.nav.navbar-nav > li").removeClass(); //모든 li 클래스를 다 지우고
		$("ul.nav.navbar-nav > li:eq(2)").addClass("active"); // 추가
		
		function back() {
			location.href 
			= "${pageContext.request.contextPath}/jsp/homepage/homepage?homepage_no=${homePage.homepageNo}";
		}
		function setting() {
			if(confirm('이대로 변경사항을 저장합니다.')){
				form.submit();
				return;
			}
			return false;
		}
		function fileDel(field) {
			if(!confirm('정말로 파일을 삭제 하시겠습니까?')){return}
			location.href 
			= "${pageContext.request.contextPath}/jsp/homepage/homepagedeletefile?homepage_no=${homePage.homepageNo}&field="+field;
		}
		function destroyHomePage() {
			var person = prompt("'홈피삭제'라고 입력해주세요.", "");
			if (person=="홈피삭제") {
				location.href 
				= "${pageContext.request.contextPath}/jsp/homepage/homepagedestroy?homepage_no=${homePage.homepageNo}";
			}else {
				alert('입력오류');
			}
		}
	</script>
</body>
</html>