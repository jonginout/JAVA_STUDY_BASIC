/*

모듈을 가져오기 위해서 require을 사용

require 함수 호출시 해당 모듈 파일의 exports객체를 반환받는다.

*/
var cal = require("./test01_module.js"); // .js 생략 가능
// cal 은 test01_module의 exports다


console.log(cal.add(100,200));

console.log(cal.sub(100,200));
