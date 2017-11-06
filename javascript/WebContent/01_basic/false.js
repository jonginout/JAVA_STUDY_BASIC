/*

  자바스크립트 false 나타내는 값
  
  5가지
  null, undefined, 0, '', NaN

*/
"use strict"

console.log("!0", !0, !!0);
console.log("null", !!null, !!!null);
console.log("100", Boolean(100));
console.log("0", Boolean(0));
console.log("test", Boolean("test"));
console.log("", Boolean(""));