<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document" style="margin-top: 100px">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">
        	<i class="fa fa-comments-o" aria-hidden="true"></i>
        	쪽지 보내기
        </h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form name="messageForm" id="messageForm" method="post" 
        action="${pageContext.request.contextPath}/jsp/msg/msgsend">
        
            <label for="recipient-name" class="col-form-label">받는이:</label>
       	 <div class="input-group">
            <input type="text" class="form-control" id="recipient-name" name="recId" >
            <span class="input-group-btn" id="recIdChkBtn" >
				<button class="btn btn-secondary" type="button" onclick="recIdChk(recId.value);">아이디 체크</button>
			</span>
            <span class="input-group-btn" id="recIdChCancel" >
				<button class="btn btn-success" type="button" onclick="recIdChCancel();">다른 사람에게 보내기</button>
			</span>
          </div>
          
          <div class="form-group">
            <label for="message-text" class="col-form-label">메시지:</label>
            <textarea class="form-control" id="message-text" name="content" rows="10"></textarea>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
        <button type="button" class="btn btn-primary" id="sendMsg" onclick="sendMsg()">메시지 보내기</button>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
// 	$("#message-text").val("");
// 	$("#recipient-name").val("");
// 	$("#recipient-name").removeAttr("readonly");

	
	$("#recIdChCancel").hide();
	$("#sendMsg").hide();
	function sendMsg() {		
		var recId = $("#recipient-name").val();
		var content = $("#message-text").val();
		if (recId.length<1) {
			alert("받는이를 입력하세요.");
			return;
		}
		if (content.length<3) {
			alert("내용을 3자 이상 입력하세요.");
			return;
		}
		if(confirm(recId+'님 에게 쪽지를 보냅니다.')){
			$("#messageForm").submit();
		}
	}
	function recIdChk(recId) {
// 		var recId = $("#recipient-name").val();
		var recId = recId;
		$("#recipient-name").val(recId);
		
	        $.ajax({
	             
	            type : "POST",
	            url : "${pageContext.request.contextPath}/jsp/msg/idchk",
	            data : {
	            	recId : recId,
	            	url : "${ pageContext.request.requestURL }"
	            	},
	            dataType : "json",
	            success : function(data){
	            	if (data.success) {
	            		alert(data.success);
		                $("#recipient-name").attr("readonly", "readonly");
		                $("#recIdChkBtn").hide(200);
		                $("#recIdChCancel").show(200);
		                $("#sendMsg").show(200);
					}else {
	            		alert(data.error);
					}
	            }
	             
	        });
	        
	}
	
	function recIdChCancel() {
		$("#recipient-name").removeAttr("readonly");
		$("#recIdChCancel").hide();
        $("#recIdChkBtn").show(200);
        $("#sendMsg").hide(200);

	}
</script>

<!-- create table msg( -->
<!--     msg_no number(30) primary key, -->
<!--     send_id varchar2(30) not null, -->
<!--     rec_id varchar2(30) not null, -->
<!--     content varchar2(2000) not null, -->
<!--     send_date date default sysdate, -->
<!--     is_read char(2) default 'F' -->
<!-- ); -->