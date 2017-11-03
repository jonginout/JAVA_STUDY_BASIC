package common;

import java.io.File;
import java.util.UUID;

import com.oreilly.servlet.multipart.FileRenamePolicy;

// 파일 리네임 정책 재정의
// 한글 -> 영어
public class HanbitFileRenamePolicy implements FileRenamePolicy{

	//생성자만 호출해도 자동으로 rename 메소드 호출
	@Override
	public File rename(File f) {
	
		// f 파일 객체의 경로는
		// c:/test/2017/aaa.jpg
		// 라고 가정 할 경우
		
		String name = f.getName();
		// aaa.jpg
		
		String ext = ""; // 확장자
		int index = name.lastIndexOf(".");
		if(index != -1) { // 찾았다면
			ext = name.substring(index);
		}
		
		String parent = f.getParent();
		// c:/test/2017/
		
		String fName = UUID.randomUUID().toString();
		// 랜덤한 값을 가진 유니크 값
		
		return new File(parent, fName+ext);
	}
	
	
}
