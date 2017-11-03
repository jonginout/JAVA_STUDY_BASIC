package homepage;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import common.HanbitFileRenamePolicy;

@WebServlet("/jsp/homepage/homepagesetting")
public class HomePageSetting extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

	int homepageNo = Integer.parseInt(request.getParameter("homepage_no"));
	
//	SimpleDateFormat sdf = new SimpleDateFormat("/"+homepageNo+"/yyyy/MM/dd/HH");
//	String subPath = sdf.format(new Date());
	String subPath = "/homePageSetting/"+homepageNo;
	String uploadPath = "C:/mini-home/upload";
	File f = new File(uploadPath + subPath);
	if (!f.exists()) {
		f.mkdirs();
	}
	
	MultipartRequest mRequest = new MultipartRequest(
			request,	// request 자체를 넘긴다.
			uploadPath+subPath,	// 어디에 저장할지 위치
			1024 * 1024 * 100,	// 최대 사이즈 크기 (1024*1024 = 1메가)
			"utf-8",	// 파라미터값 인코딩을 뭐로 하겠냐
			new HanbitFileRenamePolicy()	// 기본제공 이름 중복 정책
			//생성자만 호출해도 자동으로 rename 메소드 호출
		);
	
	
	String title = mRequest.getParameter("title");
	String introduce = mRequest.getParameter("introduce");
	String openRange = mRequest.getParameter("open_range");
	char photoGallaryUseYn = mRequest.getParameter("photoGallaryUseYn")==null ? 'F' : 'Y';
	char fileGallaryUseYn = mRequest.getParameter("fileGallaryUseYn")==null ? 'F' : 'Y';
	char guestBookUseYn = mRequest.getParameter("guestBookUseYn")==null ? 'F' : 'Y';
	char diaryUseYn = mRequest.getParameter("diaryUseYn")==null ? 'F' : 'Y';
	
	
	HomePageDomain homePage = new HomePageDomain();
	
	homePage.setProfile(mRequest.getParameter("tmpProfile"));
	homePage.setBackgroundImg(mRequest.getParameter("tmpBackgroundImg"));
	homePage.setBgm(mRequest.getParameter("tmpBgm"));
	
	Enumeration<String> fNames = mRequest.getFileNames();
	while (fNames.hasMoreElements()) {
		String fName = fNames.nextElement();
		System.out.println("fName : " + fName);
		File file = mRequest.getFile(fName);
		if (file != null) {
			String systemName = mRequest.getFilesystemName(fName);
			System.out.println("시스템이름 : " + systemName);
			String path = subPath+"/"+systemName;
			System.out.println(path);
			if(fName.equals("bgm")) {
				homePage.setBgm(path);
			}
			if(fName.equals("profile")) {
				homePage.setProfile(path);
			}
			if(fName.equals("backgroundImg")) {
				homePage.setBackgroundImg(path);
			}
		}
	}
	
	System.out.println(title);
	System.out.println(introduce);
	System.out.println(photoGallaryUseYn);
	System.out.println(fileGallaryUseYn);
	System.out.println(guestBookUseYn);
	System.out.println(diaryUseYn);
	
	homePage.setHomepageNo(homepageNo);
	homePage.setTitle(title);
	homePage.setIntroduce(introduce);
	homePage.setOpenRange(openRange);
	homePage.setPhotoGallaryUseYn(photoGallaryUseYn);
	homePage.setFileGallaryUseYn(fileGallaryUseYn);
	homePage.setGuestBookUseYn(guestBookUseYn);
	homePage.setDiaryUseYn(diaryUseYn);
	

	System.out.println(homePage.toString());
	
	HomePageDAO dao = new HomePageDAO();

		try {
			dao.modifyHomePage(homePage);
			response.sendRedirect(request.getContextPath()+"/jsp/homepage/homepage?homepage_no="+homepageNo);
		} catch (Exception e) {
			throw new ServletException(e);
		} 
	
	}
}





