package com.cloud.repository.mapper;

import com.cloud.repository.vo.Member;

public interface MemberMapper {
	
	public int idChk(String id) throws Exception;
	public void memberInsert(Member member) throws Exception;
	public Member memberLogin(Member member) throws Exception;

}
