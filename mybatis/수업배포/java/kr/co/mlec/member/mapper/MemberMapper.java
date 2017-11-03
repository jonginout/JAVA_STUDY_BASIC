package kr.co.mlec.member.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import common.db.MyAppSqlConfig;
import kr.co.mlec.member.MemberVO;
import kr.co.mlec.member.SearchVO;

public class MemberMapper {

	private static SqlSession sqlMapper;
	
	public MemberMapper() throws Exception {
		sqlMapper = MyAppSqlConfig.getSqlSessionInstance();
		System.out.println(sqlMapper);
//		test01();
//		test02();
//		insertMember();
//		updateMember();
//		deleteMember();
//		selectForeach();
//		dynamicSelect();
	}
	
	public void test01() throws Exception {
	}

	private void test02() throws Exception {
	}

	private void insertMember() throws Exception {
	}
	
	private void updateMember() throws Exception {
	}
	
	private void deleteMember() throws Exception {
	}
	
	private void selectForeach() throws Exception {
	}
	
	private void dynamicSelect( ) throws Exception {
	}

	public static void main(String[] args) {
		try {
			new MemberMapper();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
