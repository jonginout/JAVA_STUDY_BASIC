// tcp서버를 이용한 http 통신 이해하기
// 요청방식 처리

package day02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

// 톰캣 웹 서버도 처음에 실행할때 
// 메인메소드로 실행 자바로 만들었기 때문에

public class test03 {
	public static void main(String[] args) {
		
		try {
			ServerSocket ss = new ServerSocket(10001);
			System.out.println(" 서버 구동 중 ");
			while(true) { 
				// 여러명 접속하기 위해 루프로 
				Socket s = ss.accept();
				// 클라이언트와 클라이언트를 통신하기 위한것 소켓
				
				//연결된 클라이언트가 보내준 정보를 읽기 위해
				//바이트 처리
				InputStream in = s.getInputStream();
				
				InputStreamReader isr = new InputStreamReader(in);
				// 인풋스트림(바이트)를 리더로 변환해주겠다.
						
				//줄단위로 읽어들이기 위해서
				//문자 처리
				BufferedReader br = new BufferedReader(isr);
				
				System.out.println("-시작라인 정보-");
				System.out.println(br.readLine());
				
				System.out.println("-헤더정보-");
				while(true) {
					String line = br.readLine();
					if(line.equals("")) {
						break;
					}
					System.out.println(line);
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
