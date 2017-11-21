package aop.test04_1;

import java.util.Random;

public class MemberController implements Controller{

	
	@Override
	public void execute() throws Exception {

		
		System.out.println("MemberController 핵심기능... 수행중....");
		Random r = new Random();
		for (int i = 0; i < r.nextInt(100); i++) {
			Thread.sleep(100);
		}
		System.out.println("MemberController 핵심기능 종료..");
	}
	
	
}
