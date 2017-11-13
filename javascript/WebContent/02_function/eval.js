/*

    eval("자바스크립트 코드 문자열")
    - 문자열로 되어있는 자바스크립트 코드를 실행시켜준다.
    - Ajax(비동기 통신)을 할때 많이 사용하던 것...
    현재는 JSON.parse();가 대체하고 있다.

*/

// "use strict" 하면 안된다.

var data = "var i = 100; var j = 200; console.log(i+j);";

// console.log(i) // 이건 에러

eval(data); //300
console.log(i) //100
// eval 뒤는 실행 된거라 ㄱㅊ
