/* 

    클로저
    - 함수내의 지역변수를 함수 외부에서 사용할 수 있게 하는 기술
    - 생명주기가 종료된 함수의 변수를 참조하는 기술


*/

function outer(val){
    var msg = "당신의 메시지 : "+ val;
    return function () {return msg;} 
    //원래는 outer 함수가 끝나면 메모리서 msg는 없어져야 하는데 
    //리턴한 함수가 msg 이기 때문에 msg 변수가 안없어지고 유지되는 것
}


var fn = outer("하이");
console.log(fn());
