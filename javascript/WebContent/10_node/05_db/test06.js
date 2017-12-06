const mysql = require('mysql');

var con = mysql.createConnection({
    user : "hanbit",
    password : "hanbit",
    database : "hanbit"
});

var sql = 'update tb_board set writer = ? where no = 8';

con.query(sql, ['천사'], function (err, result) {
    if(err){
        console.log(err);
        return;
    }
    console.log("==============결과출력=============");
    console.log(result);

    console.log(result.affectedRows); // 수정된 row 수

})
