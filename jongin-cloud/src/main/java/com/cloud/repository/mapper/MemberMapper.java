package com.cloud.repository.mapper;

import com.cloud.repository.vo.AuthSms;
import com.cloud.repository.vo.AutoLogin;
import com.cloud.repository.vo.Member;

public interface MemberMapper {
	
	public int idChk(String id) throws Exception;
	public void memberInsert(Member member) throws Exception;
	public Member memberLogin(Member member) throws Exception;
	public void keyInsert(AuthSms authKey) throws Exception;
	public int keyChk(AuthSms authSms) throws Exception;
	public void keyDelete(AuthSms authKey) throws Exception;

	public void autoLoginInsert(AutoLogin autoLogin) throws Exception;
	public AutoLogin autoLoginChk(String sessionId) throws Exception;
	public Member autoLoginToMember(int memberNo) throws Exception;
	public void autoLoginUpdate(int memberNo) throws Exception;

	public Member pwChk(Member member) throws Exception;
	public void lockUpdate(Member member) throws Exception;
}
