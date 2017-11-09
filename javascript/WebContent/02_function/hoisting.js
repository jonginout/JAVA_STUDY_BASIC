
function func(){
    //....
}

var func = function (){
    //...
}

//선언적 함수는 함수 주소의 값이 할당된 상태로 컴파일 된다.

function aaa(){
    console.log("aaa함수()-2");
}

var aaa = function (){ // 이 함수 주소값을 aaafunc 라고 하겠다. (가정)
    console.log("aaa함수()-1")
}

aaa(); // aaa함수()-1

// var aaa = (함수주소값"aaafunc");

// var aaa;

// aaa = function (){
//     console.log("aaa함수()-1")
// }


// aaa(); //aaa함수()-1

/* ----------------------------------------------------------- */
var ccc = 1;
var ccc = 2;
console.log(ccc); // 2


// var ccc;
// var ccc;
// ccc= 1;
// ccc= 2;
// console.log(ccc); // 2

/* ----------------------------------------------------------- */

var bbb = function (){
    console.log("bbb함수()-1")
}

function bbb(){ 
    console.log("bbb함수()-2"); 
}

bbb(); //bbb함수()-1


// var bbb;
// var bbb = (함수주소값"bbbfunc");

// bbb = function (){  // bbb 변수에 값이 덮어써진다.
//     console.log("bbb함수()-1")
// }


// bbb(); //bbb함수()-1