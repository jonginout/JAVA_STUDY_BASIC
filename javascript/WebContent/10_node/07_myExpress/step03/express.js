var http = require("http");

var server = http.createServer(function(req, res){
    console.log("사용자 요청 들어옴");
    res.end();
});

module.exports = function () {
    return {

        listen (port, cb) {
            server.listen(port,cb());
        }

    };
    // 객체를 리턴
};


