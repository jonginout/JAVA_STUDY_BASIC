package co.kr.jongin.blog.signin;

import org.apache.ibatis.session.SqlSession;
import co.kr.jongin.blog.common.MyAppSqlConfig;
import co.kr.jongin.blog.login.LoginDomain;

public class SigninMapper {

	private final static String NS = "co.kr.jongin.blog.signin.SigninMapper.";
	
	private static SqlSession session;
	
	public SigninMapper () {
		session = MyAppSqlConfig.getSqlSessionInstance();
	}
	
	public void signin(LoginDomain login) throws Exception {
		session.insert(NS + "signin", login);
	}
	
	
	public int signinIdChk(String id) throws Exception {
		return session.selectOne(NS + "signinIdChk", id);
	}
	
	
}

