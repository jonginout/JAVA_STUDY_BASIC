/* 

    논리 연산자 활용
    - 조건문을 대신해서 처리
    (간단한 명령일 경우)

*/
"use strict"

var num = 10;
if((num % 2 ) == 0){
    console.log("num은 짝수 입니다.")
}
((num % 2 ) == 0) && console.log("num은 짝수 입니다.");
