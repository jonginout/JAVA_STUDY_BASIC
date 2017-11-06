/* 

    Node를 이용한 외부 모듈 사용하기
    써드파티 모듈 : 개발사에서 만든게 아니라 일반 개발자들이 만들어서 배포하는 것

    prompt-sync : 사용자의 입력을 기다렸다가 수행하는 것 
    사용자 화면에서 데이터를 입력받게 처리됨..
    (자바에서 스캐너..)

    외부모듈을 가져오기
    - 사용하려는 모듈을 npm을 통해서 다운받는다(가져온다).

    WEBCONTENT에 설치해라 꼭 (cd..)
    명령어 : npm install 모듈명 
    https://www.npmjs.com/
    
    - 실행시에는 require() 함수 이용

*/

var prompt = require('prompt-sync')();
// 
// get input from the user. 
// 
var n = prompt('How many more times? ');
console.log("n : ", n)