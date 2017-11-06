/*

    자바스크립트 변수12

    let 변수는 중복선언이 불가능 하다.
    - 호이스팅이 안되기 떄문이다.

    ECMA6에서 상수 선언이 가능하다.
    const - 상수
    - let와 키워드와 변수의 값을 변경할 수 없다는 점만 제외하면
    동일한 기능 처리됨
    - 선언할때 값을 할당해야 한다.
    - {} 이 스코프(범위)로 처리된다.
    - 호이스팅에서 제외된다.

*/

"use strict"
var msg = 1;
var msg = 2;
console.log(msg);

let msg2 = 3;
// let msg2 = 4; //에러 중복 선언 안된다.
console.log(msg2);

const msg3 = 100;
// msg3 = 6;
console.log(msg3);