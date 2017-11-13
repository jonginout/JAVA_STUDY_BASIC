/* 

    콜백함수 이해하기 3
    
    - 함수의 결과로 함수 리턴하기 

*/
"use strict"

function func() {
    return function(){ console.log("리턴된 함수 실행"); }
}

var myCb = func();
console.log(typeof (myCb)) // function

myCb(); // 리턴된 함수 실행
/* 
    var myCb = function(){ console.log("리턴된 함수 실행"); }
*/

func(); // 아무것도 실행 안됨
/* 
    function(){ console.log("리턴된 함수 실행"); }
*/

func()(); // 리턴된 함수 실행
/* 
    function(){ console.log("리턴된 함수 실행"); }
    을 바로 호출
*/


