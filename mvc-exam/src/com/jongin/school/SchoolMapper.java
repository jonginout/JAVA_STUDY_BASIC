package com.jongin.school;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.jongin.common.MyAppSqlConfig;

public class SchoolMapper {

	private final static String NS = "com.jongin.school.SchoolMapper.";
	
	private static SqlSession session;
	
	public SchoolMapper () {
		session = MyAppSqlConfig.getSqlSessionInstance();
	}
	
	public List<SchoolDomain> listSchool() throws Exception {
		return session.selectList(NS + "listSchool");
	}
	
}
