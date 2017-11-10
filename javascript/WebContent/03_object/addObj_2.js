/*

  ECAMA6 - 객체 추가 1

  객체의 프로퍼티를 단축해서 사용한다 (함수!! )

*/


let id = "hong",
    name = "홈길돔",
    age = 15;

let m1 = {
    id : id,
    name : name,
    age : age,
    info : function () {
      console.log(this.id, this.name, this.age);
    }
}
console.log(m1);
m1.info();

// 프로퍼티 값으로 변수를 사용하는 경우
// 프로퍼티의 이름을 생략할 수 있다.

let m2 = {
  id, name, age,
  info(){
    console.log(this.id, this.name, this.age);
  }
};
console.log(m2);
m2.info();
