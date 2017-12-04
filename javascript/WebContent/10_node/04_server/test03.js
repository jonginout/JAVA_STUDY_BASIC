const http = require("http");
const port = 10001;

const server = http.createServer(function (req, res) {
    console.log("사용자 접속");

    // 사용자쪽으로 응답하기
    res.end(`<h1>kim-jong-in is god of fifa online3,4</h1>`)
})

server.listen(port, function () { // 서버가 실행된 후 콜백
    console.log("서버 구동중...");
});
