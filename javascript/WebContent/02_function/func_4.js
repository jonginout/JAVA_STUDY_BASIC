/* 



*/
"use strict"

function foo() {
    console.log(1);
}

foo();

var foo = function(){
    console.log(2);
}

function foo(){
    console.log(3);
}

/* 

    컴파일시
    function foo() {
    console.log(1);
    }
    var foo;
    function foo(){
        console.log(3);
    } //덮어써지고
    실행시
    foo();
    foo = function(){
        console.log(2);
    }

*/