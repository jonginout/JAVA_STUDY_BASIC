package di.test10;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//이 클래스가 Menu로 자동 등록
@Component
public class Menu {
	
	//객체가 여러개일 경우 해결책
//	@Resource(name="egg")
	@Resource(name="eggSand")
	private ISand sand;
	// 값대입하는게 없다??
	
	public Menu() {
		System.out.println("Menu 기본 생성자");
		
	}

	public void print() {
		sand.info();
	}
	
}
