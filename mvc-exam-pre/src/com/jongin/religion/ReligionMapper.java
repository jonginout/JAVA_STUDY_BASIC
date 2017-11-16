package com.jongin.religion;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.jongin.common.MyAppSqlConfig;

public class ReligionMapper {

	private final static String NS = "com.jongin.religion.ReligionMapper.";
	
	private static SqlSession session;
	
	public ReligionMapper () {
		session = MyAppSqlConfig.getSqlSessionInstance();
	}
	
	public List<ReligionDomain> listReligion() throws Exception {
		return session.selectList(NS + "listReligion");
	}
	
}
