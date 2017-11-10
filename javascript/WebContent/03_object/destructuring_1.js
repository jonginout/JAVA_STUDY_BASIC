/*

  ECAMA6 - 객체 추가기능 3

  디스트럭팅 ( Destructuring )
    - 객체에 입력된 값을 개별적인 변수에 할당을 편리하게  처리


*/

var m = {
  id : "hz",
  name : "헤이즈",
  email : "ewqmk@as.com"
};


// es6 부터 가능한
var {id, name, email, addr="서울시"} = m;
// var id = m.id;
// var name = m.name;
// var email = m.email;

console.log(m);
console.log(addr);
