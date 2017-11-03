package com.jongin.login;


import org.apache.ibatis.session.SqlSession;

import com.jongin.common.MyAppSqlConfig;

public class LoginMapper {
	
	private static SqlSession session;
	private final static String NS = "login.LoginMapper.";
	
	public LoginMapper() {
		session = MyAppSqlConfig.getSqlSessionInstance();
		if(session==null) {System.out.println("DB연결실패");}
	}
		
	// 로그인 처리
	public Login detailMember(Login param) {
		Login login = session.selectOne(NS + "getLoginInfo", param);
		return login;
		
	}
	
}
