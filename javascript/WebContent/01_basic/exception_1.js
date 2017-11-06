/* 

    자바스크립트의 예외 처리
    try, catch

*/

function isMan(juminNo){
    console.log(juminNo.length);
    // 넘어온 juminNo가 13자리가 아니라면 예외를 발생시킨다
    if(juminNo.length != 13){
        throw new Error("주민번호는 13자리 입니다 세끼야");
    }
    return true;
}

try {
    var result = isMan("123456123213214567");
    console.log(result);
} catch (error) {
    console.log(error)
}   

console.log("나머지 코드가 정상적으로 수행된다.")