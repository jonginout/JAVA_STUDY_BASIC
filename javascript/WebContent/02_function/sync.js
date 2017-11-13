/*

    함수의 동기적 호출과 비동기적 호출

    동기적 호출
    - 호출한 함수의 실행이 종료될 때까지 block 상태에 빠진다
    (일반적인 형태)
    - 함수에서 시간이 오래 걸리면 다음 실행이 오랫동안 지연될 수 있다.
    - 네트워크 상황에 따라 사이트가 느려질 수 있다.


    비동기적 호출
    - 함수 실행을 시킨 후, 함수가 작업을 끝날 때까지 기다리지 않고
    즉시 다음 명령어를 실행시킨다.
    - 비동기적 대표적 케이스 : Ajax 통신

*/
"use strict"
var prompt1 = require('prompt-sync')(); //동기 모듈
var prompt2 = require("prompt");  // 비동기 모듈

var name = prompt1("당신의 이름은 뭐임?");
// 동기라서 입력값이 있을때 까지 대기
console.log("name : ", name);

prompt2.start();
// prompt2.get(['age'], function (err, result){}); 이게 실행이 끝나면
// function (err, result){} 자동으로 호출된다
// 콜백함수
prompt2.get(['age','addr'], function (err, result){
    console.log(`당신의 나이는 ${result.age}이고 주소는 ${result.addr}입니다.`);
});

console.log("누가 먼저 실행 될려나?? ");
//이게 prompt2.get()와 동시에 실행 된다.
