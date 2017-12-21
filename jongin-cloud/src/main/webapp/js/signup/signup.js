"use strict";



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
			url : url+"/login/idchk.do",
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
		
		for(let i = 0; i < ptn.length; i ++) {
			if(ptn[i].matches() == false) {
				alert(ptn[i].msg + ", " + ptn[i].id.val());
				ptn[i].id.focus();
				e.preventDefault();
				return;
			}
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
	
});

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
		success : function(data) {
			alert("회원가입 성공!! 로그인 페이지로 이동합니다.")
			location.href = projectURL+"/login/loginform.do"
		},
		error : function(data) {
			alert("회원가입 실패")
		}
	})
	
	
}







