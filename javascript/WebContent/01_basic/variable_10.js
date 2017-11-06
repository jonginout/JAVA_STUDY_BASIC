/*

    자바스크립트 변수11

    let 변수
    - ECMA6(ES6) 부터 제공되는 변수
    - 블럭에서만 사용할 수 있다.
    - 호이스팅 대상에서 제외


*/

"use strict"
var msg = 1;
if (msg == 1) {
    let msg = 2;
}
console.log(msg);

console.log(msg);
{
    let msg2 = 3;
}
// console.log(msg2); // 에러 발생 : let는 선언된 블럭에서만 접근한다.