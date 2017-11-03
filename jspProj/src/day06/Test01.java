package day06;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.oreilly.servlet.MultipartRequest;

import common.HanbitFileRenamePolicy;


@WebServlet("/day06/test01")
public class Test01 extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
	
		// 파일 업로드 라이브러리
		// - cos.jar / apache commons
		
		// 파일이 저장되는 기본 업로드 루트에
		// /연도/월/일/시간
		SimpleDateFormat sdf = new SimpleDateFormat(
					/*/회원명/게시판*/"/yyyy/MM/dd/HH"
				);
		String subPath = sdf.format(new Date());
		String uploadPath = "C:/jongin/upload";
		File f = new File(uploadPath+subPath);
		if(!f.exists()) {
			f.mkdirs();
		}
		
		// - cos
		// 멀티파트로 가져온 데이터다
		MultipartRequest mRequest = new MultipartRequest(
				request,	// request 자체를 넘긴다.
				uploadPath+subPath,	// 어디에 저장할지 위치
				1024 * 1024 * 100,	// 최대 사이즈 크기 (1024*1024 = 1메가)
				"utf-8",	// 파라미터값 인코딩을 뭐로 하겠냐
				new HanbitFileRenamePolicy()	// 기본제공 이름 중복 정책
				//생성자만 호출해도 자동으로 rename 메소드 호출
			);
		
		String msg = mRequest.getParameter("msg");	
		System.out.println("파일업로드 성공"+msg);
		
		// 테이블에 파일 정보를 저장하기 위한 정보 추출
		// 사용자가 파일을 선택해서 올린 경우에만 실행한다.
		// file 이 null 일 경우 사용자가 파일을 선택하지 않은 경우
		
		// 파일이 여러개 넘어온 경우		//이름만 가져온다.
		Enumeration<String> fNames = mRequest.getFileNames();
		while (fNames.hasMoreElements()) {
			String fName = fNames.nextElement();
			System.out.println("fName : " + fName);
			File file = mRequest.getFile(fName);
			if (file != null) {
				long fileSize = file.length();
				String oriName = mRequest.getOriginalFileName(fName);
				String systemName = mRequest.getFilesystemName(fName);
				System.out.println("사이즈 : " + fileSize);
				System.out.println("원래이름 : " + oriName);
				System.out.println("시스템이름 : " + systemName);
			}
		}
	}
	
}

//		// 화면에서 전송되는 데이터를 확인
//		PrintWriter out = response.getWriter();
//		
//		// 요청 body에 들어온 내용을 확인
//		InputStream in = request.getInputStream();
//		Scanner sc = new Scanner(in); //줄단위
//		
//		while( sc.hasNextLine() ) {
//			out.println(sc.nextLine());
//		}
//		out.close();