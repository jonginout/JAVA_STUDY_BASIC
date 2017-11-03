package common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class AlertBack {
	
	//선언을 안해도 되니깐 static으로

	public static void alertAndBack(HttpServletResponse response, String msg) throws IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		StringBuffer html = new StringBuffer();
		html.append("<script>");
		html.append("	alert('"+msg+"');");
		html.append("	history.back();");
		html.append("</script>");
		out.write(html.toString());
		out.close();
		return;
	
	}
	
	
	
}
