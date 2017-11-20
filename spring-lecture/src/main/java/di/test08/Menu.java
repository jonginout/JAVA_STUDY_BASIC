package di.test08;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;


public class Menu {
	
	//객체가 여러개일 경우 해결책
	@Resource(name="isegg") // 리소스는 id로 찾을 수 있다. //이게 우선 //즉 다른거와 다르게 이름으로 찾는다.
	@Qualifier("ch")	//이게 2번째 우선수위
	
	//Qualifier는 @Resource나 @Autowired를 보강해주는 어노테이션
	
	
	private ISand sand;
	// 값대입하는게 없다??
	
	public Menu() {
		System.out.println("Menu 기본 생성자");
		
	}

	public void print() {
		sand.info();
	}
	
}
