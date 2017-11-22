package kr.co.mlec.file;

import java.io.File;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/file")
public class UploadController {

	@Autowired
	ServletContext servletContext;

	@RequestMapping(value = "/upload.do", method = RequestMethod.POST) // 포스트 방식일때만 접근 가능하게 한다.
	public ModelAndView fileUpload(MultipartHttpServletRequest mRequest) throws Exception {
															// 예전의  MultipartRequest 라고 생각하면 된다.

		// 실행되는 웹어플리케이션의 실제 경로 가져오기
		String uploadDir = servletContext.getRealPath("/upload");
		System.out.println(uploadDir);
		
		File f = new File(uploadDir);
		if(!f.exists()) {
			f.mkdirs();
		}
		//폴더 만들기

		ModelAndView mav = new ModelAndView("file/uploadResult");

		String id = mRequest.getParameter("id");
		System.out.println("id : " + id);
		
		// 반환타입이 이뉴멀레이션인거 빼고 똑같다
		// 파일리스트를 루프 돌린다
		Iterator<String> iter = mRequest.getFileNames();
		while (iter.hasNext()) {

			String formFileName = iter.next();
			// 폼에서 파일을 선택하지 않아도 객체 생성됨
			MultipartFile mFile = mRequest.getFile(formFileName);
			// 스프링에서 제공하는 MultipartFile 클래스에 담는다.
			// 그냥 File 객체랑 다른 점은 
			// 클라이언트가 파일을 업로드 안하면 File 이 null온다
			// MultipartFile은 클라이언트가 업로드 하든 안하든 일단 MultipartFile 객체는 생성가능

			// 원본 파일명
			String oriFileName = mFile.getOriginalFilename();
			// 그렇기 때문에 원본이름으로 null을 구분
			
			System.out.println("원본 파일명 : " + oriFileName);

			if (oriFileName != null && !oriFileName.equals("")) {

				// 확장자 처리
				String ext = "";
				// 뒤쪽에 있는 . 의 위치
				int index = oriFileName.lastIndexOf(".");
				if (index != -1) {
					// 파일명에서 확장자명(.포함)을 추출
					ext = oriFileName.substring(index);
				}

				// 파일 사이즈
				long fileSize = mFile.getSize();
				System.out.println("파일 사이즈 : " + fileSize);

				// 고유한 파일명 만들기
				String saveFileName = "mlec-" + UUID.randomUUID().toString() + ext;
				System.out.println("저장할 파일명 : " + saveFileName);
				// 리네임 뭐 그런거 없이 바로 파라미터로 MultipartHttpServletRequest이렇게 넘어오기 떄문에
				// 파일 리네임 및 경로주는거 같은 행동을 을 우리가 줘야한다.
				

				// 임시저장된 파일을 원하는 경로에 저장
				mFile.transferTo(new File(uploadDir + "/" + saveFileName));
				// transferTo 를 해야 내가 원하는 곳으로 저장된다.
			}
		}
		return mav;
	}
	
	
	
	// 파일 여러개
	@RequestMapping(value="/upload2.do", method=RequestMethod.POST)
	public ModelAndView fileUpload2(FileVO fileVO) throws Exception {
		
			// 실행되는 웹어플리케이션의 실제 경로 가져오기
			String uploadDir = servletContext.getRealPath("/upload");
			System.out.println(uploadDir);
			
			File f = new File(uploadDir);
			if(!f.exists()) {
				f.mkdirs();
			}
			System.out.println("id : " + fileVO.getId());
			
			ModelAndView mav = new ModelAndView("file/uploadResult");
			
			for (MultipartFile file : fileVO.getAttach()) {
				if (file.isEmpty()) continue;
				
				String oriFileName = file.getOriginalFilename();
				// 그렇기 때문에 원본이름으로 null을 구분
				
				System.out.println("원본 파일명 : " + oriFileName);
	
				if (oriFileName != null && !oriFileName.equals("")) {
	
					// 확장자 처리
					String ext = "";
					// 뒤쪽에 있는 . 의 위치
					int index = oriFileName.lastIndexOf(".");
					if (index != -1) {
						// 파일명에서 확장자명(.포함)을 추출
						ext = oriFileName.substring(index);
					}
	
					// 파일 사이즈
					long fileSize = file.getSize();
					System.out.println("파일 사이즈 : " + fileSize);
	
					// 고유한 파일명 만들기
					String saveFileName = "mlec-" + UUID.randomUUID().toString() + ext;
					System.out.println("저장할 파일명 : " + saveFileName);
					// 리네임 뭐 그런거 없이 바로 파라미터로 MultipartHttpServletRequest이렇게 넘어오기 떄문에
					// 파일 리네임 및 경로주는거 같은 행동을 을 우리가 줘야한다.
					
	
					// 임시저장된 파일을 원하는 경로에 저장
					file.transferTo(new File(uploadDir + "/" + saveFileName));
					// transferTo 를 해야 내가 원하는 곳으로 저장된다.
				
				System.out.println(file.getOriginalFilename());
			}
		}
			return mav;
	}
}
