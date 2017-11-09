/* 



*/

function nextId(val){
    var id = 1;
    return function () { return id++; };
}

var id1 = nextId();
console.log(id1()); //1  //++이 뒤에 있으니깐
console.log(id1()); //2
// 클로저 때문에 id변수는 메모리에 안없어지고 유지된다.

var id2 = nextId();
console.log(id2()); //1
console.log(id2()); //2