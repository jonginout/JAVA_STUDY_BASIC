package co.kr.jongin.blog.signin;


import java.util.HashMap;
import java.util.Map;

import org.springframework.web.mvc.Controller;
import org.springframework.web.mvc.ModelAndView;
import org.springframework.web.mvc.RequestMapping;

import com.google.gson.Gson;

import co.kr.jongin.blog.login.LoginDomain;

@Controller
public class SigninController {

	@RequestMapping("/signin/signinform.do")
	public void signinform() {}
	
	@RequestMapping("/signin/signin.do")
	public String signin(LoginDomain login) throws Exception {
		SigninMapper signinDAO = new SigninMapper();
		signinDAO.signin(login);
		return "redirect:/main/main.do";
	}
	
	@RequestMapping("/signin/idchk.do")
	public String idchk(String id) throws Exception{
		
		Map<String, Object> obj = new HashMap<>();
		ModelAndView mav= new ModelAndView();
		SigninMapper signinDAO = new SigninMapper();		 
		
		if (signinDAO.signinIdChk(id)<1) {
			
			obj.put("result", 1);
			obj.put("msg", "사용 가능한 아이디 입니다.");

		}else {	
			
			obj.put("result", 2);
			obj.put("msg", "이미 존재하는 아이디 입니다.");
		}
		
		return "jsonView:"+new Gson().toJson(obj);	
	}
	
}
