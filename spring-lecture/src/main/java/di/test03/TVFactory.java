package di.test03;

// 별도의 조립기 클래스를 활용하여 객체 생성

public class TVFactory {
	public static TV getBean(String name) {
		switch (name) {
		case "sony":
			return new SonySmartTV();
		case "lg":
			return new LgTV();
		default:
			return null;
		}
	}
}
