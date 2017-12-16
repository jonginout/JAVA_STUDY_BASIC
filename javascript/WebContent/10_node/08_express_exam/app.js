const express = require('express');
const bodyParser = require('body-parser');
const app = express();
const pug = require("pug");
const path = require('path');

app.use(express.static("./publics"));

var board = require("./routes/board");
app.use("/board", board);

app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'pug');


app.get("/", function (req, res) {
	res.render("index");
});



// error 처리...
app.use(function (err, req, res, next) {
	res.writeHead(200, {"Content-Type" : "text/html; charset=utf-8"})
	console.error(err);
	res.end("<h1>오류!</h1>")
});

app.listen(3000, function () {
	console.log("서버 구동 중....");
});
