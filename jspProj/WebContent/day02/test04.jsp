<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 
		 action : 
		 호출할 서버페이지의 주소
		 액션이 없다면 자기 자신을 부른것
		 /를 안하고 경로를 쓰면 맨 끝에만 바꾼다.
		 /를 하면 프로젝트 경로부터 싹다 바뀐다.
		 ${pageContext.request.contextPath} EL표현식 => 프로젝트명
		 
		 서버로 데이터를 전송하기 위해서 사용하는 태그
		 input, textarea, select
		 그렇기 위해서는 꼭 name 속성이 있어야 한다.
	 -->
	<form action="${pageContext.request.contextPath}/day02/test04" method="get">
		<p>
			<input type="checkbox" name="fruit" value="1" id="f1"/> <label for="f1">사과</label>
			<input type="checkbox" name="fruit" value="2" id="f2"/> <label for="f2">포도</label>
			<input type="checkbox" name="fruit" value="3" id="f3"/> <label for="f3">딸기</label>
		</p>
		<!-- 버튼에 타입이 없으면 submit -->
		<p>
			<button>전송</button>
<!--
 			<button type="button">버튼</button>
			<button type="reset">리셋</button>
-->
 		</p>
	</form>
	
</body>
</html>