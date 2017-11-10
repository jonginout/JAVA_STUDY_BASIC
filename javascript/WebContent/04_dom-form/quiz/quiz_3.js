function isEmpty(obj, msg){
    if(!obj.value){
        alert(msg);
        obj.focus();
        return true; //비어있으면
    }
    return false;
}

function iscareerEmpty() {

    var career = document.mForm.career;
    var time = document.mForm.time;

    var chk = true;
    for (var i = 0; i < career.length; i++) {
        if(career[i].checked){
            chk = false;
        }
    }
    if (chk) {
        alert('경력을 하나 이상 체크하시오.');
        return true;
    }

    if(career[1].checked){
        if(time.value==0){
            alert('경력체크시 기간을 선택하시오.');
            return true;
        }
    }
}

function checkOld(chkStatus) {
    var time = document.mForm.time;
    if(chkStatus){
        time.selectedIndex = 0;
        time.disabled = chkStatus;
    }else {
        time.disabled = chkStatus;
    }
}


function doSubmit() {

    var f = document.mForm;

    if(isEmpty(f.name, "이름 입력하세요")) return false;
    if(isEmpty(f.id, "아이디를 입력하세요")) return false;
    if(isEmpty(f.pass1, "비밀번호를 입력하세요")) return false;
    if(isEmpty(f.pass2, "비밀번호 확인을 입력하세요")) return false;

    if(f.pass1.value != f.pass2.value){
        alert("비밀번호가 일치하지 않습니다.");
        f.pass1.focus();
        f.pass1.select();

        f.pass2.value="";
        return false;
    }

    if(iscareerEmpty()) return false;


    if(isEmpty(f.email, "이메일을 입력하세요")) return false;

    alert('서브밋 성공!');
    return true;
}
