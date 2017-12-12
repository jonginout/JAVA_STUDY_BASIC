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
	
//	@RequestMapping("/turnlist.do")
//	public ModelAndView list(Turn turn) throws Exception {
//		ModelAndView mav = new ModelAndView("turn/turnlist");
//		
//		System.out.println(turn.toString());
//
//		Curri curri = curriService.curriDetail(turn.getCurriNo());
//		List<Turn> turns = service.turnList(turn);
//
//		mav.addObject("turns", turns);
//		mav.addObject("turnsJson", new Gson().toJson(turns));
//		mav.addObject("curri", curri);
//		return mav;
//
//	}
	
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
                }else if (ff.isDirectory()) {
                	tree.setTitle(ff.getName());
                	tree.setIsFolder(true);
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
    
	@RequestMapping("/list.json")
	@ResponseBody
	public List<Tree> detail(String dir) throws Exception {
		
		String root = "C:\\jongin\\tree";
		
		return pullFile(root);
		
//		
//		for (int i = 0; i < 2; i++) {	
//			Tree tree = new Tree();
//			tree.setTitle(i+"번째 폴더");
//			tree.setIsFolder(true);
//			trees.add(tree);
//		}
//		//폴더속 폴더는??
//		List<Tree> trees2 = new ArrayList<>();
//		Tree tree2 = new Tree();
//		tree2.setTitle("종인짱");
//		trees2.add(tree2);
//		
//		trees.get(0).setChildren(trees2);
//		
//		return trees;
		
		
	}

	
//	@RequestMapping("/turninsert.do")
//	@ResponseBody
//	public void add(Turn turn) throws Exception {
//		System.out.println(turn.toString());
//		service.turnAdd(turn);
//	}
//
//	@RequestMapping("/turndelete.do")
//	public String delete(int curriNo, int turnNo) throws Exception {
//		service.turnRemove(turnNo);
//		return "redirect:/turn/turnlist.do?curriNo=" + curriNo;
//	}
//
//	@RequestMapping("/turnupdate.do")
//	@ResponseBody
//	public void update(Turn turn) throws Exception {
//		System.out.println(turn.toString());
//		if (turn.getDescription() != null) {
//			service.turnEdit(turn); // 내용바꾸기
//		} else if (turn.getStatus() != null) {
//			service.turnEditStatus(turn); // 상태 바꾸기
//		} else if (turn.getYear() != null) {
//			service.turnEditDate(turn); // 평가일 바꾸기
//		} else if (turn.getUnderstand() != null) {
//			service.turnEditUnderstand(turn); // 이해도 바꾸기
//		}
//	}
//	
//	//////////////////////////////////////////////////////////
//	// 통계
//	//////////////////////////////////////////////////////////
//	@RequestMapping("/turnsummary.do")
//	@ResponseBody
//	public Map<String, Object> sum(Turn turn) throws Exception {
//		Map<String, Object> map = new HashMap<>();
//		map.put("statusSummary", service.statusSummary(turn));
//		map.put("understandSummary", service.understandSummary(turn));
//		return map;
//	}
//
//	@RequestMapping("/studentsummary.do")
//	@ResponseBody
//	public Map<String, Object> sumStudent(Turn turn) throws Exception {
//		Map<String, Object> map = new HashMap<>();
//		map.put("attendanceSummary", service.attendanceSummary(turn));
//		map.put("feedbackSummary", service.feedbackSummary(turn));
//		return map;
//	}
//	
//	//////////////////////////////////////////////////////////
//	// 피드백 & 출첵
//	//////////////////////////////////////////////////////////
//
//	@RequestMapping("/student.do")
//	public ModelAndView studentList(Turn turn) throws Exception {
//		ModelAndView mav = new ModelAndView("turn/student");
//
//		Curri curri = curriService.curriDetail(turn.getCurriNo());
//		Turn t = service.turnDetail(turn);;
//		List<Student> students = service.StudentList(turn);
//		
//		mav.addObject("students", students);
//		mav.addObject("studentsJson", new Gson().toJson(students));
//		mav.addObject("turn", t);
//		mav.addObject("curri", curri);
//		return mav;
//
//	}
//
//	@RequestMapping("/studentdetail.do")
//	@ResponseBody
//	public Student stDetail(Student student) throws Exception {
//		System.out.println(service.studentDetail(student).toString());
//	
//		Student s = service.studentDetail(student);
//		s.setBestMemberName(service.bestMemberName(s.getBestMember()));
//		return s;
//	}
	
	
}
