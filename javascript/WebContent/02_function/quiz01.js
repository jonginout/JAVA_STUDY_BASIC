"use strict"
var prompt = require('prompt-sync')();

var menuArr = [
    {"msg" : "1. 전체 게시물 조회", "input" : []},
    {"msg" : "2. 글번호 조회", "input" : ["조회할 글 번호를 입력하시오"]},
    {"msg" : "3. 글등록", "input" : ["제목을 입력하시오", "작성자를 입력하시오"]},
    {"msg" : "4. 글수정", "input" : ["수정할 글 번호를 입력하시오","제목을 입력하시오", "작성자를 입력하시오"]},
    {"msg" : "5. 글삭제", "input" : ["삭제할 글 번호를 입력하시오"]},
    {"msg" : "0. 종료", "input" : []}
];

var boardList = [
    {"no" : 1, "writer" : "종인", "title": "첫번째 글이다~!"},
    {"no" : 2, "writer" : "성희", "title": "두번째 글이다~!"},
    {"no" : 3, "writer" : "제현", "title": "세번째 글이다~!"}
];

var board = (menuArr, boardList, run) => {
    for (var m in menuArr) {
        console.log(menuArr[m].msg)
    };
    var select = prompt('기능 선택 : ');
    select = select -1;
    for(var input of menuArr[select].input){
        select = prompt(input);
        run(select);
    }
};


board(menuArr, boardList, (select) => {
    if(select==0){
        for( var v of boardList){
            console.log(v);
        }
    }
});

