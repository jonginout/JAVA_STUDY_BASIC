const http = require("http");
var fs = require("fs");

http.createServer(function (req, res) {
    // data/test.html 파일의 내용을
    // 사용자에게 출력
    fs.readFile("./data/test.html", "utf-8", function (err, data) {
        res.writeHead(200, {"Content-Type":"text/html; charset=utf-8"});
        res.end(data);
    });
}).listen(10001, function () {
    console.log("10001번 포트로 구동중....");
})

http.createServer(function (req, res) {
    fs.readFile("./data/test.jpg", function (err, data) {
        res.writeHead(200, {"Content-Type":"image/jpeg"});
        res.end(data);
    });
}).listen(10002, function () {
    console.log("10002번 포트로 구동중....");
})

http.createServer(function (req, res) {
    fs.readFile("./data/test.mp3", function (err, data) {
        res.writeHead(200, {"Content-Type":"audio/mp3"});
        res.end(data);
    });
}).listen(10003, function () {
    console.log("10003번 포트로 구동중....");
})
