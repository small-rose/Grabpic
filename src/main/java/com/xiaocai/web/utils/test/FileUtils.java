package com.xiaocai.web.utils.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.jsoup.nodes.Document;

public class FileUtils {
	
	private static String roadPath = "F:/pic_html/";
	private static String COUNT_NAME = "count" ;
	/**
	 * 将网页保存成html
	 * @param doc
	 * @return
	 */
	public  static boolean saveToHtml(Document doc){

		Integer count = getCountNum();
		if(null == count){
			count = getCountNum();
			if(null == count){
				System.out.println("=====properties count is null====");
				return false ;
			}
		}
		String tmpName = "doc_"+count+".html";
		File file = new File(roadPath);
		if(!file.exists()){
			file.mkdirs();
		}
		String filePath = file.getAbsolutePath();
		if(!filePath.endsWith("/")){
			filePath += "/";
		}
		FileOutputStream out = null;   
		byte[] bt = doc.html().getBytes();
		try {
			out = new FileOutputStream(new File(filePath+tmpName));
		
			out.write(bt, 0, bt.length);
			out.close();
			System.out.println("=========");
			count = count+1 ;
			System.out.println(" count" + count +", ---"+setCountNum(""+count));
			
			if(count == setCountNum(""+count)){
				System.out.println("====properties=====");
			}
			return true;
			
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false ;
	}
	
	
	
	private static Integer getCountNum(){
		Properties prop = new Properties(); 
		
		try {
			//a.properties
			InputStream in = new BufferedInputStream (new FileInputStream("src/count.properties"));
			prop.load(in);
			String countval =  prop.getProperty(COUNT_NAME);
			in.close();
			Integer count = Integer.parseInt(countval);
			return count ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("=====properties=====exception====");
		}     
		return null ;
	}
	
	private static Integer setCountNum(String c){
		Properties prop = new Properties(); 
		
		try {
			//a.properties
			FileOutputStream oFile = new FileOutputStream("src/count.properties", true);//true��ʾ׷�Ӵ�
			prop.setProperty(COUNT_NAME, c);
			prop.store(oFile, "The New properties file");
			oFile.close();
			
			Integer count = getCountNum();
			return count ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("=====properties=====exception====");
		}     
		return 0 ;
	}


	
}
