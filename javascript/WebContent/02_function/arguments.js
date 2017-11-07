/* 

    함수의 호이스팅과 오버로딩..

    - 함수의 매개변수에 따른 처리
    - arguments : 함수의 매개변수를 포함하고 있는 함수 내부의 프로퍼티

    유사배열 객체
    {"0":1, "1":2, "2":3 ....}

*/
"use strict"

function func() {
    console.log(arguments.length);
    console.log(arguments);

    for (var key in arguments) {
        console.log(key, arguments[key]);
    }
}


func();
func(1);
func(1,213,21,3,213,1,31);