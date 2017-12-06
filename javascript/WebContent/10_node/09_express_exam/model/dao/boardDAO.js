var con = require('../db-connect');


var boardDAO = {

    list : function(cb){
        var sql = `select * from tb_board order by no desc`;
        con.query(sql, function (err, rows, fields) {
            if (err) {
                cb(err);
                return;
            }
            cb(null, rows); // err를 구분하기 위한 null
        });
    },

    detail : function(arr, cb){
        var sql = `select * from tb_board where no = ?`;
        con.query(sql, arr, function (err, row, fields) {
            if (err) {
                cb(err);
                return;
            }
            cb(null, row[0]);
        });
    },

    delete : function(){
        var sql = `delete from tb_board where no = ?`;
        con.query(sql, arr, function (err, row, fields) {
            if (err) {
                cb(err);
                return;
            }
            cb(null, row[0]); //리다이렉션
        });
    }

}



module.exports = boardDAO;