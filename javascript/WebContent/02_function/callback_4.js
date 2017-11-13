"use strict"

var arrFor = (arr, callBack) => {
    for(let i = 0; i < arr.length; i++){
        callBack(i, arr[i]);
    }
}

var arr = [1,2,3,4,5];
var sum = 0;

arrFor( arr , (i, val) => { sum += val } ); //함수의 인자로 함수를
console.log("합 : ", sum); //15



// for(let i = 0; i < arr.length; i++){
//     sum += arr[i];
// }

// console.log("합 : ", sum);



var sum2 = 0;
// 홀수번째 인덱스 위치의 값을 합 출력
arrFor(arr,(i, val) => { 
    if(i%2==1){
        sum2 += val 
    }
})
console.log("홀수의 합 : ", sum2);
