
/* 
ECMA5 : 제공 반복문
- forEach


- for~in
객체 정보를 뽑을때 주로 사용
객체의 키를 반환

ECMA6 : 제공 반복문
- for ~ of
이터러블 객체를 대상으로 처리한다.

*/

var arr = [1, 2, 3, 4, 5, 6];

for(let i = 0; i<arr.length; i++){
    console.log(arr[i]);
}


/* 
    객체일 경우에는 for ~ in 
    배열도 객체임.. for ~ in
*/


for (let key in arr) {
    console.log(key, arr[key]);
}


/* 
    배열객체.forEach(function (값, 인덱스, 배열객체) {});

    콜백함수 : 다른 함수에 의해서 호출되거나 이벤트 등에 의해서 호출되는 방식
    어떤 특정한 상황에서 호출되는 방식

    반복 중간에 빠져나올 수 없다.
*/
arr.forEach(function(val, index, array) {
    console.log("val", val, "index", index, array);
});


/*
    for ~ of : value 값을 가져오기 위한 편리한 반복문
*/

for(let val of arr){
    console.log(val);
}