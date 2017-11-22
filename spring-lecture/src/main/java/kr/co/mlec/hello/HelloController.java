package kr.co.mlec.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


//컨트롤러 하는 역할인 클래스에 붙히는 어노테이션
@Controller
public class HelloController {

	@RequestMapping("/hello/hello.do")
	public ModelAndView hello() {
		ModelAndView mav = new ModelAndView("hello/hello");
		// 	<mvc:jsp prefix="/WEB-INF/jsp/" suffix=".jsp" />
		// 	이거에 의해서
		// 	/WEB-INF/jsp/hello/hello.jsp
		//  패키지 경로랑 매핑시키는게 좋다
		mav.addObject("msg", "hi 스프링 MVC~~");
		return mav;
	}

}
