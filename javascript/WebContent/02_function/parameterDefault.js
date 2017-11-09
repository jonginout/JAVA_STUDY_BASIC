/* 

    ECMA6에서 추가된 파라미터 디폴드 설정하기

    파라미터에 디폴트 값을 설정해서
    간편하게 파라미터 값이 안온 경우 초기화를 시킨다.

*/

function func (num1,num2){

    num1 = num1 || 0;
    num2 = num2 || 0;


    // if(!num1){
    //     num1 = 0;
    // }
    // if(!num2){
    //     num2 = 0;
    // }

    console.log(num1+num2);
}

// 파라미터가 일치하지 않아도 호출이 가능 
func();             // 0
func(100);          // 100
func(100, 200);     // 300



/////////////////////////////////////////////////


function func2 (num1 = 0, num2 = 0){ //  = 값  > 없을떄 기본값;
        console.log(num1+num2);
    }
    

    func2();             // 0
    func2(100);          // 100
    func2(100, 200);     // 300