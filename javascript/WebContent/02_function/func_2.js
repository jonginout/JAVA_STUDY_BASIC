/*

    익명 함수
    - 함수의 이름이 존재하지 않는다.
    - 이름이 없는데 어떻게 호출하지?? 변수에 함수를 담아서 호출한다.

*/
"use strict"

let func1 = function () {
    console.log("익명함수 입니당~");
}

func1();

// 일반함수도 변수에 담을 수 있다.
// 다만 test 라는 이름으로 함수 접근이 불가능하다.
let func2 = function test() {
    console.log("익명2")
    // test(); 재귀적으로는 호출이 가능하다.
}

func2(); // 익명2

// test(); 불가
