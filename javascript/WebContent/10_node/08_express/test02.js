// 프레임 워크
const express = require('express');
var app = express();


app.get('/', function (req, res) {
    res.end(`<h1>익스프레스인건가???</h1>`)
})


app.listen(3000, function () {
    console.log('서버 구동 중...');
})
