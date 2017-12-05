/*

    자바스크립트를 통해서 DB (mysql)을 연결

    mysql 모듈을 다운로드 npm i mysql

*/

const mysql = require('mysql');


var con = mysql.createConnection({
    // host : "localhost",
    // port : 3306,
    // localhost, 3306 일때는 생략가능
    user : "hanbit",
    password : "hanbit",
})

// 연결시도
con.connect();

// SQL 실행하기 - 비동기적 실행처리
// 그렇기에 콜백함수 필수
con.query("create database hanbit", function (err, result) {
    if(err){
        console.log("데이터 베이스 생성 오류");
        console.log("err");
        return;
    }
    console.log("데이터 베이스 생성 완료",result);
});

// 연결 종료
// 없어도 DB 작업이 끝나면 자동 호출된다.
con.end();
