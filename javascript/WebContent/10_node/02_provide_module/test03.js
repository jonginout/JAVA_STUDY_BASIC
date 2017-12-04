// url 모듈 : url 정보를 분석해서 처리

var url = require("url");

var urlObj = url.parse(
    "http://localhost:10001/list.do?id=aaa&pass=1234", true
    //true를 주면 query를 객체로 파싱해준다.
    //false거나 없는경우에는 문자열로 파싱해준다.

);

console.log(urlObj);


console.log(urlObj.query);
console.log(urlObj.query.id);
