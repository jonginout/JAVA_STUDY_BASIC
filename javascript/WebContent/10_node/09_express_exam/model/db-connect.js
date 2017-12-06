const mysql = require("mysql");

// 커넥션...
var con = mysql.createConnection({
	user: "hanbit",
	password: "hanbit",
	database: "hanbit"
});


module.exports = con;
// 디비 연결 정보


