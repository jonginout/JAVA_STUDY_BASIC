package aop.test02;

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

// BoardController와 이름만 다르고 다 똑같은데 다 복붙이다.??? 너무 비효율적이다...
// 하나 고치면 나머지 파일도 다 고쳐야 한다...

