/* 

    특정한 시간뒤에 함수를 실행 시킨다.
    (콜백함수)

    var id = setTimeout(콜백함수, 밀리세컨드);
    
    - 등록된 함수의 정보를 취소하기
    clearTimeout(id);

*/

//몇초에 한번씩 에이젝스 쓰도록

var func = () => {
    var date = new Date();
    var time = date.toLocaleTimeString();
    console.log(time);
    
    var id = setTimeout(func, 1000);
    clearTimeout(id);
}


func();



console.log("??SD?A?D")