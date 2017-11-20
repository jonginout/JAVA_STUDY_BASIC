package di.test10;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
	public static void main(String[] args) {
		
		ApplicationContext container = new ClassPathXmlApplicationContext(
					"di/test10.xml"
				);
		
		System.out.println("=============================");		
		
		Menu menu = (Menu)container.getBean("menu");
		menu.print();
		
	}
}
