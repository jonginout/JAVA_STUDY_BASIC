/*

    https://pugjs.org/ 참조..

    띄어쓰기 : 
    탭으로 띄우던가 스페이스로 띄우던가 둘 중 하나로 통일해야 한다.

    작성규칙
    1.  태그를 작성
        태그이름

        ex)
        h1

    2.  태그안에 내용을 입력하기
        태그 띄고 내용

        ex)
        h1 내용
    
        여려줄의 내용 입력하기
        태그.
            내용
            내용
        
        ex)
        p.
            내용
            내용21

    3.  태그에 속성 입력하기
        태그명 뒤에 괄호를 사용해서 입력
        태그(속성명="값")

        ex)
        a(href="http:...")

    4. 주석문
        // : html에 나오는 주석문
        //- : html에 나오지 않는 진짜 주석문

*/

const http = require("http");
const pug = require("pug");
const fs = require("fs");

http.createServer(function(req, res){
    fs.readFile("test01.pug", "utf-8", function(err, data){
        var html = pug.render(data);
        res.writeHead(200, {"Content-Type" : "text/html; charset=utf-8"});
        res.end(html);
    })
}).listen(10001, function(){
    console.log("서버 구동....")
})