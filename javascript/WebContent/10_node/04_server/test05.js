// 노드는 단일 쓰레드 + 이벤트 방식이다

/*

    single 쓰레드 방식으로 동작한다
    동시처리 방식이 아니다... (한순간에 한사용자의 요청만 처리한다..)
    특정 서비스를 순차적으로 빠르게 처리할 경우 사용


*/

const http = require("http");
const port = 10001;

const server = http.createServer(function (req, res) {
    res.writeHead(200, {"Content-Type":"text/html; charset=utf-8"});
    res.write(`<h1>화면에 데이터 보내기</h1>`);
    res.write(`<h1>화면에 데이터 보내기</h1>`);
    res.end();
});


server.listen(port, function () {
    console.log("서버 구동 성공...");
})
