package com.xiaocai.web.utils.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.xiaocai.web.utils.picUtils.DownImages;

public class DownImageTest {

	static String PATH = "F:\\pic_html\\images\\";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DownByFiles(PATH);
	}

	public static void  DownByFiles(String filepath) {
		try {
			File file = new File(filepath);
			if (file.isDirectory()) {
				System.out.println("文件夹");
				String[] filelist = file.list();
				System.out.println("读取到文件数量是：" + filelist.length);
				String fileName = "000";
				for (int i = 0; i < filelist.length; i++) {
					
					File readfile = new File(filepath + "\\" + filelist[i]);
					fileName = filelist[i].replace(".txt", "");
					readfile(readfile,fileName);
				}
			}
		} catch (Exception e) {
			System.out.println("readfile()   Exception:" + e.getMessage());
		}
	}
	
	
	private static void readfile(File readfile,String fileName) {
		
		System.out.println("File path "+readfile.getAbsolutePath());
        try{
            BufferedReader br = new BufferedReader(new FileReader(readfile));//构造一个BufferedReader类来读取文件
            String strline = null;
            String imgSrc ;
            String name = "111";
            while((strline = br.readLine())!=null){//使用readLine方法，一次读一行
                if(strline.trim()!=null || strline.trim()!=""){
                	if(!strline.contains("src")){
                		continue ;
                	}else{
                		imgSrc =  strline.replace("src-->", "");
                		name = imgSrc.substring(imgSrc.lastIndexOf("/")+1);
                		DownImages.down(PATH+fileName,name, imgSrc, null, "");
                	}
                }
            }
            br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }	
	}
}
