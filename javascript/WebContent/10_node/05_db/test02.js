const mysql = require('mysql');

var con = mysql.createConnection({
    user : "hanbit",
    password : "hanbit",
    database : "hanbit"
});

con.connect();

var sql = "select no, title, writer from tb_board order by no desc";
con.query(sql, function (err, result) {
    if(err){
        console.log("err");
        return;
    }
    console.log("결과출력");
    console.log(result);
    result.forEach(row => {
        console.log(row.no, row.title, row.writer);
    })

})

con.end();
