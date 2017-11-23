package kr.co.mlec.interceptor;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.mlec.form.MemberVO;

@Controller
@RequestMapping("/interceptor")
public class MyPage {

	@RequestMapping("/myPage.do")
	public void info(HttpSession session, Model model) {
		model.addAttribute("msg", "반갑습니다");
	}

}

// 마이페이지는 로그인 해야지만 호출이 가능한데
// 그냥 들어올 수 있는 것을 막아야 한다.


// 인터셉터를 어느 시점에 실행 할지 spring-mvc 에서 정해준다.