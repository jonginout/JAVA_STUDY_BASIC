var express = require('express');
var router = express.Router();

router.get("/list.do", function (req, res) {

    console.log("게시글 리스트");
    res.end();
});

router.get("/write.do", function (req, res) {

    console.log("게시글 쓰기");
    res.end();
});


module.exports = router;
