package com.cloud.login.service;

import com.cloud.repository.vo.AuthSms;
import com.cloud.repository.vo.AutoLogin;
import com.cloud.repository.vo.Member;

public interface LoginService {

	public int checkId(String id) throws Exception;
	public void signup(Member member) throws Exception;
	public Member login(Member member) throws Exception;
	public void saveKey(AuthSms authSms) throws Exception;
	public int checkKey(AuthSms authSms) throws Exception;
	public void removeKey(AuthSms authSms) throws Exception;
	
	public void autoLogin(AutoLogin autoLogin) throws Exception;
	public AutoLogin checkAutoLogin(String sessionId) throws Exception;
	public Member autoLoginToMember(int memberNo) throws Exception;
	public void autoLoginEdit(int memberNo) throws Exception;
}
