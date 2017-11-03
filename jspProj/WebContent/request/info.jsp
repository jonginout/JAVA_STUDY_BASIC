<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<br><%=request.getRemoteAddr() %>
<br><%=request.getContentLength() %>
<br><%=request.getCharacterEncoding() %>
<br><%=request.getContentType() %>
<br><%=request.getProtocol() %>
<br><%=request.getRequestURI() %>
<br><%=request.getMethod() %>
<br><%=request.getContextPath() %>
<br><%=request.getServerName() %>
<br><%=request.getServerPort() %>
<br><%=request.getParameter("aaa") %>
<!-- 
request.getParameter("aaa")
					파라미터 변수명
이게 제일 중요 

무조건 스트링을 리턴한다.

한글은 2바이크 이상의 문자기 때문에
1바이트로 인코딩해서 넘겨야 한다

utf-8은 한글자 만드는데 3바이트를 쓴다.
%는 한글자당 3개씩
%~~% 가 1바이트 씩
 -->
</body>
</html>
