 //<![CDATA[
    // 사용할 앱의 JavaScript 키를 설정해 주세요.
    Kakao.init('d97a3b08e5637c89bd3b046a6e13e268');
    function loginWithKakao() {
      // 로그인 창을 띄웁니다.
      Kakao.Auth.login({
        success: function(authObj) {
					kakaoLogin(authObj)
        },
        fail: function(err) {
          alert(JSON.stringify(err));
        }
      });
    };
  //]]>

  function kakaoLogin(authObj){

	$.ajax({
			type : "POST",
			url : projectURL+"/login/kakao.json",
			data : {
				accessToken : authObj.access_token
			},
			success : function(data){
				var user = JSON.parse(data);

				var kaccount_email = user.kaccount_email.split("@");
				kaccount_email = kaccount_email[0]

				$.ajax({
				type : "POST",
				url : projectURL+"/login/login.json",
				data : {
					id : kaccount_email,
					name : user.properties.nickname,
					email : user.kaccount_email,
					profileImg : user.properties.profile_image,
					type : "KAKAO",
					save : "true"
				},
				beforeSend : loadingAjax("카카오 연동중.."),
				dataType : "json",
				success : function (data) {
					if(data.result){
						location.href = projectURL+"/cloud/cloud.do"
					}
				},
				error : function(){
					alert("카카오 로그인 오류")
					location.href = projectURL+"/login/loginform.do"
				}
			})
			},
			error : function(data){
				alert("카카오 연동 실패!")
				loadingStopAjax()
			}
		  })
  }
