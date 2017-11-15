
"use strict"

function Member(id, name, email) {
    this.id = id;
    this.name = name;
    this.email = email;
    ////////////////////////////////////////
    this.info = function () {
        console.log(this.id, this.name, this.email);
    }
    ////////////////////////////////////////
    // 이 함수 부분은 어떤 객체마다 공통인데 뭔가 비효율적이다.(메모리 낭비)
    // =====> 해결책 : 프로토타입
}


var m1 = new Member("hz", "헤이즈", "hz@naber.com"); 
var m2 = new Member("hz2", "헤이즈2", "hz22@naber.com"); 

m1.info(); //hz 헤이즈 hz@naber.com
m2.info(); //hz2 헤이즈2 hz22@naber.com

console.log(m2.info())
//hz2 헤이즈2 hz22@naber.com undefined
