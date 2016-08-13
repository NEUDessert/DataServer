package com.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Communicate {
	private String url;
	
	public Communicate(String url){
		this.url = url;
	}
	
	public void send(String content){
		URL path;
		try {
			path = new URL(url);
			HttpURLConnection urlcon = (HttpURLConnection) path.openConnection();
			
			urlcon.setConnectTimeout(3000);
			urlcon.setRequestMethod("POST");
			urlcon.setRequestProperty("accept", "text/html, application/xhtml+xml, */*");
			urlcon.setRequestProperty("connection", "Keep-Alive");
			urlcon.setRequestProperty("accept-language", "zh-CN");
			urlcon.setRequestProperty("user-agent",
			                    "Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; LCJB; rv:11.0) like Gecko");

			urlcon.setDoOutput(true);
			PrintWriter out = new PrintWriter(urlcon.getOutputStream());
			out.print(content);
	        out.flush();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
