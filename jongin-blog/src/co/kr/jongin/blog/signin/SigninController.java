package co.kr.jongin.blog.signin;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.mvc.Controller;
import org.springframework.web.mvc.RequestMapping;

import com.google.gson.JsonObject;

import co.kr.jongin.blog.login.LoginDomain;

@Controller
public class SigninController {

	@RequestMapping("/signin/signinform.do")
	public void name() {}
	
	@RequestMapping("/signin/signin.do")
	public String signin(LoginDomain login) throws Exception {
		SigninMapper signinDAO = new SigninMapper();
		signinDAO.signin(login);
		return "redirect:/main/main.do";
	}
	
	@RequestMapping("/signin/idchk.do")
	public void idchk(String id, HttpServletResponse response) throws Exception{

		SigninMapper signinDAO = new SigninMapper();
		JsonObject jsonObject = new JsonObject();				 
		PrintWriter out = null;
		response.setContentType("application/x-json; charset=UTF-8"); //HttpServletResponse response

		if (signinDAO.signinIdChk(id)<1) {		
			jsonObject.addProperty("success", "사용 가능한 아이디 입니다.");
			out = response.getWriter();
		}else {			 
			jsonObject.addProperty("error", "존재하는 아이디 입니다.");
			out = response.getWriter();
		}
		
		out.print(jsonObject);
		out.close();	
	}
	
}
