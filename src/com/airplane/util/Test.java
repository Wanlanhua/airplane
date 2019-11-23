package com.airplane.util;

import java.io.IOException;

import com.airplane.view.BASE64DecodedMultipartFile;

import sun.misc.BASE64Decoder;

public class Test {
	
	
	public static BASE64DecodedMultipartFile uploudImg(String file){
		try {
	        String[] baseStrs = file.split(",");

	        BASE64Decoder decoder = new BASE64Decoder();
	        byte[] b = new byte[0];
	        b = decoder.decodeBuffer(baseStrs[1]);

	        for(int i = 0; i < b.length; ++i) {
	            if (b[i] < 0) {
	                b[i] += 256;
	            }
	        }

	        return new BASE64DecodedMultipartFile(b, baseStrs[0]);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	
		
	}
}
