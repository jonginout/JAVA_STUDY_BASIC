package aop.test03;

public class Main {
	public static void main(String[] args) {
		
		try {
			Controller controller = new ProxyController(
						new BoardController()
					);
			
			controller.execute();
			
			System.out.println("===================================================");
			
			
			controller = new ProxyController(
					new MemberController()
				);
		
			controller.execute();
			
		} catch (Exception e) {
			e.printStackTrace();		
		}
		
	}
}


/*
	프록시가 동적으로 클래스를 만들어서 처리
	
	자기가 수행해서 연결해야 할 부모 인터페이스를 통해 
	클래스를 만들고 호출도 프록시가 한다.
	
	
	스프링에서는 프록시 없이 연결 가능 이제 부터 해보자
	
 */