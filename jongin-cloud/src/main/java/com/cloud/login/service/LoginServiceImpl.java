package com.cloud.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.repository.mapper.MemberMapper;
import com.cloud.repository.vo.Member;


@Service("TreeService")
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private MemberMapper mapper;
	
	@Override
	public int checkId(String id) throws Exception {
		return mapper.idChk(id);
	}

	@Override
	public void signup(Member member) throws Exception {
		mapper.memberInsert(member);
	}

	@Override
	public Member login(Member member) throws Exception {
		return mapper.memberLogin(member);
	}
	

}
