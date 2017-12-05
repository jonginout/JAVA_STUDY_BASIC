const mysql = require('mysql');

var con = mysql.createConnection({
    user : "hanbit",
    password : "hanbit",
    database : "hanbit"
});

// con.connect();
// con.query()가 없으면 자동으로 con.connect를 실행해줌

var sql = `delete from tb_board where no = ?`

            // 이부분에 순서대로 인자
con.query( sql, [7], function (err, result) {
    if(err){
        throw err;
    };
    console.log("==============결과출력=============");
    console.log(result);

    console.log(result.affectedRows); // 삭제된 row 수

})

con.end();
