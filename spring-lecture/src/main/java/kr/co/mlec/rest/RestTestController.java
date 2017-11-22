package kr.co.mlec.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.mlec.form.MemberVO;

/*
	@RestController는 rest 방식의 메시지 기반의 서비스를 만들경우 사용
	스프링 4 부터 지원함
	예> AJAX 서비스를 제공하는 컨트롤러
*/

@RestController //@ResponseBody를 일일히 붙이는 게 없다 한번에
@RequestMapping("/rest")
public class RestTestController {
	
	@RequestMapping("/resBody.do")
	public String resStringBody() {
		return "OK, 성공";
	}
	

	@RequestMapping("/resBody.json")
	public Map<String, String> resJsonBody() {
		Map<String, String> result = new HashMap<>();
		result.put("id", "sbc");
		result.put("name", "hong");
		result.put("addr", "서울");
		return result;
	}
	
	@RequestMapping("/resVOBody.json")
	public MemberVO resJsonVOBody() {
		MemberVO vo = new MemberVO();
		vo.setId("sbc");
		vo.setName("sbc");
		vo.setPassword("1234");
		return vo;
	}

	@RequestMapping("/resStringListBody.json")
	public List<String> resJsonStringListBody() {
		List<String> list = new ArrayList<>();
		for (int i = 1; i < 4; i++) {
			list.add(String.valueOf(i));
		}
		return list;
	}
	
	@RequestMapping("/resVOListBody.json")
	public List<MemberVO> resJsonVOListBody() {
		List<MemberVO> list = new ArrayList<>();
		for (int i = 1; i < 4; i++) {
			MemberVO vo = new MemberVO();
			vo.setId("sbc");
			vo.setName("sbc");
			vo.setPassword("1234");
			list.add(vo);
		}
		return list;
	}
}

// 한번에다 지시하겠다.