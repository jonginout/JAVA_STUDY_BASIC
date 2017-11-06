/* 

    자바스크립트의 예외 처리
    try, catch, finally

*/

function isMan(juminNo){
    console.log(juminNo.length);
    // 넘어온 juminNo가 13자리가 아니라면 예외를 발생시킨다
    if(juminNo.length != 13){
        throw {result : -1 , msg : "주민번호는 13자리야 ㅄ아"};
    }
    return true;
}

try {
    var result = isMan("123456123213214567");
    console.log(result);
} catch (error) {
    console.log(error.result)
    console.log(error.msg)
}finally{
    console.log("항상 수행~~")
}

console.log("나머지 코드가 정상적으로 수행된다.")