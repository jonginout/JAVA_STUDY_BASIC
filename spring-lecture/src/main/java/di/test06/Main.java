package di.test06;

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
					"di/test6.xml"
					// 밑에서 찾든 안찾든 실행되는 순간에
					// 미리 이 XML에 있는 정보를 쭉 모두 가져온다.
				);
		System.out.println("---------------------");
		
		Menu menu = (Menu)container.getBean("menu");
		menu.printDesc();
		menu.printInfo();

		System.out.println("---------------------");
		
		Menu menu2 = (Menu)container.getBean("menu2");
		menu2.printDesc();
		menu2.printInfo();
		
	
		
	}
}
