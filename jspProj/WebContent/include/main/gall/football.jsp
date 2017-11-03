<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script type="text/javascript">

function imgChange(i){
	alert(i);
	img.src=i;
}
</script>
<img src="main/gall/football/1.jpg" height="300" name="img"/>
<br>
<% for(int i =1; i<6;i++) {%>
	<img src="main/gall/football/<%=i %>.jpg" alt="" width="20" onclick="imgChange(this.src)"/>
<%} %>