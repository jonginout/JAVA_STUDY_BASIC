<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.StudVO"%>
<%@page import="model.StudDAO"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	
	StudVO vo = new StudDAO().detail(request.getParameter("id"));

	String [] ho = vo.getHobby().substring(1,vo.getHobby().length()-1).split(",");
	
	ArrayList <String> hhh = new ArrayList();
	for(String str: ho)
	{
		hhh.add(str.trim());
	}
	
	
	String [] hoIndex = {"read_독서","fish_낚시","clim_등산"};
	String [] emailIndex = {"naver.com_네이버","daum.net_다음","google.com_구글","jum.com_줌"};
	
	System.out.println("fewfwef:"+vo.getEmail());
	String [] email = vo.getEmail().split("@");
	
	
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function gogo(i){
	
	
	if(confirm('수정하시겠습니까?')){
		
		frm.submit();
	}
}

</script>
</head>
<body>
<form name='frm' action="modifyReg.jsp" method="post">
<input name="id" type="hidden" value="<%=vo.getId()%>" readonly="readonly">
	<table border="1">
	<tr>
		<td>pw</td>
		<td><input name="pw" type="password" size="10" maxlength="5"></td>
	</tr>
	<tr>
		<td>성별</td>
		<td><input type="radio" name="gender" value="m" <% if(vo.getGender().equals("m")) out.print("checked='checked'"); %>   >남 
			<input type="radio" name="gender" value="f" <% if(vo.getGender().equals("f")) out.print("checked='checked'"); %> >여
		</td>
	</tr>
	<tr>
		<td>취미</td>
		<td>
		<% for(String hh: hoIndex){ 
			String [] hhArr = hh.split("_");
			String chk = "";
			
			if(hhh.contains(hhArr[0]))
				chk = "checked=\"checked\"";
		%>
		
		<input type="checkbox" name="hobby" value="<%=hhArr[0]%>" <%=chk %>><%=hhArr[1]%>
		<%} %> 
		</td>
	</tr>
	<tr>
		<td>file</td>
		<td><input name="upfile" type="file"></td>
	</tr>
	<tr>
		<td>이메일</td>
		<td>
		<input name="email1" type="text" value="<%=email[0]%>">@
		<select name="email2">
			<% for(String hh: emailIndex){ 
			String [] hhArr = hh.split("_");
			String chk = "";
			
			if(hhArr[0].equals(email[1]))
				chk = "selected=\"selected\"";
		%>
		
		<option value="<%=hhArr[0]%>" <%=chk %>><%=hhArr[1]%></option>
		<%} %> 
		</select>
		</td>
	</tr>
	<tr>
		<td>내용</td>
		<td><textarea name="content" rows="5" cols="50"><%=vo.getContent()%></textarea></td>
	</tr>
	
	<tr>
		<td colspan="2" align="right">
			<input type="button" value="수정" onclick="gogo()">
			<a href="detail.jsp?id=<%=vo.getId()%>">뒤로가기</a>
		</td>
	</tr>
</table>
</form>	
	
	

</body>
</html>
