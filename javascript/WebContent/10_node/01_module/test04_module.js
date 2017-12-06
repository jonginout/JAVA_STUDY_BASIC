
/*

var 로 선언된 변수는 모듈 변수이디.
선언된 파일에서만 사용이 가능함
global. 변수명으로 선언시 외부 참조 가능

*/

var val1 = 200;
console.log("module val1", val1);

global.val2 = 300;
console.log("module val2", val2);
