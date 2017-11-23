package db.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import db.member.service.MemberService;
import db.repository.vo.Member;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	public void retrieveMember() throws Exception {
		List<Member> list = memberService.retrieveMember();
		System.out.println("회원 정보");
		System.out.println("===================================");
		for (Member member : list) {
			System.out.println(member);
		}
	}
	
	public void retrieveMemberInfo(String id) throws Exception {
		Member memberVO = memberService.retrieveMemberInfo(id);
		System.out.println(id + " : 회원 정보 ");
		System.out.println(memberVO);
	}
	
	public void registMember() throws Exception {
		Member memberVO = new Member();
		memberVO.setId("test-123456");
		memberVO.setAddr("테스트시 테스트동 테스트 아파트");
		memberVO.setEmail("tester@a.com");
		memberVO.setName("tester");
		memberService.registMember(memberVO);
		System.out.println("성공적으로 등록되었습니다.");
	}

	public void deleteMember(String id) throws Exception {
		memberService.deleteMember(id);
		System.out.println("삭제되었습니다.");
	}
}








