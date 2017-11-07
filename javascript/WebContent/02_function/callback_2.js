/* 

    콜백함수 이해하기 2

    - 매개변수로 함수 넘기기

*/
"use strict"

function func(callback) {
    console.log(typeof (callback));
    callback();
}

/* 1 */
func(()=>{console.log("익명함수 호출됨....")})

/* 2 */
// func(function(){console.log("익명함수 호출됨....")})


/* 3 */
// function myCall() {
//     console.log("myCall 호출됨");
// }
// func(myCall);
    

// 1,2,3 다 똑같음
