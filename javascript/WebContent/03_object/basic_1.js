/* 

    객체 이해하기_1
    
    - 객체는 문자열로 이름을 붙인 값들의 집합체...
    

*/
"use strict"

// 빈 객체 생성
var member = {};

// 객체에 저장하는 값을 프로퍼티(property)라고 한다.
// 문자열을 사용해서 값을 저장한다.

// 객체.프로퍼티명 = 값
// 객체["프로퍼티명"] = 값(프로퍼티)

member.id = 'admin';
member["email"] = "jonginout@naver.com";

console.log(member.id);
console.log(member["email"]);
console.log(member);


// 객체의 프로퍼티명에 특수문자 또는 공백이 있는 경우
// " . " 도트연산자를 사용 불가능

// member.home-page = "종인 홈페이지";
member["home-page"] = 'www.naver.com';
console.log(member["home-page"]);
