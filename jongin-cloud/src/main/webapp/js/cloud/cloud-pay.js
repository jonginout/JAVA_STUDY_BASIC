
$("body").on("keyup", ".pay-info input[name=tel]", function(){
	var telPattern = /^([0-9]){9,12}$/;
	var tel = $(this).val();
	if(!telPattern.test(tel)){
		$(this).removeClass("form-success").addClass("form-err");
	}else{
		console.log("??")
		$(this).removeClass("form-err").addClass("form-success");			
	}
})

$("body").on("click", "#kPay-btn", function(){

	
	if(!$(".pay-info input[name=tel]").val().length<1){
		alert("결제정보를 옳바르게 입력하시오.");
		return;
	}

	$(".pay-close").trigger("click");

		IMP.init('imp32572105'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
		IMP.request_pay({
		pg : 'kakao', // version 1.1.0부터 지원.
		pay_method : 'card',
		merchant_uid : 'merchant_' + new Date().getTime(),
		name : '종인 클라우드 ['+fd.volume+'MB] 용량추가',
		amount : 1000,
		buyer_email : fd.email,
		buyer_name : fd.name,
		buyer_tel : fd.tel,
		m_redirect_url : '/'
	}, function(rsp) {
		if ( rsp.success ) {
			var msg = '결제가 완료되었습니다.';
			msg += '고유ID : ' + rsp.imp_uid;
			msg += '상점 거래ID : ' + rsp.merchant_uid;
			msg += '결제 금액 : ' + rsp.paid_amount;
			msg += '카드 승인번호 : ' + rsp.apply_num;
		} else {
			var msg = '결제에 실패하였습니다.';
			msg += '에러내용 : ' + rsp.error_msg;
		}
		$(".tf_kakaopay").val(fd.tel);
		alert(msg);
	});
})