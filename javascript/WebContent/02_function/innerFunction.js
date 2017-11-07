/* 

    내부 함수

    - 함수안에 선언된 함수
    - 내부 함수의 호출은 자신이 
    선언된 외부 함수내에서만 접근이 가능함

*/

"use strict"

// 외부함수
function outerFn(){
    console.log("outerFn");

    // 내부함수
    // 외부 함수 안에서만 호출가능
    function innerFn(params) {
        console.log("innerFn")
    }

    innerFn();

}

outerFn();