const express = require("./express"); 
const app = express();

// 위 아래 똑같은 것
// const app = require("./express")(); // 가져온 함수를 실행한다.


app.listen(3000, function(){
    console.log("3000번 포트로 서버를 구동중 입니다...");
})