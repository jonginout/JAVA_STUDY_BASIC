/*

    자바스크립트 변수7

    변수와 데이터 타입

    정적 타입(static type) = 엄격 타입(strong type)
    - 변수의 선언시 용도를 고정하는 방식
    - 변수를 만들때 선언한 용도 대로만 값을 넣어야 한다.
    ex) c, c++, c#, java 정적타입의 언어들

    동적타입(dynamic type)
    - 대입되는 값에 따라서 용도가 변경되는 방식
    ex) Javascript

    자바스크립트는
    6개의 데이터 타입을 가지고 있다

    ------------------------------
    기본 데이터 타입
    - string
    - number
    - boolean
    - null
    - undefined
    ------------------------------
    객체 데이터 타입
    - object
    ------------------------------
    
*/

"use strict"
var num1 = 10;
console.log(num1);

var num2 = 10.3;
console.log(num2);

var msg = "HI";
console.log(msg);

var bool = true;
console.log(bool)

var nullVal = null;
console.log(nullVal)

var undefinedVal = undefined;
console.log(undefinedVal)

var obj = {};
console.log(obj)