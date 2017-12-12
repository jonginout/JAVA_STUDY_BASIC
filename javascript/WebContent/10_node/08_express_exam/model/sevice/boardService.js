var boardDAO = require('../dao/boardDAO');

var boardService = {

    list : function(cb){
        boardDAO.list(function(err, result){ 
            // 자바스크립트는 오버로딩이 없으니깐 파라미터가 1개여도 콜백함수 실행 가능
            // ex) db(err) 로만 실행 했지만 이 콜백함수 실행 가능
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
    },

    delete : function(arr, cb){
        boardDAO.delete(arr, function(err, result){
            if (err) {
                cb(err);
                return;
            }
            cb(null); //리다이렉션
           // 에러쪽은 널 어차피 result 필요 없으니깐
        })
    }

}
    

module.exports = boardService

// dao는 쿼리문을 무조건 한번하지만
// 서비스는 한번에 dao를 2번 부를 수 있기 때문에 
// 서비스에서 거친다.