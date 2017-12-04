const http = require('http');
const fs = require('fs');
var qs = require("querystring");


http.createServer(function (req, res) {

    if(req.url=='/favicon.ico'){
        res.end();
        return;
    }
    console.log(req.url);

    switch (req.url) {
        case '/list.do':
            fs.readFile("./board/list.html", function (err, data) {
                res.writeHead(200, {"content-Type":"text/html; charset=utf-8"});
                res.end(data);
            });
            break;
        case '/write.do':
            res.writeHead(200, {"Content-Type":"text/html; charset=utf-8"});
            var pData = "";
            req.on("data", function (data) {
                console.log("파라미터 읽는 중...");
                pData += data;
            });
            req.on("end", function () {
                var param = qs.parse(pData);
                console.log(param);
                var str = `<h1>등록된 내용</h1>`;
                for (var key in param) {
                    str += `<h3>${key} : ${param[key]}</h3>`
                }
                str += `<a href="/list.do">뒤로가기</a>`
                res.end(str)
            });
            break;
        case '/writeForm.do':
            fs.readFile("./board/writeForm.html", function (err, data) {
                res.writeHead(200, {"content-Type":"text/html; charset=utf-8"});
                res.end(data);
            });
            break;
        default:
            fs.readFile("./board/index.html", function (err, data) {
                res.writeHead(200, {"content-Type":"text/html; charset=utf-8"});
                res.end(data);
            });
            break;
    }



}).listen(10001, function () {
    console.log("서버 구동 중........");
})
