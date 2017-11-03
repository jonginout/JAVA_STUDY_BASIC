package model;

import java.io.File;

public class FileCnt {
	
	public int cnt=0;

	public int pullFile(String path) {
	    File[] list = new File(path).listFiles(); 
	    
	    try{
	        for (File ff : list) {
	        	System.out.println(ff);
	            if (ff.isFile()) {
	                cnt++;
	            }
	        }
	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    return cnt;
	}
}
