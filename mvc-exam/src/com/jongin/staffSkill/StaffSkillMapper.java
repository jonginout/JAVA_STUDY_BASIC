package com.jongin.staffSkill;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.jongin.common.MyAppSqlConfig;

public class StaffSkillMapper {

	private final static String NS = "com.jongin.staffSkill.StaffSkillMapper.";
	
	private static SqlSession session;
	
	public StaffSkillMapper () {
		session = MyAppSqlConfig.getSqlSessionInstance();
	}
	
	public int deleteStaffSkill(int staffNo) throws Exception {
		return session.delete(NS + "deleteStaffSkill", staffNo);
	}
	
	
	public List<StaffSkillDomain> detailStaffSkill(int staffNo) throws Exception {
		return session.selectList(NS + "detailStaffSkill", staffNo);
	}
	
	public List<StaffSkillDomain> listStaffSkill() throws Exception {
		return session.selectList(NS + "listStaffSkill");
	}
	
	public void insertStaffSkill(StaffSkillDomain staffSkill)
			throws Exception {
		
		System.out.println("스태프스킬도메인"+staffSkill.toString());
		
		session.insert(NS + "insertStaffSkill", staffSkill);
	}
}
