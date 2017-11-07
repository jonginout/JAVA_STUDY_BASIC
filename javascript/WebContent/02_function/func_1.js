/* 

    선언적 함수
    - 함수의 이름이 존재하는 것
    - 컴파일 시점에 함수 정보를 등록 (함수의 호이스팅)
    - 함수를 선언하기 이전에 사용이 가능
    - 함수가 별도의 return을 설정하지 않으면 기본적으로 undefined가 반환된다.

    익명 함수

*/
"use strict"

// 선언형 함수
function func1(num1, num2) {
    console.log(num1, num2);
    return num1 + num2;
}


var result = func1(1,4);
console.log("func1 :" , result);

// 선언전 호출가능
result = func2();
console.log("func2 :" , result);


function func2() {
    return "선언전에 호출 가능??"
}

//반환이 없으면 기본 반환 -> undefined
function func3() {}
console.log(func3())