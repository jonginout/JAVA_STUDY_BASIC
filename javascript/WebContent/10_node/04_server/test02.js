const http = require("http");
const port = 10001;

// server.on("request", cb )
// createServer(cb)
// 똑같은 거다

const server = http.createServer(function () {
    console.log("사용자 요청이 들어옴");
});



server.listen(port);
console.log("http://localhost:10001 으로 구동중....");
