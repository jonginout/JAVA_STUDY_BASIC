package kr.co.mlec.file;

import org.springframework.web.multipart.MultipartFile;

public class FileVO {
	
	private String id;
	private MultipartFile[] attach;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public MultipartFile[] getAttach() {
		return attach;
	}
	public void setAttach(MultipartFile[] attach) {
		this.attach = attach;
	}
	
}
