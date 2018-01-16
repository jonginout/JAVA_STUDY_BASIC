package com.cloud.common;
 
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
	private Encryption() {}
	
	public static String getSHA512(String str) throws NoSuchAlgorithmException {
        MessageDigest sh = MessageDigest.getInstance("SHA-512");
        sh.update(str.getBytes());
        
        StringBuffer sb = new StringBuffer();
        
        for(byte b : sh.digest())
        	sb.append(Integer.toHexString(0xff & b));
        
        return sb.toString();
    }
}
