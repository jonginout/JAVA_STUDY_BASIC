const ejs = require('ejs');
const http = require('http');
const fs = require('fs');


http.createServer(function (req, res) {

    ejs.renderFile(
        "./test02.ejs",
        {
        title : "노드 게시판",
        data : [2,4,5,7]
        },
        function (err, data) {
            if(err){
                console.log(err);
                return
            }
            res.writeHead(200, {"Content-Type":"text/html; charset=utf-8"});
            res.end(data)
        }
    );

    /*
    fs.readFile("test02.ejs", "utf-8", function (err, data) {
        if(err){
            console.log(err);
            return
        }
        var result = ejs.render(
            data,
            {
            title : "노드 게시판",
            data : [2,4,5,7]
            }
        );
        res.writeHead(200, {"Content-Type":"text/html; charset=utf-8"});
        res.end(result)
    });
    */
}).listen(10001, function () {
    console.log("서버 구동 중....");
});
