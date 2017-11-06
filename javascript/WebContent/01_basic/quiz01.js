var prompt = require('prompt-sync')();

var menuArr = [
    "1. 전체 게시물 조회",
    "2. 글번호 조회",
    "3. 글등록",
    "4. 글수정",
    "5. 글삭제",
    "0. 종료"
];

var boardList = [
    {"no" : 1, "writer" : "종인", "title": "첫번째 글이다~!"},
    {"no" : 2, "writer" : "성희", "title": "두번째 글이다~!"},
    {"no" : 3, "writer" : "제현", "title": "세번째 글이다~!"}
];

home:while (true) {
    for (var v of menuArr) {
        console.log(v)
    }
    var select = prompt('메뉴 중 처리할 항목을 선택하세요');

    switch (select) {
        case "1":
             console.log(menuArr[0])

             console.log("========================")
             console.log("글번호 | 작성자 | 글제목")
             for (var b of boardList) {
                 console.log(b.no, b.writer, b.title)
                }
            console.log("========================")
            
            
            break;
            case "2":
            console.log(menuArr[1])
            
            console.log("========================")
            var findNo = prompt('조회할 글 번호를 입력하세요');
            console.log(boardList[findNo]);
            console.log("========================")

            break;
        case "3":
            console.log(menuArr[2])
            
            console.log("========================")
            var title = prompt('제목을 입력하세요');
            var writer = prompt('작성자를 입력하세요');
            var newBoard = {
                "no" : boardList.length+1,
                "writer" : writer,
                "title" : title
            };
            boardList.push(newBoard)
            console.log("========================")

            break;
        case "4":
            console.log(menuArr[3])

            console.log("========================")
            var no = prompt('수정할 글 번호를 입력하세요');
            var title = prompt('수정할 제목을 입력하세요');
            var writer = prompt('수정할 작성자를 입력하세요');
            boardList[no-1].title = title;
            boardList[no-1].writer = writer;
            console.log(boardList[no-1]);
            console.log("========================")

            break;
        case "5":
            console.log(menuArr[4])


            console.log("========================")
            var no = prompt('삭제할 글 번호를 입력하세요');
            boardList.splice(0,1); // 1번째 위치다음 부터 몇개 
            console.log("========================")

            break;
        case "0":
            console.log(menuArr[5])
            break home;
        default:
            console.log("잘못된 입력 입니다.")
            break;
    }
    continue;
}
console.log("종료");