/*

    자바스크립트 변수8

    변수의 자료형을 검사하기
    - typeof (데이터) : 데이터 타입을 문자열로 반환해준다.
    - null 데이터 타입은 object로 표시됨 : 오래된 버그...
    - function 은 일급객체(기능을 가진 객체)라고 한다. 데이터 타입은 function으로 출력된다.
*/

"use strict"
var num1 = 10;
console.log(typeof num1);

var num2 = 10.3;
console.log(typeof num2);

var msg = "HI";
console.log(typeof msg);

var bool = true;
console.log(typeof bool)

var nullVal = null;
console.log(typeof nullVal)

var undefinedVal = undefined;
console.log(typeof undefinedVal)

var obj = {};
console.log(typeof obj)

function aaa(){}
console.log(aaa)