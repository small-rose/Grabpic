package com.xiaocai.web.utils.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.xiaocai.web.utils.common.ConstantUtils;
import com.xiaocai.web.utils.common.NetUtils;

public class StringTest2 {

	static String url = "http://www.17sucai.com/preview/811421/2017-09-12/%E4%BB%BFqq%E7%A9%BA%E9%97%B4%E7%95%99%E8%A8%80%E6%9D%BF/demo.html";
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		url="http://cxly.video/vodplay/111626-1-1.html";
		
		//url="http://adminlte.la998.com/pages/UI/icons.html";
		
		Document doc = NetUtils.getDocument(url);
		
		System.out.println(doc);
		
		Elements elemens = doc.select("link");
		for(Element e : elemens){
			
			String link = e.attr("abs:href");
			System.out.println(link);
		}
		
		Elements imgs = doc.select("img");
		for(Element e : imgs){
			
			//String imgSrc = e.attr("abs:src");
			//System.out.println(imgSrc);
			//String name = imgSrc.substring(imgSrc.lastIndexOf("/"));
			//downImage(imgSrc,name);
		}
		
		String imgSrc = "http://cxly.video/template/datll_wapian//images/p3.jpg";
		downImage(imgSrc , "p3.jpg");
		
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
