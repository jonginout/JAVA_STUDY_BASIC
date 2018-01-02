package com.cloud.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.repository.mapper.MemberMapper;
import com.cloud.repository.vo.AuthSms;
import com.cloud.repository.vo.AutoLogin;
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

	@Override
	public void saveKey(AuthSms authSms) throws Exception {
		mapper.keyInsert(authSms);
	}

	@Override
	public int checkKey(AuthSms authSms) throws Exception {
		return mapper.keyChk(authSms);
	}

	@Override
	public void removeKey(AuthSms authSms) throws Exception {
		mapper.keyDelete(authSms);
	}

	
	
	@Override
	public void autoLogin(AutoLogin autoLogin) throws Exception {
		mapper.autoLoginInsert(autoLogin);
	}

	@Override
	public AutoLogin checkAutoLogin(String sessionId) throws Exception {
		return mapper.autoLoginChk(sessionId);
	}

	@Override
	public Member autoLoginToMember(int memberNo) throws Exception {
		return mapper.autoLoginToMember(memberNo);
	}

	@Override
	public void autoLoginEdit(int memberNo) throws Exception {
		mapper.autoLoginUpdate(memberNo);
	}
	

}
