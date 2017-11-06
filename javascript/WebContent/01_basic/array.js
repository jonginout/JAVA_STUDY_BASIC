/* 

    자바스크립트 배열 1
    - 한번에 여러개의 메모리를 준비하는 방법
    - 변수명과 인덱스 번호를 사용해서 메모리의 위치를 제어

*/
"use strict"

/* 
    배열의 생성 : [] 또는 Array() 활용
    배열의 인덱스는 0 부터...
*/

let arr1 = [];
let arr2 = new Array();

console.log(arr1.length);
console.log(arr1[0]);
console.log(arr2.length);

/* 
배열의 크기가 동적으로 변경된다.
크기가 지정되어 있지 않은 상태에서도 데이터의 입력이 가능
배열의 크기 : 마지막 인덱스 + 1
*/

arr1[0] = 100;
arr1[2] = 200;
console.log(arr1[0]);
console.log(arr1[1]);
console.log(arr1[2]);


console.log(arr1.length)


/* 
    여러가지 데이터 타입을 배열에 담을 수 있다.

*/

arr1[3] = function () {}; // 배열에 함수 가능
arr1[4] = {}; // 배열에 객체 가능
arr1[5] = [1,2,3]; // 배열에 배열;


console.log(arr1);
console.log(arr1[3]);
console.log(arr1[4]);
console.log(arr1[5]);