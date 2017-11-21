package aop.test04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		
		try {

			ApplicationContext context = new ClassPathXmlApplicationContext(
						"aop/test4.xml"
					);
			
			Controller controller = context.getBean("board", Controller.class); 
			// 두번쨰 인자가 있어서 형변환 필요없다.
			controller.execute();
			
//			controller = context.getBean("member", MemberController.class);
//			이렇게 하면 안된다. 이유는 프록시가 MemberController를 상속받은게 아니라
//			Controller를 상속 받았기 때문이다. 이를 해결하려면
//			xml에 옵션을 주면 된다. <aop:config proxy-target-class="true">
			
			controller = context.getBean("member", Controller.class);
			controller.execute();
			
			
			/*
			
				LogAspect의 공통기능을 핵심기능에 연결 한거다 
			
			*/
			
			
		} catch (Exception e) {
			e.printStackTrace();		
		}
		
	}
}


