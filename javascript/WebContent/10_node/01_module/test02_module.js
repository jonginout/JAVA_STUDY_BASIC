/*

var cal = {
    add(i, j){ return i + j },
    // ecma6 부터는 객체에 함수를 등록할때 키밸류로 안주고 이렇게 해도 된다.
    // add가 키 return이 밸류
    sub(i, j){return i - j }
}

module.exports = cal;
// exports에 cal 객체 자체를 대입한거다

*/

module.exports = {
    add(i, j){ return i + j },
    // ecma6 부터는 객체에 함수를 등록할때 키밸류로 안주고 이렇게 해도 된다.
    // add가 키 return이 밸류
    sub(i, j){return i - j }
};
// exports에 cal 객체 자체를 대입한거다
