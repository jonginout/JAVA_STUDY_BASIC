//호이스팅 퀴즈
"use strict"
console.log(id);

var id = "테스트";
console.log(id);


/*
    컴파일 단계
    var id; // 5줄


    실행 단계
    console.log(id); // undefined
    id = "테스트";
    console.log(id);


*/