package com.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class StringToImage {
	private static final BASE64Decoder decoder = new BASE64Decoder();
	
	public static void base64StringToImage(String base64String, String picName, String path){      
        try {      
            byte[] bytes1 = decoder.decodeBuffer(base64String);      
	       
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);      
	        BufferedImage bi1 =ImageIO.read(bais);
	        
	        File w2 = new File(path + picName); 
            ImageIO.write(bi1, "jpg", w2);
        } catch (IOException e) {      
        	e.printStackTrace();      
	    }      
	}
	
	public static String GetImageStr(String imgFilePath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
		byte[] data = null;  
		  
		// 读取图片字节数组  
		try {  
		InputStream in = new FileInputStream(imgFilePath);  
		data = new byte[in.available()];  
		in.read(data);  
		in.close();  
		} catch (IOException e) {  
		e.printStackTrace();  
		}  
		  
		// 对字节数组Base64编码  
		BASE64Encoder encoder = new BASE64Encoder();  
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串  
	}  
}
