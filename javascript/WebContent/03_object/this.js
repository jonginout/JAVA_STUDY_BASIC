/* 

    객체 이해하기_4

    - 함수내에서 this는 일반적으로 함수를 호출한 객체이다.

*/
// "use strict"


function msg () {
    console.log(this);
    console.log(
        this.name + "님이 입장함..."
    );
}


var m1 = {
    name : "헤이즈",
    msg : msg
};
var m2 = {
    name : "갓이즈",
    msg : msg
};


m1.msg();
//{ name: '헤이즈', msg: [Function: msg] }
//헤이즈님이 입장함...

m2.msg();
//{ name: '헤이즈', msg: [Function: msg] }
//헤이즈님이 입장함...