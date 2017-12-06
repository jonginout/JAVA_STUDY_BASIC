var express = require('./express');

var app = express();

app.use(function (req, res) {
  console.log("1");
  req.msg = "a";
});

app.use(function (req, res) {
  console.log("2");
  req.msg += "b";
});

app.use(function (req, res) {
  console.log("3");
  req.msg += "c";
});

app.get('/', function(request, response) {
  console.log("msg", request.msg);
  response.writeHead(200, {'Content-Type': 'text/html;charset=utf-8'})
  response.end('<html> \
  <body> \
  <h1>안녕하세요!</h1> \
  </body> \
  </html>')
});

app.post('/writeForm', function(request, response) {
  response.writeHead(200, {'Content-Type': 'text/html;charset=utf-8'})
  response.end('<html> \
  <body> \
  <h1>등록폼 입니다.</h1> \
  </body> \
  </html>')
});

app.listen(3000, function() {
  console.log('서버가 시작되었습니다.')
});
