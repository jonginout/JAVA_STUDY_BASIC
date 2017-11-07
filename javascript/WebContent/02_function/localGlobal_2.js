/* 

    글로벌 변수와 로컬 변수 2
    - 글로벌 변수와 같은 이름의 로컬 변수를 선언해도 상관없다.

*/

"user strict"

var msg = "글로벌 변수";
console.log("msg", msg);

function func(){
    var msg = "로컬변수";
    console.log("msg", msg);
}
func();

// console.log("localMsg", localMsg);