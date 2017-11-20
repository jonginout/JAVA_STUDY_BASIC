package di.test03;

// 인터페이스를 활용한 객체 생성

interface TV {
	void powerOn();
	void powerOff();
}

class LgTV implements TV{
	public void powerOn() {
		System.out.println("lg 전원 킴");
	}
	public void powerOff() {
		System.out.println("lg 전원 꺼짐");		
	}
}

class SonyTV implements TV{
public void powerOn() {
		System.out.println("sony 전원 킴");		
	}
	public void powerOff() {
		System.out.println("sony 전원 꺼짐");		
	}
}

class SonySmartTV extends SonyTV{
	public void internet() {
		System.out.println("sony 인터넷 하기");		
	}
}

public class Main {
	
	public static void setTV(TV tv) {
		tv.powerOn();
		tv.powerOff();
	}
	
	public static void main(String[] args) {
		
		setTV(TVFactory.getBean("sony"));
		setTV(TVFactory.getBean("lg"));
		
	}
}
