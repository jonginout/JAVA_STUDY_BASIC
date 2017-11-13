/*

    ECMA6에서 추가된 화살표 함수 2
    (arrow function)
    - 실행문장 중심으로 살펴보기

*/
"use strict"

/*

var func1 = (num) => {
    return num * num;
}

!!!한문장!!!!
한문장인 경우 꼭 블럭으로 감싸고 있을 이유가 없다.
return 된다.
*/

var func1 = (num) => num * num;

console.log(func1(2)); //4

func1 = (num) => console.log("테스트");
console.log(func1(2)); //테스트 undefined

/*
 {} 없이 사용하는 경우는 무조건 return 된다.
*/
