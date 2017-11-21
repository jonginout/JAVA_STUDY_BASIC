package aop.test05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

public class Main {
	public static void main(String[] args) {
		
		try {

			ApplicationContext context = new ClassPathXmlApplicationContext(
						"aop/test5.xml"
					);
			
			Controller controller = context.getBean("board", Controller.class); 
			controller.execute();
			
			
			controller = context.getBean("member", Controller.class); 
			// "member" 찾는 부분은 항상 @Component("member")를 줘야한다.
			controller.execute();

			
			
		} catch (Exception e) {
			e.printStackTrace();		
		}
		
	}
}


