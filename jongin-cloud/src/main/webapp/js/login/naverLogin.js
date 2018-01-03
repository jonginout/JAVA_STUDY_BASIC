var localCallbackUrl = "http://localhost:8080/jongin-cloud/login/naverLogin.do";
var Cafe24CallbackUrl = "http://chopo01.cafe24.com/login/naverLogin.do";
var JonginCallbackUrl = "http://jongin.pe.kr/login/naverLogin.do";

var callbackUrl = localCallbackUrl
if(nowHost=='localhost'){
    callbackUrl = localCallbackUrl
}else if(nowHost=='chopo01.cafe24.com'){
    callbackUrl = Cafe24CallbackUrl
}else{
    callbackUrl = JonginCallbackUrl
}

var naverLogin = new naver.LoginWithNaverId(
    {
        clientId: "lbeEftb6cSDwLJdxPMJW",
        callbackUrl: callbackUrl,
        loginButton: {color: "green", type: 2, height: 30} /* 로그인 버튼의 타입을 지정 */
    }
);

/* 설정정보를 초기화하고 연동을 준비 */
naverLogin.init();