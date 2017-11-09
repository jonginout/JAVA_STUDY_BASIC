"user strict"

var mans = ["양희석", "조제현", "김종인", "윤현구", "강현수", "이원준"];

var count = 10;
var id = setInterval(()=>{
    if(count==0){
        var no = parseInt(Math.random() * mans.length);
        console.log(mans[no]);
        clearInterval(id);
        return
    }
    console.log(count--);
}, 1000);