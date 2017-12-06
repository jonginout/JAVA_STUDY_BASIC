/*

서버 자동 재시작...
: supervisor , nodemon , forever

npm i supervisor -g
// -g : global 다른 프로젝트에서 사용 가능

*/
const http = require("http");

http.createServer(function (req, res) {
    console.log("사용자 요청 들어옴");
    res.writeHead(200, {"Content-Type":"text/html; charset=utf-8"})
    res.end(`<h1>성공이당</h1>`)
}).listen(10001, function () {
    console.log("서버 구동 중....");
})
