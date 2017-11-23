package db.member.service;

import java.util.List;

import db.repository.vo.Member;

public interface MemberService {
	public List<Member> retrieveMember() throws Exception;
	
	public Member retrieveMemberInfo(String id) throws Exception;
	
	public void registMember(Member memberVO) throws Exception;

	public void deleteMember(String id) throws Exception;
}
 