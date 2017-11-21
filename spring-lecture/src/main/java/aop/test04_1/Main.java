package aop.test04_1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		
		try {

			ApplicationContext context = new ClassPathXmlApplicationContext(
						"aop/test4_1.xml"
					);
			
			Controller controller = context.getBean("board", Controller.class); 

			controller.execute();
			

			controller = context.getBean("member", Controller.class);
			controller.execute();

			
			
		} catch (Exception e) {
			e.printStackTrace();		
		}
		
	}
}


