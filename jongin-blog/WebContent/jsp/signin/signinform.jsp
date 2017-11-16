<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/jsp/include/basicInclude.jsp" %>
<style>
	#msg1{color:green}
	#msg2{color:blue}
	#msg3{color:red}
</style>
</head>
<body>
	<h1>Jongin Blog 회원가입</h1>
	<form action="${pageContext.request.contextPath}/signin/signin.do"
	id="signinForm" name="signinForm" method="post" onsubmit="return signin();">
		<p>
			id 입력 : <input type="text" name="id" id="id"/>
			<br />
			<span id="msg"></span>
		</p>
		<p>
			pw 입력: <input type="password" name="pw"/>
		</p>
		<p>
			pw 확인 : <input type="password" name="pw2"/>
		</p>
		<p>
			<button id="submit">회원가입</button>
			<button type="button" onclick="main()">
				<i class="fa fa-home" aria-hidden="true"></i>
				메인
			</button>
		</p>
	</form>
</body>
</html>

<script type="text/javascript">

	var pattern = /^([a-zA-Z]){3,10}$/;
	function signin() {
		
		var pw = signinForm.pw.value;
		var pw2 = signinForm.pw2.value;
		
		if(!pattern.test(pw)){
			alert("패스워드는 영대소문자로 3~10자 입니다.");
			return false;
		}
		if(pw!=pw2){
			alert("패스워드가 일치하지 않습니다.");
			return false;
		}
		return true;
	}
	
	function main() {
		location.href = "${pageContext.request.contextPath}/main/main.do";
	}
	

	
	
	$("#id").on("keyup", function(){
		var recId = signinForm.id.value;
		
		if(pattern.test(recId)){
	        $.ajax({
	            type : "POST",
	            url : "${pageContext.request.contextPath}/signin/idchk.json",
	            data : {
	            	id : recId
	            	},
	            dataType : "json",
	            success : function(data){
	            	if (data.result==1) {
	//             		alert(data.msg);
	// 	                $("#id").attr("readonly", "readonly");
		                $("#submit").show(200);
		                $("#msg").html("<span id='msg1'>사용가능한 아이디 입니다.<span>");
					}else {
		                $("#msg").html("<span id='msg2'>이미 존재하는 아이디 입니다.<span>");
					}
	            }
	        });
		}else {
            $("#msg").html("<span id='msg3'>아이디는 영대소문자로 3~10자 입니다.<span>");
		}
	})
	
</script>