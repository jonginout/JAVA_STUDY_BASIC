package co.kr.jongin.blog.main;

import org.springframework.web.mvc.Controller;
import org.springframework.web.mvc.ModelAndView;
import org.springframework.web.mvc.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/main/main.do")
	public ModelAndView main() {
		return new ModelAndView("/jsp/main/main.jsp");
	}
	
}
