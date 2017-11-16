package com.jongin.skill;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.jongin.common.MyAppSqlConfig;

public class SkillMapper {

	private final static String NS = "com.jongin.skill.SkillMapper.";
	
	private static SqlSession session;
	
	public SkillMapper () {
		session = MyAppSqlConfig.getSqlSessionInstance();
	}
	
	public List<SkillDomain> listSkill() throws Exception {
		return session.selectList(NS + "listSkill");
	}
	
}
