<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/jsp/include/basicInclude.jsp" %>
</head>
<body>
	<h1>Jongin Blog 로그인</h1>
	
	<c:if test="${error!=null}">
		<h3 style="color:tomato;">※일치하는 회원정보가 없습니다.※</h3>
	</c:if>
	
	<form action="${pageContext.request.contextPath}/login/login.do"
	id="loginForm" name="loginForm" method="post" onsubmit="return login();">
		<p>
			id : <input type="text" name="id" value="${cid}"/>
		</p>
		<p>
			pw : <input type="password" name="pw"/>
		</p>
		<p>
			<label for="idcheck">
				ID 기억 : 
			</label>
			<input type="checkbox" value="Y" name="idcheck" id="idcheck"
			<c:if test="${not empty cid}">checked</c:if>/>
		</p>
		<p>
			<button id="submit">로그인</button>
			<button type="button" onclick="main()">
				<i class="fa fa-home" aria-hidden="true"></i>
				메인
			</button>
		</p>
	</form>
</body>
</html>

<script type="text/javascript">
	function main() {
		location.href = "${pageContext.request.contextPath}/main/main.do";
	}
	
	function login() {
		
		var pattern = /^([a-zA-Z]){2,10}$/;
		var id = loginForm.id.value;
		var pw = loginForm.pw.value;
		
		if(!pattern.test(id)){
			alert("ID는 영대소문자로 2~10자 입니다.");
			return false;
		}
		if(!pattern.test(pw)){
			alert("패스워드는 영대소문자로 2~10자 입니다.");
			return false;
		}
		return true;
	}
	
</script>