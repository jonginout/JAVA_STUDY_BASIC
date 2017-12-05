/*

    https://pugjs.org/ 참조..

    띄어쓰기 : 
    탭으로 띄우던가 스페이스로 띄우던가 둘 중 하나로 통일해야 한다.

    작성규칙
    5.  속성을 여러개를 설정해야 한다면 ","로 구분한다.

    6.  아이디 또는 클래스 속성이 정의된 div 태그를
        생설 할 때는 div 태그는 생략

        id가 적용된 div 태그 : #contetnt
        class가 적용된 div 태그 : .title

    7.  스크립트 태그를 적용할 땐
        script(src="")

    8.  css 파일 적용
        style
        link(href="")
        
*/

const http = require("http");
const pug = require("pug");
const fs = require("fs");

http.createServer(function(req, res){
    fs.readFile("test02.pug", "utf-8", function(err, data){
        var html = pug.render(data);
        res.writeHead(200, {"Content-Type" : "text/html; charset=utf-8"});
        res.end(html);
    })
}).listen(10001, function(){
    console.log("서버 구동....")
})