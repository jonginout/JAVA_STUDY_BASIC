/*

require에 찾는 파일의  확장자가 없는 경우 기본적으로 .js 파일을 찾는다.

만약, .js 파일이 없는 경우 이름에 해당하는 디렉토리를 찾고
디렉토리 하위의 index.js 파일을 찾는다.

*/

var cal = require("./test05_module");

console.log(cal.add(1,2));
console.log(cal.sub(1,2));
