/*

  자바스크립트 문자열 3
  - 여러줄의 입력 처리방식
  - ECAM6 부터는 템플릿 리터럴 이라는 새로운 유형이 도입

*/
"use strict"
var msg = "저의 "+
"이름은 "+
"홓꼬도 입니다";

console.log(msg);


msg = "저의 \
이름은 \
홍길똥 입니다."
console.log(msg);
/* \뒤에 공백도 있으면 안된다. */


msg = `
저의
이름은
홍길동
입니다.
`;
console.log(msg);


let name = "홈길돔";
let age = 33;
console.log("나의 이름은 "+name+" 이고 나이는 "+age+"세 입니다.");

console.log(
  `나의 이름은 ${name} 이고 나이는 ${age}세 입니다.`
)