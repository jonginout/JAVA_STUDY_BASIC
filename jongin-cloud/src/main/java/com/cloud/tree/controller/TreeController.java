package com.cloud.tree.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
            	Tree tree = new Tree();
                if (ff.isFile()) {
                	tree.setTitle(ff.getName());
                	System.out.println("파일 : "+ff.getName());
                	if(ff.getName().lastIndexOf(".")!=-1) {
                		tree.setExt(ff.getName().substring(ff.getName().lastIndexOf(".")+1));
                		System.out.println("확장자 : "+ff.getName().substring(ff.getName().lastIndexOf(".")+1));
                	}
                }else if (ff.isDirectory()) {
                	tree.setTitle(ff.getName());
                	tree.setIsFolder(true);
                	tree.setIsLazy(true);
                	tree.setPath(ff.toString());
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
		
		String path = "C:\\tree\\"+user;
		
		File f = new File(path);
		if(!f.exists()) {
			f.mkdirs();
		}
		//폴더 만들기
		
		List<Tree> trees = pullFile(path);
		Tree tree = new Tree();
		
		System.out.println(trees.toString());
		tree.setTitle(user);
		tree.setIsFolder(true);
		tree.setPath(path);
		tree.setIsLazy(true);
		tree.setChildren(trees);
		
		return tree;
				
	}
	
	
	@RequestMapping("/newfolder.json")
	@ResponseBody
	public Map<String, Object> newfolder(String path, String name) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("dup", true);
		
		File f = new File(path+"\\"+name);
		if(!f.exists()) {
			map.put("dup", false);
			f.mkdirs();
		}
		
		return map;
	}
	
}
