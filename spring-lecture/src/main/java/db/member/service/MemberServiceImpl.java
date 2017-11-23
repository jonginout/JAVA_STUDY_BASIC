package db.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import db.repository.mapper.MemberMapper;
import db.repository.vo.Member;

//앞글자 소문자 
@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper mapper;

	public List<Member> retrieveMember() throws Exception {
		return mapper.selectMember();
	}
	
	public Member retrieveMemberInfo(String id) throws Exception {
		return mapper.selectMemberById(id);
	}
	
	// 트랜잭션 대상 메소드
	@Transactional(rollbackFor=Exception.class)
	public void registMember(Member memberVO) throws Exception {
		mapper.insertMember(memberVO); // 얘는 들어가고
		mapper.updateMember(memberVO); // 얘는 에러
		
		// 이렇게 하는게 아니라 트랜잭션처리를 해줘서
		// update가 에러나면 insert도 안되게 한다.
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void deleteMember(String id) throws Exception {
		mapper.deleteMember(id);
	}
	
}
