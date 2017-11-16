package com.jongin.staff;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

import com.jongin.common.MyAppSqlConfig;

public class StaffMapper {

	private final static String NS = "com.jongin.staff.StaffMapper.";
	
	private static SqlSession session;
	
	public StaffMapper () {
		session = MyAppSqlConfig.getSqlSessionInstance();
	}
	
	public int listStaffCount(StaffDomain staff) throws Exception {
		return session.selectOne(NS + "listStaffCount", staff);
	}
	
	public StaffDomain detailStaff(int staffNo) throws Exception {
		return session.selectOne(NS + "detailStaff", staffNo);
	}
	

	public int deleteStaff(int staffNo) throws Exception {
		return session.delete(NS + "deleteStaff", staffNo);
	}
	
	
	public int staffGetNextStaffNo() throws Exception {
		return session.selectOne(NS + "staffGetNextStaffNo");
	}
	
	public List<StaffDomain> listStaff(StaffDomain staff) throws Exception {
		System.out.println(staff.getPageNo());
		System.out.println(staff.getListPerPage());
		System.out.println(staff.getBegin());
		System.out.println(staff.getEnd());
		System.out.println(staff.toString());

		return session.selectList(NS + "listStaff", staff);
	}
	
	public void insertStaff(StaffDomain staff)
			throws Exception {
		
		System.out.println(staff);
		
		session.insert(NS + "insertStaff", staff);
	}
	
	public void modifyStaff(StaffDomain staff)
			throws Exception {
		
		System.out.println(staff);
		
		session.update(NS + "modifyStaff", staff);
	}
	
}
