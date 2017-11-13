/*

    객체 이해하기_3

    - 객체를 변수에 저장하게 되면 변수에는
    객체의 주소가 저장된다.

*/
"use strict"

var member1 = {
    "id" : "hong",
    "email" : "jonginout@naver.com"
};

var member2 = member1;

member2.id = "kang";


console.log("member1 : ",member1);
// member1 :  { id: 'kang', email: 'jonginout@naver.com' }
console.log("member2 : ",member2);
// member2 :  { id: 'kang', email: 'jonginout@naver.com' }

// 객체의 내용을 확인한다. for - in
for (var m in member1) {
    console.log(m +":"+ member1[m]);
}
//id:kang
//email:jonginout@naver.com

// 객체는 배열과 마찬가지로 모든 타입의 값을 저장 할 수 있다.
var obj = {
    "str" : "문자열",
    "num" : 15632,
    "bool" : true,
    "arr" : [1231,2131],
    "obj" : {"dsa":123},
    "func" : function(){},
    "null" : null,
    "und" : undefined
};
