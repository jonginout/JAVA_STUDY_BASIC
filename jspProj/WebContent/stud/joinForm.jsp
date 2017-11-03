<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

	function idchk()
	{
		var id = frm.id.value;
		alert(id);
		frm.id.value = '현구야 점심 뭐 먹어?';
	}

	function gogo(){
		
		
		if(confirm('가입하시겠습니까?')){
			//alert(frm.action);
			
			
			frm.submit();
		}
	}
	
	function imgChange(src)
	{
		//alert(son.src)
		son.src=src
	}
</script>


</head>
<body>
<form name='frm' action="joinReg.jsp" method="post">
<table border="1">
	<tr>
		<td onclick="imgChange('../img/diary/0.jpeg')">이미지</td>
		<td>
			<img name="son" alt="" src="../img/son.jpg" width="100">
			
			<table>
				<tr>
				
					<td>
						<% for(int i =0; i<5;i++) {%>
							<img src="../img/diary/<%=i %>.jpeg" 
							onclick="imgChange('../img/diary/<%=i %>.jpeg')"
							 alt="" width="20"/>
						<%} %>
					</td>
					
				</tr>
			</table>
			
		</td>
	</tr>


	<tr>
		<td>id</td>
		<td><input name="id" type="text" size="10" maxlength="5">
			<input type="button" value="중복확인" onclick="idchk()">
		</td>
	</tr>
	<tr>
		<td>pw</td>
		<td><input name="pw" type="password" size="10" maxlength="5"></td>
	</tr>
	<tr>
		<td>성별</td>
		<td><input type="radio" name="gender" value="m">남 
			<input type="radio" name="gender" value="f" checked="checked">여
		</td>
	</tr>
	<tr>
		<td>취미</td>
		<td><input type="checkbox" name="hobby" value="read">독서 
			<input type="checkbox" name="hobby" value="fish"  checked="checked">낚시
			<input type="checkbox" name="hobby" value="clim"  checked="checked">등산
		</td>
	</tr>
	<tr>
		<td>file</td>
		<td><input name="upfile" type="file"></td>
	</tr>
	<tr>
		<td>이메일</td>
		<td>
		<input name="email1" type="text">@
		<select name="email2">
			<option value="naver.com">네이버</option>
			<option value="google.com">구글</option>
			<option value="daum.net" selected="selected">다음</option>
			<option value="jum.com">줌</option>
		</select>
		</td>
	</tr>
	<tr>
		<td>file</td>
		<td><textarea name="content" rows="5" cols="50">ekwfe[fpekw[f]
kfe[kfewfk]k
f[kewfk[ewkf[ewfk[]</textarea></td>
	</tr>
	
	
	<tr>
		<td colspan="2" align="center">
			<input type="button" value="가입" onclick="gogo()">
			<input type="reset" value="초기화">
		</td>	
	</tr>
</table>
</form>
</body>
</html>
