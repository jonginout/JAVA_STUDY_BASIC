
var isNull = true;
	$("#id,#pass").on("keyup", function () {
		var len = $(this).val().length;
		if(len>0){
			$(this).parent().removeClass("has-error").addClass("has-success");
			$(this).next().removeClass("glyphicon-remove").addClass("glyphicon-ok");
			isNull = false;
		}else {
			$(this).parent().removeClass("has-success").addClass("has-error");			
			$(this).next().removeClass("glyphicon-ok").addClass("glyphicon-remove");
			isNull = true;
		}
	})
	
	$("#login-formBtn").click(function () {
		if(!isNull){
			$.ajax({
				type : "POST",
				url : projectURL+"/login/login.json",
				data : {
					id : $("#id").val(),
					pass : $("#pass").val(),
					save : $("#save").prop("checked")
				},
				beforeSend : loadingAjax("로그인 중.."),
				success : function (data) {
					loadingStopAjax();
					if(!data.result){
						swal("Error", "일치하는 회원정보가 없습니다.", "error")
					}else {
						location.href = projectURL+"/cloud/cloud.do";
					}
				},
				error : function () {	
					loadingStopAjax();		
					swal("Error", "로그인 에러", "error")
				}
			})
		}
	})
	
	$("#signup-formBtn").click(function(){
		location.href = projectURL+"/login/signupform.do";
	})