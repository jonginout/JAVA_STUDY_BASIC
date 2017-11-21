package aop.test04;

import java.util.Random;

public class BoardController implements Controller{
	
	@Override
	public void execute() throws Exception {

		
		System.out.println("BoardController 핵심기능... 수행중....");
		Random r = new Random();
		for (int i = 0; i < r.nextInt(100); i++) {
			Thread.sleep(100);
		}
		System.out.println("BoardController 핵심기능 종료..");
	}
	
	
}
