const http = require("http");
const fs = require("fs");

http.createServer(function (req, res) {
    if(req.method == "GET"){
        fs.readFile("./data/form.html", function (err, data) {
            res.writeHead(200, {"content-Type":"text/html; charset=utf-8"});
            res.end(data);
        });
    }else {
        console.log("POST 방식 요청");

        /*
            post 방식일 경우 파라미터 처리방법
            파라미터 읽는 동안 발생하는 이벤트 : data
            파라미터 내용을 다 읽은 다음 호출 이벤트 : end
            req.emit("data", "읽은 파라미터 내용");
            req.emit("data", "읽은 파라미터 내용");
            req.emit("data", "읽은 파라미터 내용");
            ...
            req.emit("end");
        */

        var pData = "";
        req.on("data", function (data) {
            console.log("파라미터 읽는 중...");
            pData += data;
        })
        req.on("end", function () {
            console.log("모든 바디의 파라미터 읽은 다음 호출...");
            console.log("파라미터 정보 : "+pData );
        })


    }
}).listen(10001, function () {
    console.log("서버 구동중....");
});
