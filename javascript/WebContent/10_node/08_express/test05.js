const express = require('express');
const fs = require("fs");

const bodyParser = require('body-parser');

const app = express();

// app.use는 실제로 구동하기 전에
// 뭔가 모듈들을 선작업 할 일이 있을 때 보통 사용!!

app.use(bodyParser.json()); // application/json
//  "{asd:sda}" 그럴일이 많이 없겠지만
// input 이나 그런곳에 직접 텍스트로 "{asd:sda}" 이런식으로 json 스트링을
// 직접 넘기는경우 얘가 json으로 진짜 바꿔준다.

app.use(bodyParser.urlencoded({extended:true})); // json 안에 json 까지 파싱해준다.
// application/x-www-form-urlencoded
// request.body 가 생성 되는것..

app.get("/", function (req, res) {

    res.sendFile(__dirname + "/views/form.html"); // 밑에랑 똑같다.

    // fs.readFile("views/form.html", function (err, data) {
    //     res.writeHead(200, {"Content-Type" : "text/html; charset=utf-8"});
    //     res.end(data);
    // });
});

app.post("/form.do", function (req, res) {
    res.writeHead(200, {"Content-Type" : "text/html; charset=utf-8"});
    console.log(req.body);
    res.write(`<h1>${req.body}</h1>`)
    res.end();
})

app.listen(3000, function () {
    console.log("서버 구동 쭝!");
})
