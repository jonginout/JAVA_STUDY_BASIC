/* 

    자바스크립트 형변환

*/

"use strict"


console.log(true, Number(true));
console.log(false, Number(false));

// 묵시적 형변환
console.log(true+1);
console.log(false+1);

// String 형을 number 형으로 명시적 변환
// 빈 문자열 또는 공백만 있는 문자열은 0이고
// 숫자가 아닌 문자열이 포함된 경우 NaN으로 변환된다.

console.log("", Number(""));
console.log("12", Number("12"));
console.log("120원", Number("120원"));
console.log("$120", Number("$120"));

// 숫자로 시작되는 부분은 모두 변경됨
console.log("120원", parseInt("120원"));
console.log("$120", parseInt("$120"));

console.log(true, String("12"));