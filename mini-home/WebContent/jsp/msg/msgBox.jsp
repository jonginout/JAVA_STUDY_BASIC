<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="navi" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/jsp/include/basicInclude.jsp"%>
<style type="text/css">
	#msgLi:hover { 
		cursor:pointer;
    	background-color: skyblue;
	}
	#boxNamd{
		color: #007bff;
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
					<c:if test="${param.field=='rec_id'}">
						<c:set var="boxName" value="받은 쪽지함"/>
					</c:if>
					<c:if test="${param.field=='send_id'}">
						<c:set var="boxName" value="보낸 쪽지함"/>					
					</c:if>
					<p class="h1">
						${user.id}님의 <span id="boxNamd">${boxName}(${count})</span>
					</p>
					
					<div class="form-group col-md-4 float-right">
						<c:if test="${param.field=='rec_id'}">
							<button class="btn btn-warning btn-lg btn-block" type="button"
								onclick="sendBox()" id="sendBox">
								 <i class="fa fa-comments-o" aria-hidden="true"></i>
								 보낸 쪽지함 보기
							</button>
							<c:set var="color" value="warning"/>
						</c:if>
						<c:if test="${param.field=='send_id'}">
							<button class="btn btn-success btn-lg btn-block" type="button"
								onclick="recBox()" id="recBox">
								 <i class="fa fa-comments-o" aria-hidden="true"></i>
								 받은 쪽지함 보기
							</button>
							<c:set var="color" value="success"/>
						</c:if>
					</div>
					
						<table class="table">
							<thead  class="table-${color}">
								<c:if test="${param.field=='rec_id'}">
									<th>보낸 사람</th>
								</c:if>
								<c:if test="${param.field=='send_id'}">
									<th>받는 사람</th>
								</c:if>
								<th>내용</th>
								<th>보낸 시간</th>
								<th>읽음 여부</th>
							</thead>
							<c:forEach var="msg" items="${msgList}" >
								<tr id="msgLi" onclick="msgDetail(${msg.msgNo})">
								<c:if test="${param.field=='rec_id'}">
									<td>${msg.sendId}</td>
								</c:if>
								<c:if test="${param.field=='send_id'}">
									<td>${msg.recId}</td>
								</c:if>
									<td style="width: 200px !important" class="d-inline-block text-truncate">${msg.content}</td>
									<td>${msg.sendDate}</td>
									<td>
										<c:choose>
											<c:when test="${msg.isRead}">
												읽음
											</c:when>
											<c:otherwise>
												읽지 않음
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
						</table>
						
				<navi:msgPage data="${MsgpageResult}" url="msgbox"/>
						
				</center>
			</div>
		</div>
		<div class="footer">
			<c:import url="/jsp/include/footer.jsp" />
		</div>
		
	</div>
	
	
	<script type="text/javascript">
		$(".nav.nav-pills.headMenu > a").removeClass("active"); //모든 li 클래스를 다 지우고
		$(".nav.nav-pills.headMenu > a:eq(3)").addClass("active"); // 추가
		
		function recBox() {			
			location.href="${pageContext.request.contextPath}/jsp/msg/msgbox?field=rec_id";
		}
		function sendBox() {			
			location.href="${pageContext.request.contextPath}/jsp/msg/msgbox?field=send_id";
		}
		function msgDetail(msgNo) {
			location.href="${pageContext.request.contextPath}/jsp/msg/msgdetail?field=${param.field}&msg_no="+msgNo;			
		}
	</script>
</body>
</html>





