// 객체 퀴즈

var myData = new MyMap();

myData.put("id", "sss");
myData.put("name", "홍짜장");

console.log(myData.size()); // 2
console.log(myData.get("id")); // sss
myData.del("id"); // 하나만 지우는거
console.log(myData.get("id")); // undefined
myData.clear(); // 다지우는거
console.log(myData.size()); // 0

/* 

    마지 Mapper 처럼
    myData 객체를 만들어라

*/