package com.cloud.tree.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.login.service.LoginService;
import com.cloud.repository.vo.Member;
import com.cloud.repository.vo.Tree;

@Controller
@RequestMapping("/cloud")
public class CloudController {

	@Autowired
	LoginService service;
	
	long folderSize(File f) {		
		long length = 0;
		for (File file : f.listFiles()) {
			if(file.isFile()) {
				length += file.length();
			}else {
				length += folderSize(file);
			}
		}
		return length;
	}
	
	
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
    
	@RequestMapping("/cloud.do")
	public void cloud() throws Exception {}
	
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
	public Tree rootTree(HttpSession session) throws Exception {
		
		Member m = (Member) session.getAttribute("user");
		String user = m.getId();
		
		String path = "cloud/"+user;
		
		File f = new File(path);
		if(!f.exists()) {
			f.mkdirs();
			System.out.println("new"+user);
		}
		//폴더 만들기
		
		List<Tree> trees = pullFile(path);
		Tree tree = new Tree();
		
		Date updateDate = new Date(f.lastModified());
		
		System.out.println(trees.toString());
		tree.setMaxSize(m.getMaxVolume());
		tree.setSize(folderSize(f));
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
	public Map<String, Object> fileRename(String path, String rename, String nowHost) throws Exception {
		
		System.out.println(nowHost);
		
		String slash = "/";
		if(nowHost.equals("localhost")) {
			slash = "\\";
		}
		
		Map<String, Object> map = new HashMap<>();
		File f = new File(path);
		Path file = Paths.get(path);

		String ext = "";
		System.out.println("옮길거 : "+ path);
		
		if(f.isDirectory()) {
			path = path.substring(0, path.lastIndexOf(slash)+1)+rename;
		}else {
			if(f.getName().lastIndexOf(".")!=-1) {
				ext = f.getName().substring(f.getName().lastIndexOf("."));
			}
			path = path.substring(0, path.lastIndexOf(slash)+1)+rename+ext;
		}

		System.out.println("바뀐것 : "+ path);
		
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
	
	@RequestMapping("/sendForm.do")
	public void sendForm() throws Exception {}
	
	// 아이디 체크
		@RequestMapping("/smsSend.do")
		public void sendSms(HttpServletRequest request, HttpServletResponse response) throws Exception {
			String charsetType = "UTF-8"; //EUC-KR 또는 UTF-8

		    request.setCharacterEncoding(charsetType);
		    response.setCharacterEncoding(charsetType);
		    String  action     = nullcheck(request.getParameter("action"), "");
		    if(action.equals("go")) {

		        String sms_url = "";
		        sms_url = "https://sslsms.cafe24.com/sms_sender.php"; // SMS 전송요청 URL
		        String user_id = base64Encode("cv613"); // SMS아이디
		        String secure = base64Encode("da71acb9982e99fc6bb47827703f32f6");//인증키
		        String msg = base64Encode(nullcheck(request.getParameter("msg"), ""));
		        String rphone = base64Encode(nullcheck(request.getParameter("rphone"), ""));
		        String sphone1 = base64Encode(nullcheck(request.getParameter("sphone1"), ""));
		        String sphone2 = base64Encode(nullcheck(request.getParameter("sphone2"), ""));
		        String sphone3 = base64Encode(nullcheck(request.getParameter("sphone3"), ""));
		        String rdate = base64Encode(nullcheck(request.getParameter("rdate"), ""));
		        String rtime = base64Encode(nullcheck(request.getParameter("rtime"), ""));
		        String mode = base64Encode("1");
		        String subject = "";
		        if(nullcheck(request.getParameter("smsType"), "").equals("L")) {
		            subject = base64Encode(nullcheck(request.getParameter("subject"), ""));
		        }
		        String testflag = base64Encode(nullcheck(request.getParameter("testflag"), ""));
		        String destination = base64Encode(nullcheck(request.getParameter("destination"), ""));
		        String repeatFlag = base64Encode(nullcheck(request.getParameter("repeatFlag"), ""));
		        String repeatNum = base64Encode(nullcheck(request.getParameter("repeatNum"), ""));
		        String repeatTime = base64Encode(nullcheck(request.getParameter("repeatTime"), ""));
		        String returnurl = nullcheck(request.getParameter("returnurl"), "");
		        String nointeractive = nullcheck(request.getParameter("nointeractive"), "");
		        String smsType = base64Encode(nullcheck(request.getParameter("smsType"), ""));

		        String[] host_info = sms_url.split("/");
		        String host = host_info[2];
		        String path = "/" + host_info[3];
		        int port = 80;

		        // 데이터 맵핑 변수 정의
		        String arrKey[]
		            = new String[] {"user_id","secure","msg", "rphone","sphone1","sphone2","sphone3","rdate","rtime"
		                        ,"mode","testflag","destination","repeatFlag","repeatNum", "repeatTime", "smsType", "subject"};
		        String valKey[]= new String[arrKey.length] ;
		        valKey[0] = user_id;
		        valKey[1] = secure;
		        valKey[2] = msg;
		        valKey[3] = rphone;
		        valKey[4] = sphone1;
		        valKey[5] = sphone2;
		        valKey[6] = sphone3;
		        valKey[7] = rdate;
		        valKey[8] = rtime;
		        valKey[9] = mode;
		        valKey[10] = testflag;
		        valKey[11] = destination;
		        valKey[12] = repeatFlag;
		        valKey[13] = repeatNum;
		        valKey[14] = repeatTime;
		        valKey[15] = smsType;
		        valKey[16] = subject;

		        String boundary = "";
		        Random rnd = new Random();
		        String rndKey = Integer.toString(rnd.nextInt(32000));
		        MessageDigest md = MessageDigest.getInstance("MD5");
		        byte[] bytData = rndKey.getBytes();
		        md.update(bytData);
		        byte[] digest = md.digest();
		        for(int i =0;i<digest.length;i++)
		        {
		            boundary = boundary + Integer.toHexString(digest[i] & 0xFF);
		        }
		        boundary = "---------------------"+boundary.substring(0,11);

		        // 본문 생성
		        String data = "";
		        String index = "";
		        String value = "";
		        for (int i=0;i<arrKey.length; i++)
		        {
		            index =  arrKey[i];
		            value = valKey[i];
		            data +="--"+boundary+"\r\n";
		            data += "Content-Disposition: form-data; name=\""+index+"\"\r\n";
		            data += "\r\n"+value+"\r\n";
		            data +="--"+boundary+"\r\n";
		        }

		        //out.println(data);

		        InetAddress addr = InetAddress.getByName(host);
		        Socket socket = new Socket(host, port);
		        // 헤더 전송
		        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), charsetType));
		        wr.write("POST "+path+" HTTP/1.0\r\n");
		        wr.write("Content-Length: "+data.length()+"\r\n");
		        wr.write("Content-type: multipart/form-data, boundary="+boundary+"\r\n");
		        wr.write("\r\n");

		        // 데이터 전송
		        wr.write(data);
		        wr.flush();

		        // 결과값 얻기
		        BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream(),charsetType));
		        String line;
		        String alert = "";
		        ArrayList tmpArr = new ArrayList();
		        while ((line = rd.readLine()) != null) {
		            tmpArr.add(line);
		        }
		        wr.close();
		        rd.close();

		        String tmpMsg = (String)tmpArr.get(tmpArr.size()-1);
		        String[] rMsg = tmpMsg.split(",");
		        String Result= rMsg[0]; //발송결과
		        String Count= ""; //잔여건수
		        if(rMsg.length>1) {Count= rMsg[1]; }

		                        //발송결과 알림
		        if(Result.equals("success")) {
		            alert = "성공적으로 발송하였습니다.";
		            alert += " 잔여건수는 "+ Count+"건 입니다.";
		        }
		        else if(Result.equals("reserved")) {
		            alert = "성공적으로 예약되었습니다";
		            alert += " 잔여건수는 "+ Count+"건 입니다.";
		        }
		        else if(Result.equals("3205")) {
		            alert = "잘못된 번호형식입니다.";
		        }
		        else {
		            alert = "[Error]"+Result;
		        }

		        System.out.println(nointeractive);
		        
		        if(nointeractive.equals("1") && !(Result.equals("Test Success!")) && !(Result.equals("success")) && !(Result.equals("reserved")) ) {
		            System.out.println("<script>alert('" + alert + "')</script>");
		        }
		        else if(!(nointeractive.equals("1"))) {
		            System.out.println("<script>alert('" + alert + "')</script>");
		        }


		        System.out.println("<script>location.href='"+returnurl+"';</script>");
		    }
		}
		
	    public static String nullcheck(String str,  String Defaultvalue ) throws Exception
	    {
	         String ReturnDefault = "" ;
	         if (str == null)
	         {
	             ReturnDefault =  Defaultvalue ;
	         }
	         else if (str == "" )
	        {
	             ReturnDefault =  Defaultvalue ;
	         }
	         else
	         {
	                     ReturnDefault = str ;
	         }
	          return ReturnDefault ;
	    }
	    /**
	    * BASE64 Encoder
	    * @param str
	    * @return
	    */
	   public static String base64Encode(String str)  throws java.io.IOException {
	       sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
	       byte[] strByte = str.getBytes();
	       String result = encoder.encode(strByte);
	       return result ;
	   }

	   /**
	    * BASE64 Decoder
	    * @param str
	    * @return
	    */
	   public static String base64Decode(String str)  throws java.io.IOException {
	       sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
	       byte[] strByte = decoder.decodeBuffer(str);
	       String result = new String(strByte);
	       return result ;
	   }

	
}
