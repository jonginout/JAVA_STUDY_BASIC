/* 
    setTimeout
    - 특정 시간 이 후, 단 한번만 특정 함수 또는 코드를 실행시킬 때 사용합니다.
    - 2번째 인자의 시간(Milliseconds)이 경과하면 1번째 인자의 함수를 실행합니다.
    - 실행중인 루프 종료방법 clearTimeout(SETINTERVAL_NAME);
*/

var i = 1;
function ff() {
    console.log("setTimeout : ",i++);
    if(i==5){clearTimeout(id);}
}
var id = setTimeout( ff , 500); // 0.5초가 지나면 ff를 한번만 실행

//=======================================================================//

/* 
    setInterval
    - 특정 시간 마다 특정 함수 또는 코드를 실행시킬 때 사용합니다.
    - 2번째 인자의 시간(Milliseconds)이 지날때마다 1번째 인자의 함수를 실행합니다.
    - 실행중인 루프 종료 방법 clearInterval(SETINTERVAL_NAME);
*/


var i2 = 10;
function ff2(){
    console.log("setInterval : ",i2++);
    if(i2==15){clearInterval(id);}
}

var id = setInterval(ff2, 300); // 0.3초마다 ff2를 반복적으로 실행