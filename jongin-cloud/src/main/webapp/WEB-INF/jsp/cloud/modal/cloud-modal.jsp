<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- Modal -->

<style>
	.modal-footer{
		padding : 10px 15px 10px 15px !important;
	}
	#img-to-text-hidden{
		display: none;
	}
	#imgToText{
		width : 100%;
	}
	#imgTextCopy{
		height : 300px;
	}
</style>

<div class="modal modal-middle fade scale-out" id="file-modal" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body file-view-content">
				<object data="" type="application/pdf" width="100%" height="600px"></object>
			</div>
			<div class="modal-footer">
				<span class="pull-left" id="file-down-link">
					<a  href=""></a>
				</span>
				<br><br>
				<span id="textConvertBtn"></span>
				<button type="button" id="img-to-text-hidden" data-toggle="modal" data-target="#imgText"></button>			
				<button class="btn btn-default file-view-close" type="button" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<div class="modal modal-middle fade scale-out" id="imgText" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body text-view-content">
				<textarea id="imgTextCopy" class="form-control"></textarea>
			</div>
			<div class="modal-footer">	
				<button class="btn btn-default clipboard clipboard-btn" type="button" data-clipboard-target="#imgTextCopy">
					<i class="fa fa-clone" aria-hidden="true"></i> 
					복사
				</button>		
				<button class="btn btn-default" type="button" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<!-- 결제 Modal -->
<div class="modal modal-middle fade scale-out" id="addVolume" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document" style="width: 94% !important">
		<div class="modal-content">
			<div class="modal-header">
				<h3 style="padding:0px;margin:0px;"><strong><i class="fa fa-hdd-o" aria-hidden="true"></i> 드라이브 용량 추가</strong></h3>
			</div>
			<div class="modal-body pay-content">
				
					<form class="form-horizontal pay-info" onsubmit="return false;">
						<div class="form-group">
							<label class="col-sm-2 control-label">추가용량</label>				
							<div class="col-sm-10">
								<select class="form-control" name="volume">
									<optgroup label="용량선택">
										<option value="50">+50MB</option>
										<option value="100">+100MB</option>
										<option value="300">+300MB</option>
										<option value="800">+800MB</option>
										<option value="1000">+1GB</option>
									</optgroup>
								</select>
							</div>					
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">전화번호</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="tel" id="payTel" placeholder="전화번호 - 제외" >
							</div>
						</div>
						<br />
						<div class="form-group">
							<div class="pull-right">
								<img src="${pageContext.request.contextPath}/img/kpay-btn.png" alt="카카오페이로 결제" id="kPay-btn">
							</div>
						</div>
					</form>


			</div>
			<div class="modal-footer">
				<button class="btn btn-default pay-close" type="button" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script>

$(function(){
	$(document).ready(function(){
		var clipboard = new Clipboard('.clipboard');
	});
})

$("body").on("click", ".img-to-text-btn", function () {

	var path = (nowNode.path).replace(/\\/gi, "/");

	$.ajax({
		type : "post",
		url : "${pageContext.request.contextPath}/cloud/detecttext.json",
		data : {
			filePath : path
		},
		beforeSend: loadingAjax("이미지 분석중... ( 최대 1분 )"),
		success : function (data) {
			loadingStopAjax();

			var result = JSON.parse(data);
			console.log(result)
			
			if(result){
				
				var content = result.responses[0].fullTextAnnotation.text;
				console.log(content)

				var width = $(".file-view-content>img").css("width");
				$("#imgTextCopy").css("width",width).val(content)
				$("#img-to-text-hidden").trigger("click")
			}
		},
		error : function () {
			alert("서버에러 개발자에게 문의하세요!")		
		}						
	})
})

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

	
	if($("#payTel").val().length<1){
		alert("결제정보를 옳바르게 입력하시오.");
		return;
	}

	$(".pay-close").trigger("click");

		IMP.init('imp32572105'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
		IMP.request_pay({
		pg : 'kakao', // version 1.1.0부터 지원.
		pay_method : 'card',
		merchant_uid : 'merchant_' + new Date().getTime(),
		name : '종인 클라우드 ['+$("select[name=volume]").val()+'MB] 용량추가',
		amount : 1000,
		buyer_email : '${user.email}',
		buyer_name : '${user.name}',
		buyer_tel : $("#payTel").val(),
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

</script>