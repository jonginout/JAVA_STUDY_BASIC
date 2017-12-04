/*

    http 모듈을 이용한 서버 통신 이해하기

*/
var http = require("http");
var port = 10001;

// 톰캣 , 아파치 서버와도 비슷하다.
// 서버를 생성 (서버 역할)
var server = http.createServer();

// 사용자 접속시에 실행 // 모듈에서 자동 발생(emit)해주는 이벤트 on
server.on("request", function () {
    console.log("사용자 요청옴...");
})

server.listen(port);
console.log("서버 구동중...");
