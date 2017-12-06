/* 
	파리미터 처리하기..
*/
var express = require('./express');
var fs = require("fs");

var app = express()

app.get("/", function (request, response) {
  fs.readFile("test.html", function (err, data) {
    response.writeHead(200, {'Content-Type': 'text/html;charset=UTF-8'})
    response.end(data);
  });
});

app.get('/test1.do', function(request, response) {
  response.writeHead(200, {'Content-Type': 'text/html;charset=UTF-8'})
  response.write('<h1>GET 요청 처리!</h1>')
  response.write('<h1>name=' + request.query.name + "</h1>")
  response.write('<h1>age=' + request.query.age + "</h1>")
  response.write('<h1>tel=' + request.query.tel + "</h1>")
  response.end()
})

app.post('/test2.do', function(request, response) {
  response.writeHead(200, {'Content-Type': 'text/html;charset=UTF-8'})
  response.write('<h1>POST 요청 처리!')
  response.write('<h1>name=' + request.body.name + "</h1>")
  response.write('<h1>age=' + request.body.age + "</h1>")
  response.write('<h1>tel=' + request.body.tel + "</h1>")
  response.end()
})

app.listen(3000, function() {
  console.log('서버가 시작되었습니다.')
})
