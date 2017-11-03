<%@page import="model.StudVO"%>
<%@page import="model.StudDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    request.setCharacterEncoding("utf-8");
 
    StudDAO dao = new StudDAO();
    StudVO st = dao.studDetail(request.getParameter("id"));
    String [] jumin = st.getJumin().split("-");
    String [] email = st.getEmail().split("@");
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="form" action="joinUpdateReg.jsp" method="post">
 
<h1><%=st.getName()%>님의 회원정보 수정</h1>
 
<table border="1">
    <tr>
        <td>id</td>
        <td><input name="id" type="text" size="20" maxlength="10" value="<%=st.getId()%>">
            <input type="button" value="중복확인" onclick="gogo('abcd')">
        </td>
    </tr>
    <tr>
        <td>pw</td>
        <td><input name="pw" type="password" size="20" maxlength="10" value="<%=st.getPw()%>"></td>
    </tr>
    <tr>
        <td>이름</td>
        <td><input name="name" type="text" size="20" maxlength="5" value="<%=st.getName()%>"></td>
    </tr>
    <tr>
        <td>주민번호</td>
        <td>
            <input type="text" name="jumin1" maxlength="6" size="10" value="<%=jumin[0]%>">-
            <input type="password" name="jumin2"" maxlength="7" size="10" value="<%=jumin[1]%>">
        </td>
    </tr>
    <tr>
        <td>성별</td>
        <td>
            <input type="radio" name="gender" value="m" >남 
            <input type="radio" name="gender" value="f" >여
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
        <td><input name="friend" type="text" size="20" maxlength="5" value="<%=st.getFriend()%>"></td>
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
        <input name="email1" type="text" value="<%=email[0]%>">@
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
        <td><textarea name="content" rows="5" cols="50"><%=st.getContent()%></textarea></td>
    </tr>
    
    
    <tr>
        <td colspan="2" align="center">
            <button onclick="history.back()">뒤로</button>
            <input type="submit" value="회원정보 수정">
            <input type="reset" value="초기화">
        </td>    
    </tr>
</table>
</form>
</body>
</html>
 
<script>
 
    if("<%=st.getGender()%>"=="m"){
        form.gender[0].checked = true;
    }else {
        form.gender[1].checked = true;        
    }
 
    
    for(i=0; i < form.grade.options.length; i++){
        if(form.grade.options[i].value=="<%=st.getGrade()%>"){
            form.grade.options[i].selected = true;
            break;
        }
    }
    
    for(i=0; i < form.email2.options.length; i++){
        if(form.email2.options[i].value=="<%=email[1]%>"){
            form.email2.options[i].selected = true;
            break;
        }
    }
 
</script>