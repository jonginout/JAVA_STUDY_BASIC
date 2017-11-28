$(document)
.ajaxStart(function () {
    $("body").waitMe({
        effect: 'bounce',
        text : '좀 쳐 기다려 세끼야',
        bg: 'rgba(255,255,255,0.7)', //바탕화면 약간 어둡
        color: '#000'
    });
})
.ajaxStop(function () {
    $("body").waitMe("hide");
})
