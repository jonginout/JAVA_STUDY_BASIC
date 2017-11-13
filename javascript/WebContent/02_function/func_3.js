/* 

    익명 함수
    - 함수의 호이스팅의 대상에서 제외된다.
    - 그래서 코드 위로 끌어올리기 불가
    - 컴파일시 함수 정보가 등록되지 않는다.
    - 결론적으로 선언 이후에만 호출이 가능하다.

*/
"use strict"

// func1(); 에러

let func1 = function () {
    console.log("익명함수 입니당~");
}

func1();


/*

    컴파일시
    var func;

    실행시
    func1 = function () {
        console.log("익명함수 입니당~");
    }

*/
