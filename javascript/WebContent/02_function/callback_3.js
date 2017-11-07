/* 

    콜백함수 이해하기 3
    
    - 함수의 결과로 함수 리턴하기 

*/
"use strict"

var func = () => {
    return () => { console.log("리턴된 함수 실행"); }
}

var myCb = func();
console.log(typeof (myCb))

myCb();


func()();


