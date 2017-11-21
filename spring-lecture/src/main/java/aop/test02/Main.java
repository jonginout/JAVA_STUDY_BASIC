package aop.test02;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
	public static void main(String[] args) {
		
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(
					"BoardController.execute 메소드 호출된 시간 : "+sdf.format(new Date())
			);
			
			Controller controller = new BoardController();
			controller.execute();
			
			System.out.println("===================================================");
			
			System.out.println(
					"MemberController.execute 메소드 호출된 시간 : "+sdf.format(new Date())
			);
			
			controller = new MemberController();
			controller.execute();
			
		} catch (Exception e) {
			e.printStackTrace();		
		}
		
	}
}

//공용으로 빼는 방법?

// 메인에 넣긴 넣는데 이건 개오바다