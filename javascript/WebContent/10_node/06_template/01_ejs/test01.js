const ejs = require('ejs');
const http = require('http');
const fs = require('fs');


http.createServer(function (req, res) {
    fs.readFile("test01.ejs", "utf-8", function (err, data) {
        if(err){
            console.log(err);
            return
        }
        ejs.render(data);
        console.log(data);
        res.writeHead(200, {"Content-Type":"text/html; charset=utf-8"});
        res.end(data)
    });
}).listen(10001, function () {
    console.log("서버 구동 중....");
});
