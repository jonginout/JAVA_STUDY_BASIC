package db.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import db.member.controller.MemberController;

public class MemberTest {

	public static void main(String[] args) {
		try {
			ApplicationContext factory = 
					new ClassPathXmlApplicationContext(
							"db/spring/context-repository.xml"); 
			MemberController controller = 
					factory.getBean(
							"memberController", MemberController.class);
	
			controller.retrieveMember();
//			controller.registMember();
//			controller.retrieveMemberInfo("test-123456");
//			controller.deleteMember("test-123456");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}









