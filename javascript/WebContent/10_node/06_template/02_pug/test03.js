/*

    https://pugjs.org/ 참조..

    띄어쓰기 : 
    탭으로 띄우던가 스페이스로 띄우던가 둘 중 하나로 통일해야 한다.

    작성규칙
    9.  pug 태그 속에 js 순수 태그 작성
        - for(~~~)
        
    10. pug에서 동적인 값을 사용하기
        #{이름}
*/

const http = require("http");
const pug = require("pug");
const fs = require("fs");

http.createServer(function(req, res){
    fs.readFile("test03.pug", "utf-8", function(err, data){
        var html = pug.render(
            data,
            {
                title : "pug 사용하기",
                arr : [2,31,24,25,45],
                url : "https://pugjs.org"
            }            
        );
        res.writeHead(200, {"Content-Type" : "text/html; charset=utf-8"});
        res.end(html);
    })
}).listen(10001, function(){
    console.log("서버 구동....")
})