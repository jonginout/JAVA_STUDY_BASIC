var express = require('express');
var router = express.Router();

router.get("/list.do", function (req, res) {

    console.log("회원 목록");
    res.end();
});

router.get("/join.do", function (req, res) {

    console.log("회원 등록");
    res.end();
});


module.exports = router;
