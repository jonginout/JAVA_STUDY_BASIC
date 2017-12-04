/*
    전역 객체와 console
*/


//현재 작업 디렉토리
console.log("__dirname", __dirname);

//현재 작업 파일
console.log("__filename", __filename);


var msg = "hello , node JS";
console.log("%s", msg); // %s 는 문자열 출력

var person = {
        name : "홍길동",
        age : 22,
        addr : "서울특별시"
};
console.log("%j", person); // %j 는 json 출력

console.log("나이 : %d", 24); // %d 는 숫자

/*
시간 측정
console.time(구분이름) -시작
console.timeEnd(구분이름) -종료

*/
console.time("루프");
var sum = 0;
for (var i = 0; i < 10000000; i++) {
    sum += i;
}
console.timeEnd("루프");
