package org.springframework.web.mvc;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class URLHandlerMapping {
	private Map<String, CtrlAndMethod> mappings;
	private List<String> ctrlNameList;
	
	public URLHandlerMapping() {}
	public URLHandlerMapping(String scanPackage) throws Exception {
		mappings = new HashMap<>();
		ctrlNameList = new ArrayList<>();
		
		getCtrlNameList(scanPackage);
		
		addMap();
	}

	private void addMap() throws Exception {
		for (String ctrlName : ctrlNameList) {
			Class<?> clz = Class.forName(ctrlName.trim());
			Object target = clz.newInstance();
			Method[] mArr = clz.getDeclaredMethods();
			for (Method method : mArr) {
				RequestMapping rm = method.getAnnotation(RequestMapping.class);
				if (rm == null) continue;
				
				mappings.put(
					rm.value(), new CtrlAndMethod(target, method)
				);
			}
		}
	}
	
	/**
	 * scanPackage 밑의 모든 클래스 중에서 @Controller 어노테이션이
	 * 설정된 컨트롤 클래스를 찾아서 ctrlNameList에 추가한다.
	 * @param scanPackage
	 */
	private void getCtrlNameList(String scanPackage) throws Exception {
		URL url = this.getClass()
					  .getClassLoader()
					  .getResource(scanPackage.replace(".", "\\"));
		File f = new File(url.toURI());
		String packagePath = f.getPath()
				              .replace(scanPackage.replace(".", "\\"), "");
		
		
		File[] fileList = f.listFiles();
		for (File file : fileList) {
			getClassNameByPackage(file, packagePath);
		}
	}
	
	/**
	 * file 이 만약 디렉토리라면 디렉토리 하위의 모든 파일들을 찾는다.
	 * file 이 파일 이라면 파일의 이름을 추출해서 클래스 패키지 형태로 변환
	 * @param file
	 * @param packagePath
	 */
	private void getClassNameByPackage(File file, String packagePath) 
			throws Exception {
		if (file.isDirectory()) {
			File[] fileList = file.listFiles();
			for (File f : fileList) {
				getClassNameByPackage(f, packagePath);
			}
		}
		else if (file.isFile()) {
			String fPath = file.getPath();
			String className = fPath.substring(0, fPath.lastIndexOf("."))   
					                .replace(packagePath, "")
					                .replace("\\", ".");
			
			// 어노테이션이 선언된 경우만 리스트에 추가
			try {
				boolean isExist = isControllerAnno(className);
				if (isExist == true) {
					System.out.println(className);
					ctrlNameList.add(className);
				}
			} catch (ClassNotFoundException cnf) {}
		}
	}
	
	/**
	 * 매개변수에 넘어온 클래스에 @Controller 어노테이션이 선언되어 있는지
	 * 판단
	 * @param className : 패키지를 포함한 클래스명
	 * @return
	 */
	private boolean isControllerAnno(String className) throws Exception {
		Class<?> clz = Class.forName(className);
		Controller cAnno = clz.getAnnotation(Controller.class);
		return cAnno != null;
	}
	
	public CtrlAndMethod getCtrlAndMethod(String requestUri) {
		return mappings.get(requestUri);
	}
}



//package org.springframework.web.mvc;
//
//import java.lang.reflect.Method;
//import java.util.HashMap;
//import java.util.Map;
//
//
///*
// 	Dispatcher의 역할 중 에서 uri와 controller 에 대한 정보 관리 부분을 처리
// */
//
//public class URLHandlerMapping {
//	
//	private Map<String, CtrlAndMethod> mappings = new HashMap<>();
//	
//	public URLHandlerMapping(String controllers) throws Exception {
//		
//		String[] arr = controllers.split(";");
//		
//		for (String ctrl : arr) {
//
//			// "패키지.클래스" 로 부터 클래스 정보를 뽑아온다
//			Class<?> clz = Class.forName(ctrl.trim());
//			// 그 클래스 인스턴스 객체를 뽑아 내고
//			Object target = clz.newInstance();
//			// 그 클래스의 메소드 들을 뽑아 내서
//			Method[] mArr = clz.getDeclaredMethods();
//
//			for (Method m : mArr) {
//				
//				RequestMapping rm = m.getAnnotation(RequestMapping.class);
//
//				if (rm == null)
//					continue;
//
//				mappings.put(rm.value(), new CtrlAndMethod(target, m));
//			}
//			
//		}
//	}
//	// 모든 컨트롤러 클래스와, 모든 컨트롤러 속 메소드를 매핑해준다.
//	
//	public CtrlAndMethod getCtrlAndMethod(String uri) {
//		
//		System.out.println(mappings.size());
//		// uri가 맞는 value 값 CtrlAndMethod를 뽑는다.
//		return mappings.get(uri);
//	}
//}