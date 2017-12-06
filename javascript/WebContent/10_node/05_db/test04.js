const mysql = require('mysql');

var con = mysql.createConnection({
    user : "hanbit",
    password : "hanbit",
    database : "hanbit"
});

// con.connect();
// con.query()가 없으면 자동으로 con.connect를 실행해줌

var sql = `insert into tb_board (writer, title, content)
            values (?, ? , ?)`

            // 이부분에 순서대로 인자
con.query(
    sql,
    ["븅씐", "나는 븅신이다", "나는 븅신이다 내용입니다."],
    function (err, result) {
    if(err){
        throw err;
    };
    console.log("==============결과출력=============");
    console.log(result);


    console.log(result.affectedRows); // 인서트된 row 수
    console.log(result.insertId); //이번에 인서트 된 no => 이걸 통해 파일 업로드


})

con.end();
