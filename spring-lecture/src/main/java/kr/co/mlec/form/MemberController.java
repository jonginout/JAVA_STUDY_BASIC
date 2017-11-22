package kr.co.mlec.form;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/form")
public class MemberController {

	@RequestMapping("/joinForm.do")
	public String joinForm() {
		return "form/joinForm";
	}

/*	@RequestMapping("/join.do")
	public String join(
			//MemberVO member 이클래스 네임이 길어질 수 있으니 이름을 정해준다
			@ModelAttribute("member") MemberVO member)
			 {
	 System.out.println(member.getId());
	 System.out.println(member.getPassword());
	 System.out.println(member.getName());
	 //객체를 공유를 한게 없는데? 객체가 넘어간다??
	 return "form/memberInfo";
	}*/
	
/*	
 	// 파라미터에 없는 객체를 넘기고 싶을때
 	@RequestMapping("/join.do")
	public ModelAndView join(MemberVO member) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("form/memberInfo");
		mav.addObject("member", member);
		return mav;
	}
	
	*/
	
/*	@RequestMapping("/join.do")
	public String join(MemberVO member, Model model) {
		System.out.println(member.getId());
		System.out.println(member.getPassword());
		System.out.println(member.getName());
		model.addAttribute("msg", "가입이 완료되었습니다.");
		//return "form/joinForm";
		// msg가 계속 뜬다
		 return "redirect:/form/joinForm.do";
		 // 재호출이라 msg가 아예 데이터가 뜨지 않는다.
		 // 리다이렉션은 데이타 공유가 아니니깐
		 
//		 해결책은  RedirectAttributes
	}*/
	
	
	@RequestMapping("/join.do")
	public String join(MemberVO member, RedirectAttributes attr) {
		System.out.println(member.getId());
		System.out.println(member.getPassword());
		System.out.println(member.getName());
		attr.addFlashAttribute("msg", "가입이 완료되었습니다.");
		// 리다이렉션시 일회성으로 데이터 전송
		return "redirect:/form/joinForm.do";
	}

	

}

// 두개가 합쳐지는 것 이다.

// @RequestMapping("/form")
// @RequestMapping("/joinForm.do")

// => /form/joinForm.do
