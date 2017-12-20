package com.cloud.tree.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.repository.vo.Tree;
import com.cloud.tree.service.TreeService;

@Controller
@RequestMapping("/tree")
public class TreeController {

	@Autowired
	TreeService service;
	
	
    //ff 폴더 안 모든 파일 및 폴더 검색
	List<Tree> pullFile(String path) {
		
        File[] list = new File(path).listFiles(); 
        List<Tree> trees = new ArrayList<>();
        
        try{
            for (File ff : list) {
            	
            	//jci 파일 제외
            	if(FilenameUtils.getExtension(ff.getName()).equals("jci")) {continue;}
            	
            	Tree tree = new Tree();
            	
            	Date updateDate = new Date(ff.lastModified());
            	tree.setUpdateDate(updateDate);
            	tree.setTitle(ff.getName());
            	tree.setPath(ff.toString());
            	
                if (ff.isFile()) {
                	System.out.println("파일 : "+ff.getName());
            		tree.setExt(FilenameUtils.getExtension(ff.getName()));
                	
                }else if (ff.isDirectory()) {
                	tree.setIsFolder(true);
                	tree.setIsLazy(true);
                	System.out.println("폴더 : "+ff.getName());
                	
                    //pullFile(ff.toString());
                }
                trees.add(tree);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return trees;
    }
	
	void deleteFile(String path) {
		
		File file = new File(path);
		File [] fList = file.listFiles();

		if(!file.isDirectory()) {
			file.delete();
		}else {
			for (File f : fList) {
				deleteFile(f.toString());
			}			
		}
		
		file.delete();
		
	}
    
	@RequestMapping("/sublist.json")
	@ResponseBody
	public List<Tree> subTree(String path) throws Exception {
		
		System.out.println(path);
		
		List<Tree> trees = pullFile(path);
		System.out.println(trees.toString());
		
		return trees;
				
	}
	

	@RequestMapping("/list.json")
	@ResponseBody
	public Tree rootTree(String user) throws Exception {
		
		String path = user;
		
		File f = new File(path);
		if(!f.exists()) {
			f.mkdirs();
		}
		//폴더 만들기
		
		List<Tree> trees = pullFile(path);
		Tree tree = new Tree();
		
		Date updateDate = new Date(f.lastModified());
		
		System.out.println(trees.toString());
		tree.setTitle(user);
		tree.setIsFolder(true);
		tree.setPath(path);
		tree.setIsLazy(true);
		tree.setChildren(trees);
		tree.setUpdateDate(updateDate);
		
		return tree;
				
	}
	
	
	@RequestMapping("/newfolder.json")
	@ResponseBody
	public Map<String, Object> newfolder(String path, String name) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("dup", true);
		
		File f = new File(path+"/"+name);
		if(!f.exists()) {
			map.put("dup", false);
			f.mkdirs();
		}
		
		return map;
	}
	
	@RequestMapping("/newCode.json")
	@ResponseBody
	public Map<String, Object> newCode(String path, String name) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("dup", true);
		
		File f = new File(path+"/"+name);
		if(!f.exists()) {
			map.put("dup", false);
			f.createNewFile();
		}
		
		return map;
	}
	
	@RequestMapping("/filedelete.json")
	@ResponseBody
	public void fileDelete(String path) throws Exception {
		
		String jci = path+".jci";
		
		deleteFile(path);
		deleteFile(jci);
	}
	
	@RequestMapping("/filerename.json")
	@ResponseBody
	public Map<String, Object> fileRename(String path, String rename) throws Exception {
		
		Map<String, Object> map = new HashMap<>();
		File f = new File(path);
		Path file = Paths.get(path);

		String ext = "";
		
		if(f.isDirectory()) {
			path = path.substring(0, path.lastIndexOf("/")+1)+rename;
		}else {
			ext = f.getName().substring(f.getName().lastIndexOf("."));
			path = path.substring(0, path.lastIndexOf("/")+1)+rename+ext;
		}
		
		
		try {
			Files.move(file , Paths.get(path));
			map.put("result", true);
		} catch (Exception e) {
			System.out.println(e);
			map.put("result", false);
		}
		return map;
	}
	
	
	@RequestMapping("/filemove.json")
	@ResponseBody
	public Map<String, Object> fileMove(String moveFilePath, String recFilePath) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		System.out.println("옮길 것 : "+moveFilePath);
		
		Path moveF = Paths.get(moveFilePath);
		File recF = new File(recFilePath);
		
		if(!recF.isDirectory()) {
			map.put("result", false);
			return map;
		}
		
		System.out.println(recFilePath);
		Path recPath = Paths.get(recFilePath+"/"+moveF.getFileName());
		System.out.println("옮겨질 곳 : "+recPath);
		
		try {
			Files.move(moveF , recPath);
			map.put("result", true);
			map.put("path", ""+recPath);
		} catch (Exception e) {
			System.out.println(e);
			map.put("result", false);
		}
		
		return map;
		
	}
	
	@RequestMapping("/codeview.json")
	@ResponseBody
	public Map<String, Object> codeView(String path) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		
		String line;
		String code = "";
		while((line=br.readLine())!=null){
			code += line+"\n";
		}
		
		map.put("code", code);
		return map;
	}
	
	@RequestMapping("/codechange.json")
	@ResponseBody
	public void codeChange(String path, String changeCode) throws Exception {
		
		deleteFile(path);

		File file = new File(path);
		FileWriter fw = new FileWriter(file);
		fw.write(changeCode);
		fw.close();
		
	}
	
	@RequestMapping("/fileupload.json")
	@ResponseBody
	public Map<String, Object> fileUpload(
			String uploadPath, MultipartFile[] files
			) throws Exception {
		
		System.out.println("================");

		Map<String, Object> map = new HashMap<>();
		
		System.out.println("업로드 할 경로 : "+uploadPath);
		
		
        File[] list = new File(uploadPath).listFiles(); 
        List<String> fileNameList = new ArrayList<>(); 

        // 업로드할 경로에 존재하는 파일을 읽는다 (중복확인용)
        for (File f : list) {
        	fileNameList.add(f.getName());
        }
		
        
		for (MultipartFile f : files) {
			if(f.isEmpty()) {continue;}
			
			String oriFileName = f.getOriginalFilename();
			String saveFileName = oriFileName;
			
			if(fileNameList.indexOf(oriFileName)!=-1) {
				String ori = oriFileName.substring(0, oriFileName.lastIndexOf("."));
				SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd-hh-mm-ss");
				String hash = "("+sdf.format(new Date())+")";
				String ext = oriFileName.substring(oriFileName.lastIndexOf("."));
				
				saveFileName = ori+hash+ext;
				
				map.put("dup", true);
			}
			
			System.out.println("업로드한 파일 명 : "+oriFileName);
			
			if(oriFileName != null && !oriFileName.equals("")) {
				long fileSize = f.getSize();
				System.out.println("파일 사이즈 : " + fileSize);
				
				f.transferTo(new File(uploadPath+"/"+saveFileName));
			}
			
		}
		
		System.out.println("================");
		return map;
		
	}
	
	
	@RequestMapping("/filecomment.json")
	@ResponseBody
	public void fileComment(String comment, Tree tree) throws Exception {
		
		String commentPath = tree.getPath()+"/"+tree.getTitle()+".jci";
		
		deleteFile(commentPath);
		
		File file = new File(commentPath);
		if(!file.exists()) {
			file.createNewFile();
		}
		
		FileWriter fw = new FileWriter(file);
		fw.write(comment);
		fw.close();
		
		
	}
	
	@RequestMapping("/commentview.json")
	@ResponseBody
	public String commentView(Tree tree) throws Exception {
		
		String commentPath = tree.getPath()+"/"+tree.getTitle()+".jci";
				
		File file = new File(commentPath);
		if(!file.exists()) {
			return null;
		}
		
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		String line;
		String comment = "";
		while((line=br.readLine())!=null){
			comment += line+"\n";
		}
		
		return comment;
	}
	
	
}
