<%@page import="model.FileCnt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">

function imgChange(i){
	alert(i);
	img.src=i;
}
</script>

<%
	
	FileCnt fc = new FileCnt();
	out.print(fc.pullFile("../WebContent/include/main/pro/phone/"));
	

	String pro = "1";
	if(request.getParameter("pro")!=null)
	{
		pro = request.getParameter("pro");
	}
	
	String mainUrl = "phone-text/"+pro+".jsp";
%>


<img src="main/pro/phone/<%=pro %>.jpg" height="300" name="img"/>
<jsp:include page="<%=mainUrl %>"/>
<br>


<ul>
	<% for(int i =1; i<5;i++) {%>	
	<li>
		<img src="main/pro/phone/<%=i %>.jpg" alt="" width="20"/>
		<a href="?menu=pro&main=phone&pro=<%=i %>">갤럭시<%=i %></a>
	</li>
	<%} %>
</ul>


