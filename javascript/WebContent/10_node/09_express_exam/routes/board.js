var express = require('express');
var router = express.Router();
var boardService = require('../model/sevice/boardService');


router.get("/list", function (req, res, next) {

    boardService.list(function(err, result){
        if (err) {
            next(err); // err 처리는 app.use는 그래서 app.js 맨마지막에 존재
            return;
        }
        res.render("board/list", {boards : result});
    })

});

router.get("/detail/:no", function (req, res, next) {

    boardService.detail(req.params.no, function(err, result){
        if (err) {            
            next(err);
            return;
        }
        res.render("board/detail", {board : result});
    })

});

router.get("/delete/:no", function (req, res) {

    boardService.delete(req.params.no, function(err, result){
        if (err) {
            next(err);
            return;
        }
        res.redirect("/board/list");
    })

});


module.exports = router;
