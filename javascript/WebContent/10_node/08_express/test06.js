const express = require('express');
const bodyParser = require('body-parser');
const app = express();


var board = require("./route/board");
var member = require("./route/member");
app.use("/board", board);
app.use("/member", member);


app.use(bodyParser.json()); // application/json
app.use(bodyParser.urlencoded({extended:true})); // json 안에 json 까지 파싱해준다.
// application/x-www-form-urlencoded
// request.body 가 생성 되는것..



app.get("/", function (req, res) {

    console.log("루트");
    res.end();

    // res.sendFile(__dirname + "/views/form.html");

    // fs.readFile("views/form.html", function (err, data) {
    //     res.writeHead(200, {"Content-Type" : "text/html; charset=utf-8"});
    //     res.end(data);
    // });

});


app.listen(3000, function () {
    console.log("서버 구동 中.....");
})
