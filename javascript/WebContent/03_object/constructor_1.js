/* 

    객체 이해하기_6

    자바스크립트의 생성자를 이용하기

    자바스크립트의 생성자란?
    - 일반 함수와 동일한 형태지만
    - 관습적으로 첫글자를 대문자로 시작하게 한다.
    - 생성자의 역할을 하기 위해서 꼭 new 와 함께 사용해야 한다.
    - new와 함께 사용하지 않으면 this가 생서되지 않는다.

    - 생성자에서 this는 불르는 객체가 아닌 
    - 생성된 객체 자신.


*/
"use strict"

// 생성자 역할의 함수 정의
//       대문자
function Member() {
    console.log(this);
}

var m1 = Member();
var m2 = new Member(); 
// new 하면 빈객체가 생기고 그거에 이름은 m1 이다.
// new를 붙혀야지만 this(생성된 객체 자신)의 의미가 있다.


console.log(m1); // 함수의 리턴값이 없을땐 반환은 언디파인드
console.log(m2); // new 했을때 return 값이 따로 없으면 this 반환