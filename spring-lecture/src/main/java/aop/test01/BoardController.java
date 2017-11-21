package aop.test01;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class BoardController implements Controller{

	
	@Override
	public void execute() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(
				"BoardController.execute 메소드 호출된 시간 : "+sdf.format(new Date())
		);
		
		System.out.println("BoardController 핵심기능... 수행중....");
		Random r = new Random();
		for (int i = 0; i < r.nextInt(100); i++) {
			Thread.sleep(100);
		}
		System.out.println("BoardController 핵심기능 종료..");
	}
	
	
}
