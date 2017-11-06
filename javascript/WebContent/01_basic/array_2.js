/* 

    자바스크립트 배열 2

*/
"use strict"


/* 
    초기값을 가지는 배열 생성
*/

let arr = [10,20,30,40,50,60,70];

/* 
    배열은 객체이기 때문에 아래와 같은 특성이 발생한다.
    - 배열의 인덱스에 문자열을 넣을 수 있다.
*/

arr["id"] = "hong";
console.log(arr.length);

console.log(arr);


for (let key in arr) {
    console.log(key, arr[key]);
}


arr["9"] = "10번째";
console.log(arr.length);