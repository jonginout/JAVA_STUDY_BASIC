package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
	private static final int INIT_COUNT = 3;
	private static List<Connection> free = new ArrayList<>();
	private static List<Connection> used = new ArrayList<>();
	
	// static 블락
	// 클래스 정보가 로딩될때 최초 한번만 실행
	static {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				for (int i = 0; i < INIT_COUNT; i++) {
					Connection con = DriverManager.getConnection(
							"jdbc:oracle:thin:@localhost:1521:xe",
							"mini", "mini"
						);
					free.add(con);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public static Connection getConnection() throws Exception{
		
		if(free.isEmpty()) {
			throw new Exception("사용할 수 있는 커넥션이 없습니다.");
		}
		
		Connection con = free.remove(0); //누굴 삭제했는지 넘겨준다
		used.add(con);
		return con;
		
	}
	
	public static void releaseConnection(Connection con) {
		used.remove(con); 
		free.add(con);
	}
	
	public static void main(String[] args) {
		try {
			for (int i = 0; i < 10; i++) {
				Connection con = ConnectionPool.getConnection();
				System.out.println(con);
				
				ConnectionPool.releaseConnection(con);
				//재사용된 주소는 똑같이 나온다
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

