/* 

    변수의 스코프 (범위..)
    -

*/
"use strict"

var scope = "전역";

var func = () => {
    console.log(scope); // 호이스팅 되서 언디파인디드 뜬다
    // let scope = "지역";
    var scope = "지역";
    var inner = () => {
        console.log(scope); // 내부함수는 외부함수의 변수에 접근이 가능하다.
    }
    inner();    // 내부함수는 외부 함수 속에서만 호출 가능
}

func();

/* 

    

*/