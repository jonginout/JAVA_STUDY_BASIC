var express = require('express');
var app = express();
var bodyParser = require("body-parser");
var fs = require("fs");
var multer = require("multer");
var upload = multer({ dest: 'uploads/' })

app.use(bodyParser.json());  // json 데이터 처리
app.use(bodyParser.urlencoded({extended:true}));

// 뷰 엔진 설정
app.set('view engine', 'pug');

app.listen(3000, function (err) {
	console.log("localhost:3000 번으로 서버 구동중");
});

app.get("/", (request, response) =>  {
    response.sendFile(__dirname + "/index.html");	
});

// upload.single('attach') 부분 없으면 body 사용이 불가능
// multiple 속성 추가시 처리 안됨 : 에러발생
app.post('/board/write.do', upload.single('attach'), (request, response) => {
	console.log(0);
	console.log(1, request.file);
    console.log(2, request.body);
});

app.get('/board/writeForm.do', (request, response) => {
	response.sendFile(__dirname + "/writeForm1.html");	
});