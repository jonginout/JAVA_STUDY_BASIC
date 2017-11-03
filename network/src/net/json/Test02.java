package net.json;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.Gson;

// api 호출 테스트

// url : http://192.168.0.184:8080/lec04_network/api/member?id=admin
// 요청 파라미터 : id ( 필수 파라미터 )
// 응답 형태 : id, name, pass

public class Test02 {
	public static void main(String[] args) {
		

		String path = "http://192.168.0.136:8080/network/api/member";
		String param = "id=admin";
		
			try {
				URL url = new URL(path + "?" + param);
				InputStream in = url.openStream();
				InputStreamReader isr = new InputStreamReader(in);
				BufferedReader br = new BufferedReader(isr);
				
				StringBuffer sb = new StringBuffer();
				while(true) {
					String line = br.readLine();
					if(line==null) {break;}
					sb.append(line);
				}
				Member member = new Gson().fromJson(
						sb.toString(), Member.class
						);
				System.out.println(member.getId());
				System.out.println(member.getPass());
				System.out.println(member.getName());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
