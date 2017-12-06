// 미니 Express 만들기 - GET, POST 요청을 다루는 함수를 관리하는 기능 추가
var http = require('http')
var url = require('url')
var path = require('path')
var fs = require('fs')

var middleware = [];
var getMap = [];
var postMap = [];

function error404(request, response) {
  response.writeHead(200, {
    'Content-Type' : 'text/html;charset=UTF-8'
  })
                // 경로 조합해주는거 앞에 슬러쉬가 있든 없든
  fs.readFile(path.join(__dirname, './html/error404.html'), function(err, data) {
    if (err) throw err
    response.end(data)
  })
}

// http://localhost:3000/writeForm
var server = http.createServer(function(request, response) {

  // use 형태는 무조건 실행함
  for (let i = 0; i < middleware.length; i++) {
    middleware[i](request, response);
  }
  // 미들웨어에는 use 함수가 담겨있다.
  // 하나씩 꺼내서 실행

  var urlInfo = url.parse(request.url, true); // true는 json으로 뽑기
  console.log(urlInfo);
  // url 모듈로 /writeForm?no=1 파싱

  var cb = null;
  if (request.method == 'GET') {
    cb = getMap[urlInfo.pathname]
  } else if (request.method == 'POST') {
    cb = postMap[urlInfo.pathname]
  }

  if (cb) {
    // cb에 무언가가 들어갔으면 그 안에 들어간 함수 동작
    cb(request, response)
  } else {
    error404(request, response)
  }
})

module.exports = function() {
  return {
    use(fn) {
      middleware.push(fn); // 배열에 쌓아 준다.
    },
    // 주어진 url로 GET 요청이 들어왔을 때 호출될 함수를 등록한다.
    get(url, cb) {
      getMap[url] = cb
    },
    // getMap['/dssdas.do'] = function () {
    //
    // }


    // 주어진 url로 POST 요청이 들어왔을 때 호출될 함수를 등록한다.
    post(url, cb) {
      postMap[url] = cb
    },

    // 웹서버를 시작시킨다.
    listen(port, cb) {
      server.listen(port, cb);
    }
  }
};
