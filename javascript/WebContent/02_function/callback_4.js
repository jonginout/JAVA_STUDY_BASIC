"use strict"

var arrFor = (arr, cb) => {
    for(let i = 0; i < arr.length; i++){
        cb(i, arr[i]);
    }
}

let arr = [1,2,3,4,5];
let sum = 0;

// for(let i = 0; i < arr.length; i++){
//     sum += arr[i];
// }

// console.log("합 : ", sum);

arrFor(arr,(i, val) => { sum += val })
console.log("합 : ", sum);


var sum2 = 0;
// 홀수번째 인덱스 위치의 값을 합 출력
arrFor(arr,(i, val) => { 
    if(i%2==1){
        sum2 += val 
    }
})
console.log("홀수의 합 : ", sum2);
