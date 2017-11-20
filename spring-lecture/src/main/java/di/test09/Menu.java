package di.test09;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Menu {
	
	//객체가 여러개일 경우 해결책
	@Autowired
	@Qualifier("ch")
	private ISand sand;
	// 값대입하는게 없다??
	
	public Menu() {
		System.out.println("Menu 기본 생성자");
		
	}

	public void print() {
		sand.info();
	}
	
}


//import 문장 하나와 @Autowired 애노테이션을 사용하면 
//get/set 접근 메서드를 더 이상 만들지 않아도 종합쇼핑몰 
//SpringFramework 이 설정 파일을 통해서 알아서 get/set 접근 메서드 대신 일을 해주도록 말이죠.

