package db.repository.mapper;

import java.util.List;

import db.repository.vo.Member;

// 매퍼는 인터페이스는 객체생성이 안되니깐 빈생성이 안됨 그래서 해결방법이
// 그래서 어노테이션이 있을 수 없다.
// 스프링 설정에 설정해줘야 한다.
public interface MemberMapper {

	public List<Member> selectMember() throws Exception;
	
	public Member selectMemberById(String id) throws Exception;
	
	public void insertMember(Member member) throws Exception;
	
	public void updateMember(Member member) throws Exception;
	
	public void deleteMember(String id) throws Exception;
}
