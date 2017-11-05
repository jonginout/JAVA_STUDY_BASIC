package co.kr.jongin.blog.login;

import org.apache.ibatis.session.SqlSession;

import co.kr.jongin.blog.common.MyAppSqlConfig;

public class LoginMapper {

	private final static String NS = "co.kr.jongin.blog.login.LoginMapper.";
	
	private static SqlSession session;
	
	public LoginMapper () {
		session = MyAppSqlConfig.getSqlSessionInstance();
	}
	
	public LoginDomain login(LoginDomain login) throws Exception {
		return session.selectOne(NS + "login", login);
	}
	
}
