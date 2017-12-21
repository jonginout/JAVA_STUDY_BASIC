package com.cloud.login.service;

import com.cloud.repository.vo.Member;

public interface LoginService {

	public int checkId(String id) throws Exception;
	public void signup(Member member) throws Exception;
	public Member login(Member member) throws Exception;

	
}
