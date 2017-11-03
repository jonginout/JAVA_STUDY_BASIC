package com.jongin.main;

import org.springframework.web.mvc.Controller;
import org.springframework.web.mvc.ModelAndView;
import org.springframework.web.mvc.RequestMapping;

@Controller
public class MainController{
	
	
	@RequestMapping(value="/main/main.do")
	public ModelAndView main() throws Exception {
		
		return new ModelAndView("/main/main.jsp");
	}
	
}









