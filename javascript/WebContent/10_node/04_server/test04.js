// 노드는 단일 쓰레드 + 이벤트 방식이다

/*

    single 쓰레드 방식으로 동작한다
    동시처리 방식이 아니다... (한순간에 한사용자의 요청만 처리한다..)
    특정 서비스를 순차적으로 빠르게 처리할 경우 사용


*/

const http = require("http");
const port = 10001;

var i = 0
const server = http.createServer(function (req, res) {
    console.log("사용자 요청 들어옴");

    setTimeout(function () {
        res.end(`<h1>end.....${i}</h1>`);
        i++;
    },800);
});


server.listen(port, function () {
    console.log("서버 구동 성공...");
})
