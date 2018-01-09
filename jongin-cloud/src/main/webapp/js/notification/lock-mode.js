var checkedInit = user.lockMode=='T' ? true : false;
var notificationApp = new Vue({
el: '.lock',
data: {
  checked: checkedInit,
},
methods: {
    lockMode : function (e) {
        e.preventDefault() // 무조건 이벤트를 미리 막아야 한다.
        
        var pass = prompt("잠금모드\n비밀번호를 입력하시오.");
        if(!pass) return;
        //

        var changeLock = user.lockMode == 'T' ? 'F' : 'T'
        var data = new URLSearchParams()
        data.append('memberNo', user.memberNo);
        data.append('id', user.id);
        data.append('pass', pass);
        data.append('lockMode', changeLock);

        axios.post(projectURL+"/login/lock.json", data)
        .then(function (res) {
            var checkedNew = changeLock=='T' ? true : false;

            if(res.data.result){

                user.lockMode = changeLock;
                notificationApp.checked = checkedNew
            }else{
                alert("비밀번호가 일치하지 않습니다. 보안시스템 작동");
            }

        })
        .catch(function (error) {
            alert("서버에러 관리자에게 문의하세요.")
        })

    }
    }
});



///////////////////제이쿼리 구버젼
  
  // $(function(){
  //   var checkedInit = user.lockMode=='T' ? true : false;
  //   if(checkedInit){
  //     var html = '잠금 모드 <i class="fa fa-lock" aria-hidden="true"></i>';
  //     $("#lock-label").html(html)
  //   }else{
  //     var html = '잠금 해제 <i class="fa fa-unlock" aria-hidden="true"></i>';
  //     $("#lock-label").html(html)
  //   }
  //   $("#lock-check").prop("checked", checkedInit)
  // })
  




  // $("body").on("click", "#lock-check", function(){
  //   var checkedInit = user.lockMode=='T' ? true : false;

  //   var pass = prompt("잠금모드\n비밀번호를 입력하시오.");
  //   if(!pass){
  //     $("#lock-check").prop("checked", checkedInit)
  //     return;
  //   }

  //   var checked = Boolean($("#lock-check").prop("checked"))
  //   checked = checked ? 'T' : 'F';


  //     $.ajax({
  //       type : "POST",
  //       url : projectURL+"/login/lock.json",
  //       data : {
  //         memberNo : user.memberNo,
  //         id : user.id,
  //         pass : pass,
  //         lockMode : checked
  //       },
  //       dataType : "JSON",
  //       beforeSend : function(){
  //         $("#lock-check").prop("checked", checkedInit)
  //       },
  //       success : function (data){

  //         if(data.result){
  //           //성공

  //           user.lockMode = checked;
  //           var checkedNew = user.lockMode=='T' ? true : false;
  //           $("#lock-check").prop("checked", checkedNew)
  //           if(checkedNew){
  //             var html = '잠금 모드 <i class="fa fa-lock" aria-hidden="true"></i>';
  //             $("#lock-label").html(html)
  //           }else{
  //             var html = '잠금 해제 <i class="fa fa-unlock" aria-hidden="true"></i>';
  //             $("#lock-label").html(html)
  //           }

  //         }else{

  //           //실패
  //           $("#lock-check").prop("checked", checkedInit)
  //           alert("비밀번호가 일치하지 않습니다. 보안시스템 작동");

  //         }
  //     },
  //     error : function(){
  //       $("#lock-check").prop("checked", checkedInit)
  //     }
  //   })


  // })