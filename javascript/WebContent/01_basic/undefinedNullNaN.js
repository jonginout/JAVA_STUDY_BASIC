/* 

    undefined
    - 변수는 존재하지만 값이 저장되지 않아 타입을 모를경우

    null
    - null은 특정객체를 가리키지 않음을 지정할때 null 사용

    NaN
    - 숫자가 아닌 상대 Not a Number

*/

// undefined 값 비교 가능
var msg1;
if(msg1 == undefined){
    console.log("변수는 존재하지만 값이 저장되지 않아 타입을 모를경우");
} 

// undefined 직접 대입 가능
var msg2 = 20;
msg2 = undefined;


// null 값 비교 가능
var mag3 = null;
if(msg3 == null){
    console.log("null은 특정객체를 가리키지 않음을 지정할때 null 사용");
} 


var mag4 = NaN;
console.log(typeof msg4)

// 값의 직접 비교가 불가능
if(msg4 == NaN) console.log("msg4 == NaN");

// 값의 비교를 위해서 isNaN 이라는 함수를 활용
if(isNaN(msg4)) console.log("isNaN(msg4)");