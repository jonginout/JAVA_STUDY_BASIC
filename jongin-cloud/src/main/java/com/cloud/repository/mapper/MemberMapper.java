package com.cloud.repository.mapper;

import com.cloud.repository.vo.AuthSms;
import com.cloud.repository.vo.Member;

public interface MemberMapper {
	
	public int idChk(String id) throws Exception;
	public void memberInsert(Member member) throws Exception;
	public Member memberLogin(Member member) throws Exception;
	public void keyInsert(AuthSms authKey) throws Exception;
	public int keyChk(AuthSms authSms) throws Exception;
	public void keyDelete(AuthSms authKey) throws Exception;

}
