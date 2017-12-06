
module.exports = function () {
    return {
        // use : function () {console.log("use")},
        // get : function () {console.log("get")},
        // post : function () {console.log("post")}
        // ecma6 부터는 이게 밑에랑 똑같은 거다.

        use () {console.log("use 호출")},
        get () {console.log("get 호출")},
        post () {console.log("post 호출")}
    };
    // 객체를 리턴
};
