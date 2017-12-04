/*

이벤트를 연결 할 수 있는 모든 객체는 EventEmitter 객체를 상속 받는다.
사용자가 이벤트를 직접 정의하고 싶다면 EventEmitter 객체를 이용해야 한다.

이벤트를 설정할 때 : on
설정된 이벤트를 사용 : emit

*/

var EventEmitter = require("events").EventEmitter;

// 객체 상속을 쉽게 처리하기 위해서 util 모듈 사용
var util = require("util");


//이벤트를 설정하려는 사용지 정의 객체 정의
var Member = function () {};

// Member가 EventEmitter를 상속한다
util.inherits(Member, EventEmitter);

//이제 Member객체는 이벤트를 정의하고 사용 가능하다.

// 객체 프로토 타입에 함수 정의
Member.prototype.join = function () {
    // on에  join 이라고 되어있는 이벤트를 발생시키겠다.
    this.emit("join", "넘겨줄값", "넘겨줄값2", {key:"넘겨줄값3"})
};
Member.prototype.info = function () {
    console.log("info 함수 호출");
    this.emit("info");
};

var m = new Member();

// m객체에 이벤트 등록하기 : on // on은 상속받은 EventEmitter에 있는 것
m.on("join", function (v1, v2, v3) {
    console.log("join 이벤트 발생");
    console.log(v1, v2, v3);
});
m.on("info", function () {
    console.log("info 이벤트 발생");
});


// 프로토 타입에 정의된 함수 설정
m.join();
m.info();
