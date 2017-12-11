var express = require('express');
var app = express();
var fs = require("fs");
var multer = require("multer");
var upload = multer({ dest: 'uploads/' })

// 뷰 엔진 설정
app.set('view engine', 'pug');

app.listen(3000, function (err) {
	console.log("localhost:3000 번으로 서버 구동중");
});

app.get("/", (request, response) =>  {
    response.sendFile(__dirname + "/index.html");	
});

//  upload.array('attach', 2) : multiple 속성 처리, 최대 파일 개수.. 넘으면 에러발생
app.post(
	'/board/write.do', 
	upload.fields(
		[{ name: 'attach1', maxCount: 1 }, { name: 'attach2', maxCount: 2 }]), 
	(request, response) => {
		console.log(0);
		console.log(1, request.files);  // files 속성명으로 처리해야 함
		console.log(2, request.files['attach1'][0]);  // files 속성명으로 처리해야 함
		console.log(3, request.files['attach2']);  // files 속성명으로 처리해야 함
		console.log(4, request.body);
	}
);

app.get('/board/writeForm.do', (request, response) => {
	response.sendFile(__dirname + "/writeForm3.html");	
});