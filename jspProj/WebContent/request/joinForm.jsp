<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
 
    
    function idchk() {
		var id = frm.id.value;
		alert(id);
		frm.id.value = '현구야 점심 뭐 먹어?'
	}
    function gogo(i){
        //alert('또 눌렀냐'+i);
        if(confirm('가입ㄱㄱ?')){
        	//alert(frm.id.value);
        	frm.submit();
		}
    }
    function imgChange(i){
    	alert(i);
    	img.src=i;
    }
</script>
 
 
</head>
<body>
<form name="frm" action="joinReg.jsp" method="post">
<h1>회원가입 하기</h1>
<table border="1">
    <tr>
        <td>이미지</td>
        <td>
			<img src="../img/diary/0.jpeg" height="300" name="img"/>
			<table>
				<tr>
				
					<td>
						<% for(int i =0; i<5;i++) {%>
							<img src="../img/diary/<%=i %>.jpeg" alt="" width="20" onclick="imgChange(this.src)"/>
						<%} %>
					</td>
					
				</tr>
			</table>
        
        </td>
    </tr>
    <tr>
        <td>id</td>
        <td><input name="id" type="text" size="20" maxlength="10" placeholder="id를 입력하시오">
            <input type="button" value="중복확인" onclick="idchk()">
        </td>
    </tr>
    <tr>
        <td>pw</td>
        <td><input name="pw" type="password" size="20" maxlength="10"></td>
    </tr>
    <tr>
        <td>이름</td>
        <td><input name="name" type="text" size="20" maxlength="5"></td>
    </tr>
    <tr>
        <td>주민번호</td>
        <td>
            <input type="text" name="jumin1" maxlength="6" size="10">-
            <input type="password" name="jumin2"" maxlength="7" size="10">
        </td>
    </tr>
    <tr>
        <td>성별</td>
        <td><input type="radio" name="gender" value="m">남 
            <input type="radio" name="gender" value="f" checked="checked">여
        </td>
    </tr>
    <tr>
        <td>학년</td>
        <td>
        <select name="grade" style="width:100px">
            <option value="1">1학년</option>
            <option value="2">2학년</option>
            <option value="3">3학년</option>
            <option value="4">4학년</option>
        </select>
        </td>
    </tr>
    <tr>
        <td>친구</td>
        <td><input name="friend" type="text" size="20" maxlength="5"></td>
    </tr>
    <tr>
        <td>취미</td>
        <td><input type="checkbox" name="hobby" value="read">독서 
            <input type="checkbox" name="hobby" value="fish"  checked="checked">낚시
            <input type="checkbox" name="hobby" value="clim"  checked="checked">등산
        </td>
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
        <td>내용</td>
        <td><textarea name="content" rows="5" cols="50"></textarea></td>
    </tr>
    
    
    <tr>
        <td colspan="2" align="center">
            <button onclick="history.back()">뒤로</button>
            <input type="button" onclick="gogo()" value="가입">
            <input type="reset" value="초기화">
        </td>    
    </tr>
</table>
</form>
 
    <a href="userList.jsp">회원 목록 보기</a>
 
</body>
</html>