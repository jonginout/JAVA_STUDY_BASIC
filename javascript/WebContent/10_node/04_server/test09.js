// test08.js 의 내용을 변경한다.
// /html 으로 요청이 오면 data/test.html을 전송
// /image 으로 요청이 오면 data/test.jpg을 전송
// /audio 으로 요청이 오면 data/test.mp3을 전송

const http = require("http");
const url = require("url");
const fs = require("fs");


http.createServer(function (req, res) {

    if(req.url=='/favicon.ico'){
        res.end();
        return;
    }

    console.log(req.url);
    switch (req.url) {
        case '/html':
            fs.readFile("./data/test.html", function (err, data) {
                res.writeHead(200, {"Content-Type":"text/html; utf-8"});
                res.end(data);
            });
            break;
        case '/image':
            fs.readFile("./data/test.jpg", function (err, data) {
                res.writeHead(200, {"Content-Type":"image/jpeg"});
                res.end(data);
            });
            break;
        case '/audio':
            fs.readFile("./data/test.mp3", function (err, data) {
                res.writeHead(200, {"Content-Type":"audio/mp3"});
                res.end(data);
            });
            break;

    }

}).listen(10001, function () {
    console.log("서버 구동중~~~~~~");
})
