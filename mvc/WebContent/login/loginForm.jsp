<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
	<div>
		<c:import url="/include/topMenu.jsp" />
	</div>
	
	<div>
		<h2>${error}</h2>
		<form 	method="post"
				action="${pageContext.request.contextPath}/login/loginprocess.do">
			<input type="text" name="id" value="${cid}" />
			<input type="text" name="pass" />
			<button>로그인</button>
			<div>
				<input type="checkbox" name="idcheck" 
				       <c:if test="${not empty cid}">checked</c:if> value="Y" />
				아이디 저장
			</div>
		</form>
	</div>
	<div>
		<c:import url="/include/footer.jsp" />
	</div>
</div>
</body>
</html>















