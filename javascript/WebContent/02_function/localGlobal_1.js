/* 

    글로벌 변수와 로컬 변수 1
    - 글로벌 변수 : 함수 밖에 선언, 전역에서 사용이 가능
    - 로컬 변수 : 함수 내에서만 사용 가능, 함수 호출이 종료되면 제거된다.

*/

"user strict"

var msg = "글로벌 변수";
console.log("msg", msg);

function func(){
    var localMsg = "로컬변수";
    console.log("localMsg", localMsg);
}
func;

// console.log("localMsg", localMsg);