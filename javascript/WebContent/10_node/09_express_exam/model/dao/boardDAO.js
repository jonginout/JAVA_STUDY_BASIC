var con = require('../db-connect');


var boardDAO = {

    list : function(cb){
        var sql = `select * from tb_board order by no desc`;
        con.query(sql, function (err, rows, fields) {
            if (err) { 
                cb(err);
                // 자바스크립트는 오버로딩이 없으니깐 파라미터가 1개여도 콜백함수 실행 가능
                return;
            }
            cb(null, rows); // err를 구분하기 위한 null
        });
    },

    detail : function(arr, cb){
        var sql = `select * from tb_board swhere no = ?`;
        con.query(sql, arr, function (err, row, fields) {
            if (err) {
                cb(err);
                return;
            }
            cb(null, row[0]);
        });
    },

    delete : function(arr, cb){
        var sql = `delete from tb_board where no = ?`;
        con.query(sql, arr, function (err, row, fields) {
            if (err) {
                cb(err);
                return;
            }
            cb(null); //리다이렉션
           // 에러쪽은 널 어차피 result 필요 없으니깐
        });
    }

}



module.exports = boardDAO;