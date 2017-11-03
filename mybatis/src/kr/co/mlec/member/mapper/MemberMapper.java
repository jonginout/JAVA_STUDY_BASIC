package kr.co.mlec.member.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import common.db.MyAppSqlConfig;
import kr.co.mlec.member.domain.Member;
import kr.co.mlec.member.domain.Search;

public class MemberMapper {

	// SqlSession
	// web의 세션과 무관
	private static SqlSession session;
	private final static String NS = "kr.co.mlec.member.mapper.MemberMapper.";
	
	public MemberMapper() throws Exception {
		session = MyAppSqlConfig.getSqlSessionInstance();
		if(session==null) {System.out.println("DB연결실패");}
//		test01();
//		test02();
//		insertMember();
//		updateMember();
//		deleteMember();
//		selectForeach();
		dynamicSelect();
	}
	
	// detail
	public void test01() throws Exception {
		// member.xml에 있는 id가 getMemberInfo1인 태그를 호출하고 싶다!
		// xml에 있는 sql을 호출하기 위해서 SqlSession 객체가 필요하다.
		Member member = session.selectOne(
				NS+"getMemberInfo1" ,
				"member-2"
				);
		
		
		
		Member param = new Member();
		param.setId("member-3");
		param.setName("jsp");
		
		Member member2 = session.selectOne(
				NS+"getMemberInfo2",
				param
				);
		
		System.out.println(member.toString());
		System.out.println("====================");
		System.out.println(member2.toString());
		System.out.println("====================");
		
		String name = session.selectOne(
				NS+"getMemberInfo3",
				"member-2"
				);
		System.out.println(name);
		System.out.println("====================");
		
		int count = session.selectOne(
				NS+"getMemberInfo4"
				);
		System.out.println(count);
		System.out.println("====================");
		
	}

	//list
	private void test02() throws Exception {
		
		List<Member> list = session.selectList(NS+"getMemberList1");
		System.out.println(list);
		
		List<String> stList = session.selectList(NS+"getMemberList2");
		System.out.println(stList);
		
		//join_date 결과 들어온다.
		List<Member> list2 = session.selectList(NS+"getMemberList3");
		System.out.println(list2);
		
	}

	//insert
	private void insertMember() throws Exception {
		Member param = new Member();
		param.setId("cv613");
		param.setName("김종인");
		param.setEmail("cv613@naver.com");
		param.setAddr("송파구");
		session.insert(NS+"insertMember", param);
//		session.commit(); // 수정쿼리는 커밋필수
		System.out.println("등록 성공..");
	}
	
	private void updateMember() throws Exception {
		Member param = new Member();
		param.setId("cv613");
		param.setName("김종은");
		param.setEmail("chopo01@naver.com");
		param.setAddr("위례동");
		session.update(NS+"updateMember", param);
//		session.commit();
		System.out.println("업데이트 성공");
	}
	
	private void deleteMember() throws Exception {
		Member param = new Member();
		param.setId("cv613");
		param.setName("김종은");
		session.update(NS+"deleteMember", param);
//		session.commit();
		System.out.println("삭제 성공");
	}
	
	private void selectForeach() throws Exception {
		
		Member param = new Member();
		param.setAddrs(new String[] {"busan", "seoul"});
		List<Member> lsit = 
				session.selectList(NS+"getMemberIteratorList", param);
		
		for (Member m : lsit) {
			System.out.println(m);
		}
		
	}
	
	private void dynamicSelect( ) throws Exception {
		List<Member> list = session.selectList(
				NS+"getMemberIncludeList"
				);
		int count = session.selectOne(
				NS+"getMemberIncludeListCount"
				);
		
		for (Member m : list) {
			System.out.println(m.getId());
		}
		System.out.println(count);
		
		System.out.println("=====================");
		
		Search param = new Search();
		param.setSearchType("name");
		param.setSearchWord("p");
		List<Member> list2 = session.selectList(
				NS+"getMemberSearchList", param
				);
		for (Member m : list2) {
			System.out.println(m.getId());
		}
		
		System.out.println("=====================");

		Member param2 = new Member();
//		param2.setId("ber-1");
		param2.setName("s");
		List<Member> list3 = session.selectList(
				NS+"getMemberSearchList2", param2
				);
		for (Member m : list3) {
			System.out.println(m.getId());
		}
		
	}

	public static void main(String[] args) {
		try {
			new MemberMapper();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
