/*

request 모듈 : 외부모듈

외부모듈
npm i request

*/

var request = require("request");

request('http://www.dankookie.com', function (err, res, body) {
    if(err || res.statusCode != 200){
        console.log('err:', err)
        return;
    };
    console.log("읽어온 내용");
    console.log('body:', body);
});
