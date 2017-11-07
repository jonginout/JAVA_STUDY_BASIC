/* 

    자바스크립트 배열 3
    - 배열의 요소를 마지막에 추가하는 것

*/

"use strict"

let arr = [1,2,3];
arr[arr.length] = 40; // 마지막에 추가
arr.push(50);

console.log(arr);

var arr2 = arr;

arr[1] = 2000;

console.log("arr" , arr);
console.log("arr2" , arr2); // 같은 공간을 보고있다.


