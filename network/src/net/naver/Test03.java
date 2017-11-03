package net.naver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;

public class Test03 {
	public static void main(String[] args) {
		
        String clientId = "Ei3LFLWQ_lD_J3uXpfiT";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "n2GMcqH5QL";//애플리케이션 클라이언트 시크릿값";
        
        try {
            String text = URLEncoder.encode("김종인", "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/blog?query="+ text; // json 결과
            //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection(); // url 커넥션 클래스 타입으로
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);			//  ↓
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);  // 헤더값 설정을 해준다.
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
            NaverSearch search = new Gson().fromJson(response.toString(), NaverSearch.class);
            
            for (Item i : search.getItems()) {
            	System.out.println("=================================================================");
				System.out.println(i.getTitle());
				System.out.println(i.getLink());
			}
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
	}
}
