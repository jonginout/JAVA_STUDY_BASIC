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

<link href="${pageContext.request.contextPath}/css/forum/info.css" rel="stylesheet" type="text/css" id="skinSheet">

</head>
<body>

	<%@ include file="/WEB-INF/jsp/include/header.jsp"%>

	<center>
		<div class="st-panel">
			<div class="st-panel__cont text-center" style="min-height: 590px !important; max-width: 360px !important;">
				<div class="st-panel__header">
					<div class="fluid-cols">
						<div class="expand-col">
							<span class="st-panel__title">
								<h1><strong>CLOUD OF Z <i class="fa fa-cloud-download" aria-hidden="true"></i></strong></h1>
							</span>
						</div>
					</div>
				</div>
				<br>
				<div class="st-panel__content">
					개발자 : 갓종인

					<br><br>
					<div id="map"></div>

					<br><br>
					CLOUD OF Z 는 그냥 만들었습니다. <br>
					감사합니다..

					<br><br>
					<button class="btn btn-default btn-sm kakao-share-btn" onclick="javascript:sendLink();">
						<i class="fa fa-share-alt" aria-hidden="true"></i> COZ 알리기
					</button>
				</div>
			</div>
			<br>
				<div class="form-group text-center">
					<a class="btn btn-default" onclick="history.back();"> 
                        <i class="fa fa-angle-left"></i> &nbsp;&nbsp;Back
					</a>
				</div>
			</div>
	</center>

	<%@ include file="/WEB-INF/jsp/include/basic-js.jsp"%>

	<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d97a3b08e5637c89bd3b046a6e13e268"></script>

	<script>

	//<![CDATA[
			// // 사용할 앱의 JavaScript 키를 설정해 주세요.
			Kakao.init('d97a3b08e5637c89bd3b046a6e13e268');
			// // 카카오링크 버튼을 생성합니다. 처음 한번만 호출하면 됩니다.
			function sendLink() {
			Kakao.Link.sendDefault({
				objectType: 'feed',
				content: {
				title: 'CLOUD OF Z',
				description: 'Copyright © JONGIN All rights reserved.',
				imageUrl: 'http://postfiles14.naver.net/MjAxODAxMDdfMjI5/MDAxNTE1MzM0MjI3NTc5.O1aFNsRAa-SQzrMNjUmKXVktv00NhDpzWecKgWuFey8g.cGOHY4o96sxhES1UJKr4hJMuEVhsJagabO64DeZ1tFUg.PNG.jonginout/coz.png?type=w2',
				link: {
					mobileWebUrl: 'http://jongin.pe.kr',
					webUrl: 'http://jongin.pe.kr'
				}
				},
				social: {
				likeCount: 286,
				commentCount: 45,
				sharedCount: 845
				},
				buttons: [
				{
					title: 'HOME',
					link: {
					mobileWebUrl: 'http://jongin.pe.kr',
					webUrl: 'http://jongin.pe.kr'
					}
				},
				{
					title: 'SIGN UP',
					link: {
					mobileWebUrl: 'http://jongin.pe.kr/login/signupform.do',
					webUrl: 'http://jongin.pe.kr/login/signupform.do'
					}
				}
				]
			});
		}
		//]]>

		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = { 
			center: new daum.maps.LatLng(37.498414, 127.028604), // 지도의 중심좌표
			level: 3 // 지도의 확대 레벨
		};

		var map = new daum.maps.Map(mapContainer, mapOption);

		// 마커가 표시될 위치입니다 
		var markerPosition  = new daum.maps.LatLng(37.498414, 127.028604); 

		// 마커를 생성합니다
		var marker = new daum.maps.Marker({
			position: markerPosition
		});

		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);

		var iwContent = '<div style="padding:5px;"> COZ 강남역 12 출구 </div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
		iwPosition = new daum.maps.LatLng(37.498414, 127.028604); //인포윈도우 표시 위치입니다

		// 인포윈도우를 생성합니다
		var infowindow = new daum.maps.InfoWindow({
			position : iwPosition, 
			content : iwContent 
		});

		infowindow.open(map, marker);

	</script>
	
</body>
</html>