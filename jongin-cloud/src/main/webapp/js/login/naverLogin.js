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


$("#naverIdLogin").click(function(){
    location.href = "https://nid.naver.com/oauth2.0/authorize?client_id=lbeEftb6cSDwLJdxPMJW&response_type=code&redirect_uri="+callbackUrl
})

