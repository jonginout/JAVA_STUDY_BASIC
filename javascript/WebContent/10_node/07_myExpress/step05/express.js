// 파라미터 추가
var http = require('http')
var url = require('url')
var path = require('path')
var fs = require('fs')
var qs = require('querystring')

var middleware = [];
var getMap = [];
var postMap = [];

function error404(request, response) {
  response.writeHead(200, {
    'Content-Type' : 'text/html;charset=UTF-8'
  });

  fs.readFile(path.join(__dirname, './html/error404.html'), function(err, data) {
    if (err) throw err
    response.end(data)
  });
}

//여기서 겟인지 포스튼지 구분 후 함수리턴
function getCb(method, reqPath) {
  if (method == 'GET') {
    return getMap[reqPath]
  } else if (method == 'POST') {
    return postMap[reqPath]
  }
  return null
}

var server = http.createServer(function(request, response) {
  // use 형태는 무조건 실행함
  for (let i = 0; i < middleware.length; i++) {
    middleware[i](request, response);
  }

  var urlInfo = url.parse(request.url, true);


  var cb = getCb(request.method, urlInfo.pathname);
  //리턴된 함수

  if (cb) {
    if (request.method == 'GET') {
      request.query = urlInfo.query
      cb(request, response)

    } else if (request.method == 'POST') {

      var queryString = ''
      request.on('data', function(data) {
        queryString += data
      })
      request.on('end', function() {
        request.body = qs.parse(queryString)
        cb(request, response)
      })

    }
  } else {
    error404(request, response)
  }

  
})

module.exports = function() {
  return {
    use(fn) {
      middleware.push(fn);
    },
    // 주어진 url로 GET 요청이 들어왔을 때 호출될 함수를 등록한다.
    get(url, cb) {
      getMap[url] = cb
    },

    // 주어진 url로 POST 요청이 들어왔을 때 호출될 함수를 등록한다.
    post(url, cb) {
      postMap[url] = cb
    },

    // 웹서버를 시작시킨다.
    listen(port, cb) {
      server.listen(port, cb)
    }
  }
}
