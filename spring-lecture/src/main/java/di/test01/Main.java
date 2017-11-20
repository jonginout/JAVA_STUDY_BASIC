package di.test01;

// 필요한 위치에서 객체 생성
class LgTV{
	public void powerOn() {
		System.out.println("lg 전원 킴");
	}
	public void powerOff() {
		System.out.println("lg 전원 꺼짐");		
	}
}

class SonyTV{
public void powerOn() {
		System.out.println("sony 전원 킴");		
	}
	public void powerOff() {
		System.out.println("sony 전원 꺼짐");		
	}
}

public class Main {
	public static void main(String[] args) {
		
		LgTV lg = new LgTV();
		lg.powerOn();
		lg.powerOff();
		
		SonyTV sony = new SonyTV();
		sony.powerOn();
		sony.powerOff();
		// 클래스와 클래스간의 결합도가 매우 높아서
		// 유지보수가 힘들다
		// 이래서 인터페이스를 사용
		
	}
}
