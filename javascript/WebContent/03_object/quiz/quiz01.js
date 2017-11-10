// 객체 퀴즈

function MyMap() {
  this.count = 0; // put 할때 마다 ++
  this.data = {}; // 데이터가 쌓이는 공간
}

MyMap.prototype = {
  put : function(key, value) {
    this.count++;
    this.data[key] = value;
  },
  size : function () {
    return this.count;
  },
  get : function (key) {
    return this.data[key];
  },
  del : function (key) {
    if(key){
      delete this.data[key];
      this.count--;
    }
  },
  clear : function () {
    this.count = 0;
  }
};


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

    마지 Mapper 처럼/
    myData 객체를 만들어라

*/
