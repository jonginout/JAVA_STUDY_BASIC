var localCallbackUrl = "http://localhost:8080/jongin-cloud/login/naverLogin.do";
var Cafe24CallbackUrl = "http://chopo01.cafe24.com/login/naverLogin.do";
const callbackUrl = nowHost=='localhost' ? localCallbackUrl : Cafe24CallbackUrl;

var naverLogin = new naver.LoginWithNaverId(
    {
        clientId: "lbeEftb6cSDwLJdxPMJW",
        callbackUrl: callbackUrl,
        loginButton: {color: "green", type: 2, height: 30} /* 로그인 버튼의 타입을 지정 */
    }
);

/* 설정정보를 초기화하고 연동을 준비 */
naverLogin.init();