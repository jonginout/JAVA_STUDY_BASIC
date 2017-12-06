// 프레임 워크
const express = require('express');
var app = express();

// 미들웨어 사용하기
// 특정한 기능을 수행하는 미들웨어를 use에서 사용

// 예를 들면
// 세션 처리하는 기능 호출
app.use(function (req, res, next) {

    console.log("1번 use 함수 호출");
    req.msg = "1번 use ";

    //next() 를 안부르면 여기서 멈춘다.
    next(); // 나 다음 미들웨어로 이동해라
    //err 객체를 주면은
    //use 중에서 첫번쨰 매개변수에
    //err 가 있는 use로 보낸다.
});

// 파일 처리하는 기능 호출
app.use(function (req, res, next) {
    console.log("2번 use 함수 호출");
    req.msg += "2번 use ";

    next();
});

//
app.use(function (req, res, next) {
    console.log("3번 use 함수 호출");
    req.msg += "3번 use ";

    next();
});


app.get('/', function (req, res) {
    res.writeHead(200, {"Content-Type":"text/html; charset=utf-8"});
    res.end(`<h1>${req.msg}</h1>`)
})


app.use(function (req, res, next) {
    res.writeHead(404, {"Content-Type":"text/html; charset=utf-8"});
    // 강제로 응답을 404로 보내준다.
    res.end("<h1>404 에러</h1>")
})


app.listen(3000, function () {
    console.log('서버 구동 중...');
})
