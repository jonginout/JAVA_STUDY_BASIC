<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*" contentType="text/html;charset=euc-kr"%>
<%

String driverN = "oracle.jdbc.driver.OracleDriver";               // 데이터베이스에 접속하기위한 드라이버를 로드합니다.

	String url = "jdbc:oracle:thin:@localhost:1521:xe";                 // 접속 URL정보 : 포트번호(oracle 포트), SID(oracle 접속)

	Class.forName(driverN);                                   

	Connection conn = DriverManager.getConnection( url,"hr","hr");  // 계정정보url, ID(oracle접속),PW(oracle접속)

	System.out.print("tjdthddQ!!");
	
conn.close();                                                              // 접속종료

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
</body>
</html>


sql = "select * from exam wher sid = 10";
stmt = con.prepareStatement(sql);
rs = stmt.executeQuery();
System.out.print(rs);