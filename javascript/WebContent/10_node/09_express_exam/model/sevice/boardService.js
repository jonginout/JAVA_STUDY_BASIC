var boardDAO = require('../dao/boardDAO');

var boardService = {

    list : function(cb){
        boardDAO.list(function(err, result){
            if (err) {
                cb(err);
                return;
            }
            cb(null, result);
        })
    },

    detail : function(arr, cb){
        boardDAO.detail(arr, function(err, result){
            if (err) {
                cb(err);
                return;
            }
            cb(null, result);
        })
    }


}
    

module.exports = boardService

// dao는 쿼리문을 무조건 한번하지만
// 서비스는 한번에 dao를 2번 부를 수 있기 때문에 
// 서비스에서 거친다.