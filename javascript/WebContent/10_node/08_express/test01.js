// 프레임 워크
const express = require('express');
var app = express();

    //verb => 메소드 방식
app.get('/', function (req, res) {
    res.end(`<h1>홈</h1>`)
})

app.get('/board/list.do', function (req, res) {
    res.end(`<h1>리스트</h1>`)
})

app.get('/board/write.do', function (req, res) {
    res.end(`<h1>쓰기 페이지</h1>`)
})


app.listen(3000, function () {
    console.log('서버 구동 중...');
})
