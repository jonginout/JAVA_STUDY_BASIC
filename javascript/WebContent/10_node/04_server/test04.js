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
    
    if(req.url=='/favicon.ico'){
        res.end();
        return;
    }

    console.log("사용자 요청 들어옴");

    setTimeout(function () {
        res.end(`<h1>end.....${i}</h1>`);
        i++;
    },5000);
    // 즉 아무리 여러번 요청을 해도
    // 5초가 안지나면 뒤에 요청은 기다린다.
    
    // 즉 단일 쓰레드기 때문에 5초의 시간이 끝날떄
    // 까지 다른 요청은 다 기다린다.

});


server.listen(port, function () {
    console.log("서버 구동 성공...");
})
