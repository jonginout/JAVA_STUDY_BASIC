/*

    객체 이해하기_2

    - 객체 생성시 값의 초기화 및 프로퍼티 삭제

*/
"use strict"

// 객체 생성 및 값의 초기화

var member = {
    "id" : "hong",
    "email" : "jonginout@naver.com"
};

member.name = "김종인";

console.log(member);

// 프로퍼티 삭제 : delete 객체.프로퍼티명

delete member.email;
console.log(member);

// 객체 자체를 삭제할 수 없다.
// delete member;


// 객체의 프로퍼티 이름은 ""을 생략해도 처리가 된다..
// 단 JSON 객체는 생략 불가..
var member2 = {
    "id" : "kim",
    email : "jasod@asnd.com"
};

console.log(member2);
