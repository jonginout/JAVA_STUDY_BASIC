const http = require('http');
const fs = require('fs');
const qs = require("querystring");
const mysql = require('mysql');

const con = mysql.createConnection({
    user : "hanbit",
    password : "hanbit",
    database : "hanbit"
});

http.createServer(function (req, res) {
    res.writeHead(200, {"content-Type":"text/html; charset=utf-8"});

    if(req.url=='/favicon.ico'){
        res.end();
        return;
    }
    var fullUrl = req.url;
    var url = fullUrl;
    if(fullUrl.indexOf("/", 1) != -1){
        url = fullUrl.substring(0,fullUrl.indexOf("/", 1));
    }
    console.log(url);

    switch (url) {
        case '/list':
            var sql = "select * from tb_board order by no desc"
            con.query(sql, function (err, result) {
                if(err || result.length == 0){
                    console.log(err);
                    res.end("서버의 오류 혹은 존재하지 않는 데이터 입니다.");
                    return;
                }
                fs.readFile("./board/list.html", "utf-8", function (err, data) {


                    var listHtml = "";
                    result.forEach(row => {
                        listHtml += `
                            <tr>
                                <td>${row.no}</td>
                                <td><a href="/detail/${row.no}">${row.title}</a></td>
                                <td>${row.writer}</td>
                            </tr>
                        `;
                    })
                    data = data.replace(":list", listHtml);
                    res.end(data);
                });

            })
            break;
        case '/detail':

            var no = parseInt(fullUrl.substring(fullUrl.lastIndexOf("/")+1));
            var sql = "select * from tb_board where no = ?"
            con.query(sql, [no] , function (err, result) {
                if(err || result.length == 0){
                    console.log(err);
                    res.end("서버의 오류 혹은 존재하지 않는 데이터 입니다.");
                    return;
                }
                fs.readFile("./board/detail.html", "utf-8", function (err, data) {

                    console.log(result[0].title);
                    data = data.replace(":title", result[0].title)
                                .replace(":writer", result[0].writer)
                                .replace(":content", result[0].content)
                                .replace(":no", result[0].no);
                    res.end(data);
                });

            })
            break;
        case '/write':
            res.writeHead(200, {"Content-Type":"text/html; charset=utf-8"});
            var pData = "";
            req.on("data", function (data) {
                console.log("파라미터 읽는 중...");
                pData += data;
            });
            req.on("end", function () {
                var param = qs.parse(pData);
                console.log(param);


                var sql = `insert into tb_board (writer, title, content)
                            values (?, ? , ?)`
                con.query(
                    sql,
                    [param.writer, param.title, param.content] ,
                    function (err, result) {
                    if(err || result.length == 0){
                        console.log(err);
                        res.end("서버의 오류 혹은 존재하지 않는 데이터 입니다.");
                        return;
                    }
                    res.writeHead(302, { 'Location': '/list'});
                    res.end();

                })
            });
            break;
        case '/writeForm':
            fs.readFile("./board/writeForm.html", function (err, data) {

                res.end(data);
            });
            break;
        case '/updateForm':
            var no = parseInt(fullUrl.substring(fullUrl.lastIndexOf("/")+1));
            var sql = "select * from tb_board where no = ?"
            con.query(sql, [no] , function (err, result) {
                if(err || result.length == 0){
                    console.log(err);
                    res.end("서버의 오류 혹은 존재하지 않는 데이터 입니다.");
                    return;
                }
                fs.readFile("./board/updateForm.html", "utf-8", function (err, data) {


                    console.log(result[0].title);
                    data = data.replace(":title", result[0].title)
                                .replace(":writer", result[0].writer)
                                .replace(":content", result[0].content)
                                .replace(":no", result[0].no);
                    res.end(data);
                });

            })
            break;

        case '/update':
        var no = parseInt(fullUrl.substring(fullUrl.lastIndexOf("/")+1));

        res.writeHead(200, {"Content-Type":"text/html; charset=utf-8"});
        var pData = "";
        req.on("data", function (data) {
            console.log("파라미터 읽는 중...");
            pData += data;
        });

        req.on("end", function () {
            var param = qs.parse(pData);
            console.log(param);


            var sql = `update tb_board set writer = ?, title = ?, content = ? where no = ?`
            con.query(
                sql,
                [param.writer, param.title, param.content, no] ,
                function (err, result) {
                    if(err || result.length == 0){
                        console.log(err);
                        res.end("서버의 오류 혹은 존재하지 않는 데이터 입니다.");
                        return;
                    }
                    res.writeHead(302, {'Location': '/detail/'+no});
                    res.end();

                })
            });
            break;
        case '/index':
            fs.readFile("./index.html", function (err, data) {
                res.end(data);
            });
            break;
        default:
            fs.readFile("./error/error404.html", function (err, data) {
                res.end(data);
            });
            break;
    }



}).listen(10001, function () {
    console.log("서버 구동 중........");
})
