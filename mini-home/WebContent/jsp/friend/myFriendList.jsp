<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
	.badge.badge-primary:hover { 
		cursor:pointer;
    	background-color: skyblue;
	}
	.badge.badge-danger:hover{
		cursor:pointer;
    	background-color: orange;
	}
	#friendList:hover { 
		cursor:pointer;
    	background-color: skyblue;
	}
	.dropdown-item:hover{
		cursor:pointer;
    	background-color: skyblue;
	}
</style>
<c:choose>
	<c:when test="${empty sessionScope.user}">
		<h6>로그인 해주세요.</h6>
	</c:when>
	<c:otherwise>
		<table class="table">
			<thead>
				<th>친구ID</th>
				<th>상태</th>
			</thead>
			<c:forEach var="fr" items="${friendList}">
				<tr>
				
					<td class="nav-item dropdown">
						<a class="nav-link" href="#" id="friendList"
						role="button" data-toggle="dropdown" > 
						${fr.friendMemberId}
						</a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" onclick="friendLink('${fr.friendMemberId}')">홈피 리스트</a>
							<a class="dropdown-item" data-toggle="modal" data-target="#exampleModal" onclick="friendReply();recIdChk('${fr.friendMemberId}')">쪽지 보내기</a>
						</div>
					</td>


					<td>
					${fr.status}
					<c:choose>
						<c:when test="${fr.status=='WAIT'}">
							<span onclick="block('${fr.memberId}','${fr.friendMemberId}')" class="badge badge-danger">취소</span>
						</c:when>
						<c:when test="${fr.status=='SELECT'}">
							<span onclick="active('${fr.memberId}','${fr.friendMemberId}')" class="badge badge-primary">수락</span>
							<span onclick="block('${fr.memberId}','${fr.friendMemberId}')" class="badge badge-danger">거절</span>
						</c:when>
						<c:when test="${fr.status=='ACTIVE'}">
							<span onclick="block('${fr.memberId}','${fr.friendMemberId}')" class="badge badge-danger">끊기</span>
						</c:when>
					</c:choose>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:otherwise>
</c:choose>


<script type="text/javascript">
function active(memberId, friendMemberId){
	if(confirm(friendMemberId+'님의 친구 신청을 수락하시겠습니까?')){		
		location.href = 
			"${pageContext.request.contextPath}/jsp/friend/friendactive?member_id="+memberId+"&friend_member_id="+friendMemberId;
	}
}
function block(memberId, friendMemberId){
	if(confirm(friendMemberId+'님과 친구가 끊어집니다.')){
		location.href = 
			"${pageContext.request.contextPath}/jsp/friend/frienddelete?member_id="+memberId+"&friend_member_id="+friendMemberId;		
	}
}
function friendReply() {
	var content = "${sessionScope.user.id}님 (친구)가 보낸 쪽지 입니다.\n=================\n";
	$("#message-text").val(content);
}
function friendLink(friendMemberId) {	
	location.href = "${pageContext.request.contextPath}/jsp/main/main?field=id&word="+friendMemberId;
}
</script>