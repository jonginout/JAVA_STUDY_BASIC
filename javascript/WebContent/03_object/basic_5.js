/* 

    객체 이해하기_5

    객체를 만드는 함수!

*/
"use strict"

function makeMember(name, age, gender, addr) {
    return {
        name : name,
        age : age,
        gender : gender,
        addr : addr
    };
}
var m1 = makeMember("조조", 32, "남자", "위례");
var m2 = makeMember("유비", 41, "남자", "전주");
console.log(m1);
console.log(m2);

// var m1 = {
//     name : "조조",
//     age : 32,
//     gender : "남자",
//     addr : "성남"
// };