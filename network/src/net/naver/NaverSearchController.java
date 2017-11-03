package net.naver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/naver/naversearch")
public class NaverSearchController extends HttpServlet{
	
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		// 리스트 보여줄 갯 수 파라미터 받기
		String showNum = "10";
		if(request.getParameter("showNum")!=null) {
			showNum = request.getParameter("showNum");
		}
		
		// 검색어
		String word = request.getParameter("word");
		if(word != null && word.length() !=0) {
			
			// 네이버 검색 JSON 반환 (밑에 메소드) 어트리뷰트 전송
			List<Item> list = search(word,showNum);
			request.setAttribute("list", list);
		}

		
		RequestDispatcher rd = request.getRequestDispatcher(
					"/naver/naversearch.jsp"
				);
		rd.forward(request, response);
		
		
	}

	public List<Item> search(String word, String showNum) {
		
		NaverSearch search = null;
		
        String clientId = "Ei3LFLWQ_lD_J3uXpfiT";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "n2GMcqH5QL";//애플리케이션 클라이언트 시크릿값";
        try {
            String text = URLEncoder.encode(word, "UTF-8");
            String display = URLEncoder.encode(showNum, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/blog?query="+ text+"&display="+ display; // json 결과
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
            search = new Gson().fromJson(response.toString(), NaverSearch.class);

            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return search.getItems();
        
	}
}
