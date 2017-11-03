<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/jsp/include/basicInclude.jsp"%>
</head>
<body>

	<div class="row justify-content-center">

		<div class="header">
			<c:import url="/jsp/include/header.jsp" />
		</div>

		<div class="content row">
			<div class="container">
					<p class="h1">로그인</p>
			
			<c:if test="${error!=null}">
				<div class="alert alert-danger col-md-6" role="alert">${ error }</div>
			</c:if>

			<form action="${pageContext.request.contextPath}/jsp/login/loginprocess" method="post">
			  <div class="form-group col-md-6">
			    <label for="exampleInputEmail1">ID</label>
			    <input type="text" name="id" value="${cid}" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter Id">
			  </div>
			  <div class="form-group col-md-6">
			    <label for="exampleInputPassword1">Password</label>
			    <input type="password" name="pass" class="form-control" id="exampleInputPassword1" placeholder="Password">
			  </div>
			  <div class="form-check col-md-6">
				<button type="submit" class="btn btn-primary">Submit</button>
			    <label class="form-check-label float-right">
			      <input type="checkbox" class="form-check-input" id="idcheck" 
			      <c:if test="${not empty cid}">checked</c:if> value="Y" name="idcheck">
			     	 아이디 저장
			    </label>
			  </div>
			</form>
				
			</div>
		</div>

		<div class="footer">
			<c:import url="/jsp/include/footer.jsp" />
		</div>
		
	</div>
	
	
	<script type="text/javascript">
		$(".nav.nav-pills.headMenu > a").removeClass("active"); //모든 li 클래스를 다 지우고
		$(".nav.nav-pills.headMenu > a:eq(1)").addClass("active"); // 추가
	</script>
</body>
</html>