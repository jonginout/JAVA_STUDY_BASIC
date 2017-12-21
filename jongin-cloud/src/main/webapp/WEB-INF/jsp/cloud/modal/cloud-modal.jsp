<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- Modal -->
<div class="modal modal-middle fade scale-out" id="file-modal" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body file-view-content">
				<object data="" type="application/pdf" width="100%" height="600px"></object>
			</div>
			<div class="modal-footer">
				<div class="pull-left" id="file-down-link">
					<a style="font-size:22px" href=""></a>
				</div>
				<button class="btn btn-default file-view-close" type="button" data-dismiss="modal">Close</button>
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
				
					<form class="form-horizontal pay-info">
						<div class="form-group">
							<label class="col-sm-2 control-label">추가용량</label>				
							<div class="col-sm-10">
								<select class="form-control" name="volume">
									<optgroup label="용량선택">
										<option value="100">+100MB</option>
										<option value="300">+300MB</option>
										<option value="500">+500MB</option>
										<option value="800">+800MB</option>
										<option value="1000">+1GB</option>
									</optgroup>
								</select>
							</div>					
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">전화번호</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="tel" placeholder="전화번호 - 제외" >
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