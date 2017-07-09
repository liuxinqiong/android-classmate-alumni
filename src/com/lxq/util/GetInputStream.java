package com.lxq.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetInputStream {
	public static InputStream getInputStream(String urlStr){
		URL url=null;
		HttpURLConnection conn=null;
		InputStream in=null;
		
		try {
			url=new URL(urlStr);
			conn=(HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(10000);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("accept", "*/*");
			conn.getRequestProperty("location");
			conn.getResponseCode();
			conn.connect();
			in=conn.getInputStream();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return in;
	}
}
