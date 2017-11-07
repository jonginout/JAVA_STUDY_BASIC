/* 

    함수 : 명령어들을 묶어 놓은 것

    [] - > 생략가능

    function 함수명([매개변수]){
        ...

        [return 반환값;]
    }
    
*/
"use strict"

function func1() {
    console.log("1번");
}

func1();


function func2() {
    console.log("2번");
    return "반환값 -2";
}

console.log(func2());
func2();


function func3(val2) {
    console.log(val2);
}
var val2 = "3번";
func3(val2);

console.log("========================");

function func4(val2) {
    return val2;
}
val2 = "4번";
console.log(func4(val2));