package anno.quiz;

import java.lang.reflect.Method;
import java.util.Scanner;

public class Test01 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			
			Class<?> clz = BoardController.class;
			Object obj = clz.newInstance();
			Method[] mArr = clz.getDeclaredMethods();
			
			while (true) {
				System.out.print("경로 : ");
				String url = sc.nextLine();
				
				if (url.equals("quit")) break; // 종료
				
				boolean isExist = false;
				
				for (Method m : mArr) {
					RequestMapping rm = m.getAnnotation(RequestMapping.class);
					if (rm == null) continue;
					// 메소드에 어노테이션이 없으면 계속 (isExist는 여전히 false)
					
					String val = rm.value();
					if (!url.equals(val)) continue;
					// 내가 쓴거와 어노테이션 value가 다르면 계속 (isExist는 여전히 false)
					
					// 그게 아니라면 여기까지 내려오고
					isExist = true;
					m.invoke(obj);
					break;
					// 실행하고 종료
				}
				
				if (isExist == false) {
					System.out.println("존재하지 않는 경로입니다.");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
