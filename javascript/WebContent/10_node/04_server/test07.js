/*
    http://localhost:10001/test.do?id=aaa&pass=1111
    실행시 사용자 브라우저 응답결과가 아래와 같이 출력되도록..

    요청 URI : /test.do
    파라미터
    id : aaa
    pass : 1111

*/

// console.log(urlObj.query);
// console.log(urlObj.query.id);



const http = require("http");
var url = require("url");
const port = 10001;

const server = http.createServer(function (req, res) {

    if(req.url=='/favicon.ico'){
        res.end();
        return;
    }

    var urlObj = url.parse(
        req.url, true
    );

    res.writeHead(200, {"Content-Type":"text/html; charset=utf-8"});
    var str = `<h1>요청 URI : ${urlObj.pathname}</h1>
               <h1>파라미터 리스트</h1>`;
    for (var key in urlObj.query) {
        str += `<h1>${key} : ${urlObj.query[key]}</h1>`;
    }
    res.write(str);
    res.end();
});


server.listen(port, function () {
    console.log("서버 구동 성공...");
})
