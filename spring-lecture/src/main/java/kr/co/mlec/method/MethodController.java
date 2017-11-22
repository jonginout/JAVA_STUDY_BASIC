package kr.co.mlec.method;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MethodController {

	// url이 같을때는 get이냐 post냐로 구분이 가능하다
	@RequestMapping(value = "/method/method.do", method = RequestMethod.GET)
	public String callGet() {
		return "method/methodForm";
	}

	@RequestMapping(value = "/method/method.do", method = RequestMethod.POST)
	public String callPost() {
		return "method/methodProcess";
	}

}
