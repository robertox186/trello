package controllers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class cripto {
private MessageDigest md ;
private byte[] b; 
private String p;
public cripto(){
	try {
		md=MessageDigest.getInstance("MD5");
		
		
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}

public String getPass(String p){
	md.update(p.getBytes());
	b=md.digest();
	BigInteger number = new BigInteger(1, b);
	p = number.toString(16);
	return p;	
}
}
