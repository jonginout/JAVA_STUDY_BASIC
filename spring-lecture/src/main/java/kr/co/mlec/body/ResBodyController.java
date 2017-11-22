package kr.co.mlec.body;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.mlec.form.MemberVO;

@Controller
@RequestMapping("/ajax")
public class ResBodyController {

	@RequestMapping("/resBody.do")
	@ResponseBody // return 내용이 객체 공유가 아니라 그냥 전송이 된다
	public String resStringBody() {
		return "OK, 성공";
		// getWrite(); 하는 것과 유사
		// 한글이 깨지기 때문에 spring-mvc.xml 수정
	}

	@RequestMapping("/resBody.json")
	@ResponseBody
	public Map<String, String> resJsonBody() {
		Map<String, String> result = new HashMap<>();
		result.put("id", "sbc");
		result.put("name", "hong");
		result.put("addr", "서울");
		return result;
	}
	
	@RequestMapping("/resVOBody.json")
	@ResponseBody
	public MemberVO resJsonVOBody() {
		MemberVO vo = new MemberVO();
		vo.setId("sbc");
		vo.setName("sbc");
		vo.setPassword("1234");
		return vo;
	}

	@RequestMapping("/resStringListBody.json")
	@ResponseBody
	public List<String> resJsonStringListBody() {
		List<String> list = new ArrayList<>();
		for (int i = 1; i < 4; i++) {
			list.add(String.valueOf(i));
		}
		return list;
	}
	
	@RequestMapping("/resVOListBody.json")
	@ResponseBody
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