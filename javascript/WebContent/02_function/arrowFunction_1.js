/* 

    ECMA6에서 추가된 화살표 함수
    (arrow function)
    - 함수를 더욱더 심플하게 정의할 수 있도록 해준다

    (매개변수) => { 명령어; ....}

*/
"use strict"

var func1 = function () {
    console.log("매개 변수가 없는 경우의 익명 에로우 펑션!");
}

func1 = () => {
    console.log("매개 변수가 없는 경우의 익명 에로우 펑션!");
}

func1();

var func2 = function (num) {
    console.log(num);
}

func2 = (num) => {
    console.log(num);
}

func2(23123213);

var func3 = function (num1, num2) {
    console.log("익명함수 func3", num1, num2);
}
func3(1,23213);

func3 = (num1, num2) => {
    console.log("익명함수 func3", num1, num2);
}
func3(112312,212132);