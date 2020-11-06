package com.xiaocai.web.utils.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.xiaocai.web.utils.common.ConstantUtils;

public class StringTest {

	static String url = "http://www.jsdaima.com/Uploads/js/201901/1547084462/index.html";
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String a = "10-1.html";
		String b = "10-2.html";
		//System.out.println(a.compareTo(b));
		//url="http://www.jsdaima.com/Uploads/js/201901/1547084462/css/www.jsdaima.com.css";
		
		//url="http://adminlte.la998.com/pages/UI/icons.html";
		/*
		 * Document doc = NetUtils.getDocument(url);
		
		System.out.println(doc);
		
		Elements elemens = doc.select("link");
		for(Element e : elemens){
			
			String link = e.attr("abs:href");
			System.out.println(link);
		}
		
		Elements imgs = doc.select("img");
		for(Element e : imgs){
			
			String imgSrc = e.attr("abs:src");
			System.out.println(imgSrc);
			String name = imgSrc.substring(imgSrc.lastIndexOf("/"));
			downImage(imgSrc,name);
		}*/
		
		String src = "http://www.jsdaima.com/Uploads/js/201901/1547084462/images/bg.jpg";
		
		
		downImage(src,"bg.jpg");
	}

	
	static void downImage(String imgSrc,String fileName){
		try{
			URL url = new URL(imgSrc);
			URLConnection conn = url.openConnection();
			//设置超时
			conn.setConnectTimeout(10*1000);
			//设置代理
			conn.setRequestProperty("User-Agent", ConstantUtils.getAgent());
			//获取响应
			InputStream str = conn.getInputStream();
			
			//缓冲区
			byte[] bs = new byte[1024];
			//长度
			int len = 0;
			String filePath = "E:\\工作相关\\blog\\模仿说说\\images\\";
			//保存文件到本地
			File saveDir = new File(filePath);  
			if(!saveDir.exists()){  
			    saveDir.mkdir();  
			}  
			File file = new File(saveDir+File.separator+fileName); 
			System.out.println("filepath : "+file.getAbsolutePath());
			String result = file.getAbsolutePath();
			if(file.exists()) {
				System.out.println("文件已经存在");
				return ;
			}
			//文件输出流
			FileOutputStream out = new FileOutputStream(file);
			
			while ((len = str.read(bs)) != -1) {
				out.write(bs, 0, len);   
			}
			
			//刷新缓存
			out.flush();
			//关闭流
			out.close();
			str.close();
			System.out.println("result :"+result);
		}catch(Exception e){
			
		}
		
	}
}
