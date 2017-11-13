/*

    객체 이해하기_4

    객체는 연관성 있는 데이터를 묶어서 관리하고 싶을때
    각각의 값을 코드에서 쉽게 구분하고 싶을때

    - 데이터와 기능을 묶어서 관리 가능

*/
"use strict"

var member = {
    id : "hong",
    name : "홍길동",
    setId : function (id) {this.id = id; },
    getId : function () { return this.id }
};

member.setId("kim");
console.log(member.getId());
