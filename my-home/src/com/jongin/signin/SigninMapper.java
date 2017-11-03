package com.jongin.signin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.ibatis.session.SqlSession;

import com.jongin.common.ConnectionPool;
import com.jongin.common.MyAppSqlConfig;

public class SigninMapper {
	
	private static SqlSession session;
	private final static String NS = "signin.SigninMapper.";
	
	public SigninMapper() {
		session = MyAppSqlConfig.getSqlSessionInstance();
		if(session==null) {System.out.println("DB연결실패");}
	}
	
	public void insertMember(Signin signin){
		session.insert(NS+"insertMember", signin);
	}
	
	public Boolean idCheck(String id) {
		boolean result = false;
		int count = session.selectOne(NS+"getIdCheck", id);
		if(count > 0) {
			result = true;
		}
		return result;
	}


}
