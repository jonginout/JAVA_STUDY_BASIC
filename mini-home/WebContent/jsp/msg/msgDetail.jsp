<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/jsp/include/basicInclude.jsp"%>
<style type="text/css">
	.col-lg-8{
		margin-top : 30px;
	}
	.h2{
		margin-top : 30px;
		text-align: left;
	}
	#content{
		font-size: 20px !important;
		height: 500px;
	}
</style>
</head>
<body>

	<div class="row justify-content-center">

		<div class="header">
			<c:import url="/jsp/include/header.jsp" />
		</div>

		<div class="content row">
			<div class="container">
				<center>
			<p class="h2"><c:out value="${msg.sendId}님이 보낸 쪽지" /></h1>
			<h6 class="text-right">받는이 : ${msg.recId} </h6>
			<table class="table table-hover" border="3">
				<tr>
					<% pageContext.setAttribute("newLineChar", "\n"); %>
					<td id="content">
						${fn:replace(msg.content, newLineChar, "<br/>")}
					</td>
				</tr>
			</table>
			<div class="text-right">작성시 : ${msg.sendDate}</div>
			
			<div class="text-right">
				<br /> <a href="${pageContext.request.contextPath}/board/list"></a>
				<button class="btn btn-primary" onclick="msgList()">쪽지함</button>
				<c:if test="${param.field=='rec_id'}">
					<button class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" onclick="reply();recIdChk('${msg.sendId}');">답장</button>
				</c:if>
				<button class="btn btn-danger" onclick="del();">삭제</button>
			</div>
			
			
			
			
				</center>
			</div>
		</div>

		<div class="footer">
			<c:import url="/jsp/include/footer.jsp" />
		</div>
		
	</div>
	
								
	<script type="text/javascript">
		var str = document.getElementById("textarea").value;
		str = str.replace(/(?:\r\n|\r|\n)/g, '<br />');
		document.getElementById("result").value = str;
		var str = $("#content").val();
	
	
		$(".nav.nav-pills.headMenu > a").removeClass("active"); //모든 li 클래스를 다 지우고
		$(".nav.nav-pills.headMenu > a:eq(3)").addClass("active"); // 추가
		function msgList() {
			location.href = "${pageContext.request.contextPath}/jsp/msg/msgbox?field=${param.field}";
		}
		function del() {
			if(confirm('정말로 쪽지를 삭제 하시겠습니까?')){
			location.href = "${pageContext.request.contextPath}/jsp/msg/msgdelete?msg_no=${msg.msgNo}&field=${param.field}";
			}
		}
		function reply() {
			var content = "<쪽지번호 : ${msgNo}>\n보낸이 : '${msg.sendId}'에 대한 답장\n\n : ${msg.sendDate}\n=================\n<답장>";
			$("#message-text").val(content);
		}
	</script>
</body>
</html>