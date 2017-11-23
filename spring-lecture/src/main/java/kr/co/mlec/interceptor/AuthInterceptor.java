package kr.co.mlec.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.mlec.form.MemberVO;


//인터셉터를 사용하려면 무조건 HandlerInterceptorAdapter를 상속 받아야 한다.
public class AuthInterceptor extends HandlerInterceptorAdapter { 
	@Override
	public boolean preHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("user");
		if (member != null) {
			return true;
		}
		response.sendRedirect("loginForm.do");
		return false;
		
	}
}


// 어떤 클래스가 실행되기 전에 뭔가 체크하고 싶은 것.