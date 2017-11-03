<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">

function imgChange(i){
	alert(i);
	img.src=i;
}
</script>

<%


	String pro = "1";
	if(request.getParameter("pro")!=null)
	{
		pro = request.getParameter("pro");
	}
	
	String mainUrl = "laptop-text/"+pro+".jsp";
%>


<img src="main/pro/laptop/<%=pro %>.jpg" height="300" name="img"/>
<jsp:include page="<%=mainUrl %>"/>
<br>


<ul>
	<% for(int i =1; i<6;i++) {%>	
	<li>
		<img src="main/pro/laptop/<%=i %>.jpg" alt="" width="20"/>
		<a href="?menu=pro&main=laptop&pro=<%=i %>">노트북<%=i %></a>
	</li>
	<%} %>
</ul>


