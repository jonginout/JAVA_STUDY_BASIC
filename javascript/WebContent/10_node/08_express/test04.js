const express = require('express');
var app = express();

// 웹서버에서 사용할 정적 파일들의 루트 디렉토리 위치

app.use(express.static("public")); // 퍼블릭 폴더 경로
// use에 있는 static 함수 호출


app.get("/", function (req, res) {
    res.writeHead(200, {"Content-Type" : "text/html; charset=utf-8"});
    res.write("<h1>정적 파일 테스트</h1>")
    res.write("<img src='/images/pic1.jpg'/>")

    res.end()
})

app.listen(3000)
