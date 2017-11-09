/* 

    클로저 연습

*/

function calClosure(num1, num2){
    return function (op) {
        switch (op) {
            case "+": return num1+num2;
            case "-": return num1-num2;
            case "/": return num1/num2;
            case "*": return num1*num2;
        }
    };
};


var cal = calClosure(10, 2);
console.log(cal("+")); // 12
console.log(cal("-")); // 8
console.log(cal("/")); // 6
console.log(cal("*")); // 20
