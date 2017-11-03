<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	ArrayList<String>gallList = new ArrayList();

	gallList.add("son.jpg");
	gallList.add("son2.jpg");
	gallList.add("diary/0.jpeg");
	gallList.add("diary/1.jpeg");
	
	request.setAttribute("gallList", gallList);
	

	String menu = "info";

	HttpServletRequest req = (HttpServletRequest)pageContext.getRequest();
	//http프로토콜과 연관된 서블릿 요청

	if(req.getParameter("menu")!=null)
	{
		menu = req.getParameter("menu");
	}
	/* if(request.getParameter("menu")!=null)
	{
		menu = request.getParameter("menu");
	} */

	String menuUrl = "menu/"+menu+".jsp";
	
	String main = "index";

	if(request.getParameter("main")!=null)
	{
		main = request.getParameter("main");
	}

	String mainUrl = "main/"+menu+"/"+main+".jsp";
	
	
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
	<tr>
		<td colspan="2">
			<jsp:include page="inc/top.jsp"/>
		</td>
	</tr>
	<tr>
		<td width="100">
			<%-- <jsp:include page="<%=menuUrl %>"/> --%>
			<%
				pageContext.include(menuUrl);
			%>
		</td>
		<td width="500">
			<jsp:include page="<%=mainUrl %>"/>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<jsp:include page="inc/bottom.jsp"/>
		</td>
	</tr>
	
</table>
</body>
</html>