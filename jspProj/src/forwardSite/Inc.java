package forwardSite;

import javax.servlet.http.HttpServletRequest;

public class Inc {

	public Inc(HttpServletRequest request) {
	// TODO Auto-generated constructor stub
		String menu = "info";

		 if(request.getParameter("menu")!=null)
		{
			menu = request.getParameter("menu");
		} 

		request.setAttribute("menuUrl","menu/"+menu+".jsp");
		
		String main = "index";

		if(request.getParameter("main")!=null)
		{
			main = request.getParameter("main");
		}

		request.setAttribute("mainUrl","main/"+menu+"/"+main+".jsp");
	}
}
