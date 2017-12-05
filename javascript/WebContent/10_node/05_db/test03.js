const mysql = require('mysql');

var con = mysql.createConnection({
    user : "hanbit",
    password : "hanbit",
    database : "hanbit"
});

// con.connect();
// con.query()가 없으면 자동으로 con.connect를 실행해줌

var sql = "select no, title, writer \
            from tb_board \
            order by no desc \
            limit ?, ?"; //0번쨰 위치부터 몇개 가져올래
            // ? 는 값을 동적으로

            // 이부분에 순서대로 인자
con.query(sql, [0, 2], function (err, result) {
    if(err){
        throw err;
    }
    console.log("==============결과출력=============");
    result.forEach(row => {
        console.log(row.no, row.title, row.writer);
    })

})

con.end();
