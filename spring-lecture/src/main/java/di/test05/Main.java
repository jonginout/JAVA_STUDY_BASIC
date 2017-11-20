package di.test05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import di.test04.ISand;

public class Main {
	public static void main(String[] args) {
		
		// 컨테이너 객체 (ApplicationContext)가 지정된 위치의
		// xml 파일을 로딩해서 <bean> 태그에 지정된 객체들을
		// 관리하기 시작한다.
		ApplicationContext container = new ClassPathXmlApplicationContext(
					// 실제 이 컨테이너가 관리해야할 대상이 있는 xml 위치
					"di/test5.xml"
					// 밑에서 찾든 안찾든 실행되는 순간에
					// 미리 이 XML에 있는 정보를 쭉 모두 가져온다.
				);
		System.out.println("---------------------");
		
		Menu menu1 = (Menu)container.getBean("menu1");
		menu1.printDesc();
		
		System.out.println("---------------------");

		Menu menu2 = (Menu)container.getBean("menu2");
		menu2.printDesc();

		System.out.println("---------------------");
		
		Menu menu3 = (Menu)container.getBean("menu3");
		menu3.printInfo();
		
		System.out.println("---------------------");
		
		Menu menu4 = (Menu)container.getBean("menu4");
		menu4.printInfo();
		
		System.out.println("---------------------");
		// 객체에 대한 new 가 없다는게 중요하다
		// xml에서 하고 있다.
		
	}
}
