/*

    자바스크립트 변수10

    변수의 범위
    - var 선언된 변수는 글로벌 변수이다.
    - 선언된 블록에 제한 받지 않는다.

*/

"use strict"
var msg = 1;
console.log(msg); //1

if(msg==1){
    var msg = 2;
    console.log(msg); //2
}

console.log(msg) //2

{
    var msg2 = 3;
}
console.log(msg2) //3
