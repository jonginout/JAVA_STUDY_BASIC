package di.test10;

import org.springframework.stereotype.Component;

// egg 클래스를 bean으로 등록
//@Component(value="egg")   //이름이 다르다 그래서 value로 bean 이름을 등록해준다
@Component
public class EggSand implements ISand{
	
	
	public EggSand() {
		System.out.println("계란샌드위치 생성자 호출!");
	}
	
	public void info() {
		System.out.println("계란샌드위치의 정보입니다...");
	}
	
}
