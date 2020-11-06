package com.xiaocai.web.utils.common;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.xiaocai.web.utils.websiteUtil.ConstanLinkKeys;

import sun.reflect.Reflection;
@SuppressWarnings("restriction")
public class Log4JUtils {

	private static Logger logger =  null;  
	  
    @SuppressWarnings("restriction")
	public static Logger getLogger(){  
       if (null == logger){  
            //Java8 废弃了Reflection.getCallerClass()  
           logger = LogManager.getLogger(Reflection.getCallerClass());  
           logger.debug("调用者类名"+Reflection.getCallerClass().getName());  
       }  
        return logger;  
    }
    
    public static void toLog(String info){
	   PrintStream out;
	    try {
	    	File file = new File(ConstanLinkKeys.LOG_LOACATION);
	    	if(!file.exists()){
	    		file.mkdirs();
	    	}
	    	String fileName = DateUtils.date2Str(new SimpleDateFormat("yyyyMMddHHmm"))+"_Systemout.txt";
	    	File f2 = new File(file.getAbsolutePath()+"/"+fileName);
	    	System.out.println(" "+f2.getAbsolutePath());
	    	out = new PrintStream(f2.getAbsolutePath());
	    	System.setOut(out);
		    System.out.println(info);
	    } catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    }  
   }
    
    public static void toLog(){
 	   PrintStream out;
 	    try {
 	    	File file = new File(ConstanLinkKeys.LOG_LOACATION);
 	    	if(!file.exists()){
 	    		file.mkdirs();
 	    	}
 	    	String fileName = DateUtils.date2Str(new SimpleDateFormat("yyyyMMddHHmm"))+"_Systemout.txt";
	    	File f2 = new File(file.getAbsolutePath()+"/"+fileName);
 	    	out = new PrintStream(f2.getAbsolutePath());
 	    	System.setOut(out);
 	    } catch (FileNotFoundException e) {
 	    	e.printStackTrace();
 	    }  
    }
    
    public static void toLogFile(String filepath){
  	   PrintStream out;
  	    try {
 	    	File f2 = new File(filepath);
  	    	out = new PrintStream(f2.getAbsolutePath());
  	    	System.setOut(out);
  	    } catch (FileNotFoundException e) {
  	    	e.printStackTrace();
  	    }  
     }
    public static void main(String[] args) {
		toLog("test");
	}
}
