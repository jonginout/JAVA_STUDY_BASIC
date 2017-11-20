package di.test04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		
		// 컨테이너 객체 (ApplicationContext)가 지정된 위치의
		// xml 파일을 로딩해서 <bean> 태그에 지정된 객체들을
		// 관리하기 시작한다.
		ApplicationContext container = new ClassPathXmlApplicationContext(
					// 실제 이 컨테이너가 관리해야할 대상이 있는 xml 위치
					"di/test4.xml"
					// 밑에서 찾든 안찾든 실행되는 순간에
					// 미리 이 XML에 있는 정보를 쭉 모두 가져온다.
				);
	
		// 객체 찾기
//		HamSand sand = container.getBean("ham");
		
		// 무조건 아이디당 하나의 객체
		ISand sand = (ISand)container.getBean("ham");
		sand.info();
		
		sand = (ISand)container.getBean("ham2"); //name으로도 부를 수 있다.
		sand.info();
		
		
		
	}
}
