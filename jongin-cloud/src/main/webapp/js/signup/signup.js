"use strict";

//sms 인증
var AUTHSMSKEY = false;

$(function() {
	
	var chk = false;
	
	$("#id").keyup(function () {
		
		var url = $(".has-feedback").attr("data-url");
		var id = $(this).val();
		
		var id = $(this).val();
		var idPattern = /^[a-z0-9]{4,12}$/;
		if (!idPattern.test(id)) {
			$(".has-feedback").removeClass("has-success").addClass("has-error");
			$(".has-feedback>span").removeClass("glyphicon-ok").addClass("glyphicon-remove");
			chk = false;
			return;
		}

		
		$.ajax({
			global : false,
			type : "POST",
			url : projectURL+"/login/idchk.do",
			data : {id:id},
			dataType : "json",
			success : function (d) {
				console.log(d.result)
				if(!d.result){ 
					$(".has-feedback").removeClass("has-error").addClass("has-success");
					$(".has-feedback>span").removeClass("glyphicon-remove").addClass("glyphicon-ok");
					chk = false;
				}else {
					$(".has-feedback").removeClass("has-success").addClass("has-error");
					$(".has-feedback>span").removeClass("glyphicon-ok").addClass("glyphicon-remove");
					chk = true;
				}
			}
		})
		
	})
	


	$("#signup-formBtn").click((e) => {
		var ptn = [];
		ptn.push(new Pattern($("#id"), /^[a-z0-9]{4,12}$/, "아이디는 4~12자 소문자 영문과 숫자를 조합해서만 사용 가능합니다."));
		ptn.push(new Pattern($("#pass"), /^[A-Za-z0-9]{6,12}$/, "비밀번호는 6~12자 영문과 숫자를 조합해서만 사용 가능합니다.", 1));
		ptn.push(new Pattern($("#passChk"), /^[A-Za-z0-9]{6,12}$/, "비밀번호는 6~12자 영문과 숫자를 조합해서만 사용 가능합니다.", 1));
		ptn.push(new Pattern($("#name"), /^[a-z0-9가-힣]{1,10}$/, "이름은 1~10자 까지 가능합니다."));
		ptn.push(new Pattern($("#sample6_postcode"), /^[0-9]{1,}$/, "우편 번호를 확인하세요."));
		ptn.push(new Pattern($("#sample6_address"), /^().+$/, "주소를 확인하세요."));
		ptn.push(new Pattern($("#sample6_address2"), /^().+$/, "상세 주소를 확인하세요."));
		ptn.push(new Pattern($("#email1"), /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*$/i, "이메일 주소를 확인하세요."));
		ptn.push(new Pattern($("#email2"), /^[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i, "이메일 주소를 확인하세요."));
		ptn.push(new Pattern($("#tel"), /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/, "잘못된 휴대폰 번호입니다. - 를 포함한 숫자를 입력하세요."));

		for(let i = 0; i < ptn.length; i ++) {
			if(ptn[i].matches() == false) {
				alert(ptn[i].msg + ", " + ptn[i].id.val());
				ptn[i].id.focus();
				e.preventDefault();
				return;
			}
		}
		
		if(!$("#key").val()){
			alert("인증키를 입력하세요!")
			$("#key").focus();
			return;
		}

		if(!AUTHSMSKEY){
			alert("휴대폰 본인인증을 해주세요.");
			$("#tel").focus();
			return;
		}
		
		if($("input[name=images]").val() == "") {
			alert("프로필 사진을 등록해주세요.");
			e.preventDefault();
			return;
		}
		
		submitForm()
	});
	
	$("input[name=images]").on("change", function() {
	    let file = this.files[0],
			fileName = file.name;
	    
		$("input[name=profileInfo]").val(fileName);
	});
	
	// sms-auth-input
	$("#sendSMSBtn").click(function(){

		var tel = $("#tel").val();
		var pattern = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/;
		if (!pattern.test(tel)) {
			alert("잘못된 휴대폰 번호입니다. - 를 포함한 숫자를 입력하세요.");
			return false;
		}
		sendSmsKey(tel)

	})

	$("#authKeyBtn").click(function(){
		AUTHSMSKEY = false;

		var inputTel = $("#tel").val();		
		var inputKey = $("#key").val();

		if(!inputTel){
			alert("인증키를 입력하세요!")
			return;
		}
		if(!inputKey){
			alert("인증키를 입력하세요!")
			return;			
		}
		
		$.ajax({
			type : "post",
			url : projectURL+"/login/keycheck.json",
			data : {
				tel : inputTel,
				authKey : inputKey
			},
			dataType : "json",
			success : function(data){
				if(data.result){
					alert("인증 성공!")
					$("#tel").attr("disabled", true);
					$("#sendSMSBtn").attr("disabled", true);
					$("#key").attr("disabled", true);
					$("#authKeyBtn").attr("disabled", true);
					AUTHSMSKEY = true;
				}else{
					alert("인증번호를 확인하세요.")
				}
			},
			error : function(){
				alert("서버오류 개발자에게 문의하세요!")
			}
		})

	})
	
	
});

function saveAuthKey(tel, randomKey){
	AUTHSMSKEY = false;

	$("#sendSMSBtn").attr("disabled", true);

	$.ajax({
		type : "post",
		url : projectURL+"/login/keysave.json",
		data : {
			tel : tel,
			authKey : randomKey
		},
		dataType : "json",
		success : function(data){
			if(data.dup){
				if(confirm("이미 요청한 인증키가 존재합니다.\n재요청 하시겠습니까?")){
					smsKeyResend(tel);
				}
			}else{
				$(".auth-input").show();
			}
		},
		error : function(){
			alert("서버오류 개발자에게 문의하세요!")
		}
	})

	$("#sendSMSBtn").attr("disabled", false);
}

function smsKeyResend(tel){
	AUTHSMSKEY = false;

	$.ajax({
		type : "post",
		url : projectURL+"/login/keydelete.json",
		data : {
			tel : tel
		},
		success : function(){
			sendSmsKey(tel)
		},
		error : function(){
			alert("서버오류 개발자에게 문의하세요!")
		}
	})
}


function sendSmsKey(tel){
	AUTHSMSKEY = false;

	var randomKey = Math.random().toString(36).substring(7);

	if(!AUTHSMSKEY){
		$.ajax({
			type : "POST",
			url : projectURL+"/login/sms.json",
			data : {
				action : "go",
				smsType : "S",
				msg : "종인 클라우드 인증코드 : [ "+randomKey+"]",
				rphone : tel,
				sphone1 : "010",
				sphone2 : "4021",
				sphone3 : "6749",
				// testflag : "Y" //테스트
			},
			dataType : "json",
			success : function(data){
				//010-4021-6749
				if(!data.result){
					alert("서버에러! 개발자에게 문의하세요!")						
				}else{
					saveAuthKey(tel, randomKey)
					alert("인증키가 문자로 발송되었습니다.")
				}
			},
			error : function(data){
				alert("인증키 전송 에러!")
			}
		})
	}

}

function Pattern(id, pettern, msg, sameNo = -1) {
	this.id = id;
	this.pettern = pettern;
	this.msg = msg;
	this.sameNo = sameNo;
	
	this.matches = function() {
		return this.pettern.test(this.id.val());
	};
};

function submitForm() {
	
	var fd = new FormData($("#signup-form")[0]);
	
	$.ajax({
		type : "post",
		url : projectURL+"/login/signup.json",
		data : fd,
		processData : false,
		contentType : false,
		beforeSend: loadingAjax("회원가입 처리중..."),
		success : function(data) {
			loadingStopAjax();
			alert("회원가입 성공!! 로그인 페이지로 이동합니다.")
			location.href = projectURL+"/login/loginform.do"
		},
		error : function(data) {
			loadingStopAjax();
			alert("회원가입 실패")
		}
	})
	
	
}







