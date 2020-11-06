package com.xiaocai.web.utils.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.xiaocai.web.utils.common.ConstantUtils;
import com.xiaocai.web.utils.common.DateUtils;

public class SimpleFetchTest {

    // 地址
    private static final String URL = "http://djc027.com/html/article/index8349.html";
    // 获取a标签正则
    private static final String AURL_REG = "<a.*src=(.*?)[^>]*?>(.*?)</a>";
    // 获取img标签正则
    private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
    // 获取src路径的正则
    private static final String IMGSRC_REG = "[a-zA-z]+://[^\\s]*";
    
    // 获取文件保存路径
    private static final String IMG_PATH = "F:\\pic_html\\SimpleFetchTest\\";
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			SimpleFetchTest cm=new SimpleFetchTest();
            //获得html文本内容
            String HTML = cm.getHtml(URL);
            //获取A标签
           //List<String> AUrl = cm.getTagAUrl(HTML);
            //cm.fetch(cm,AUrl);
            //获取图片标签
            List<String> imgUrl = cm.getImageUrl(HTML);
            //获取图片src地址
            List<String> imgSrc = cm.getImageSrc(imgUrl);
            //下载图片
            cm.Download(imgSrc);

        }catch (Exception e){
            System.out.println("发生错误");
        }

	}
	
	 

	//获取HTML内容
    private String getHtml(String url)throws Exception{
        URL url1=new URL(url);
        URLConnection connection=url1.openConnection();
        InputStream in=connection.getInputStream();
        InputStreamReader isr=new InputStreamReader(in);
        BufferedReader br=new BufferedReader(isr);

        String line;
        StringBuffer sb=new StringBuffer();
        while((line=br.readLine())!=null){
            sb.append(line,0,line.length());
            sb.append('\n');
        }
        br.close();
        isr.close();
        in.close();
        return sb.toString();
    }
   
	//获取ImageUrl地址
    private List<String> getImageUrl(String html){
        Matcher matcher=Pattern.compile(IMGURL_REG).matcher(html);
        List<String>listimgurl=new ArrayList<String>();
        while (matcher.find()){
            listimgurl.add(matcher.group());
        }
        return listimgurl;
    }

	//获取ImageSrc地址
    private List<String> getImageSrc(List<String> listimageurl){
        List<String> listImageSrc=new ArrayList<String>();
        for (String image:listimageurl){
            Matcher matcher=Pattern.compile(IMGSRC_REG).matcher(image);
            while (matcher.find()){
                listImageSrc.add(matcher.group().substring(0, matcher.group().length()));
            }
        }
        return listImageSrc;
    }
    
	//下载图片
    private void Download(List<String> listImgSrc) {
        try {
            //开始时间
            Date begindate = new Date();
            for (String url : listImgSrc) {
                //开始时间
                Date begindate2 = new Date();
                String imageName = url.substring(url.lastIndexOf("/") + 1, url.length());
                try{
	                URL uri = new URL(url);
	                URLConnection conn = uri.openConnection();
	    			//设置超时
	    			conn.setConnectTimeout(10*1000);
	    			//设置代理
	    			
	    			conn.setRequestProperty("Host","pic.lookpic.xyz");
	    			conn.setRequestProperty("Connection","keep-alive");
	    			conn.setRequestProperty("Cookie","__cfduid=d9ceb9c401ccd018070ffc7544f5cf1c31515334327");
	    			conn.setRequestProperty("User-Agent", ConstantUtils.getAgent());
	    			conn.setRequestProperty("Content-type", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;");
	    			//获取响应
	    			InputStream str = conn.getInputStream();
	                InputStream in = uri.openStream();
	                File file = new File(IMG_PATH +DateUtils.date2Str(DateUtils.date_sdf_wz));
	                if(!file.exists()){
	                	file.mkdirs();
	                }
	                FileOutputStream fo = new FileOutputStream(new File(IMG_PATH+imageName));//文件输出流
	                byte[] buf = new byte[1024];
	                int length = 0;
	                System.out.println("开始下载:" + url);
	                while ((length = in.read(buf, 0, buf.length)) != -1) {
	                    fo.write(buf, 0, length);
	                }
	                //关闭流
	                in.close();
	                fo.close();
	                Thread.sleep( 1*1000);
                }catch(Exception e){
                	e.printStackTrace();
                	continue;
                }
                System.out.println(imageName + "下载完成");
                //结束时间
                Date overdate2 = new Date();
                double time = overdate2.getTime() - begindate2.getTime();
                System.out.println("耗时：" + time / 1000 + "s");
            }
            Date overdate = new Date();
            double time = overdate.getTime() - begindate.getTime();
            System.out.println("总耗时：" + time / 1000 + "s");
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("下载失败");
        }
    }
    
    
    //获取 a 标签Url地址
    private List<String> getTagAUrl(String html){
        Matcher matcher=Pattern.compile(AURL_REG).matcher(html);
        List<String>listAurl=new ArrayList<String>();
        while (matcher.find()){
        	listAurl.add(matcher.group());
        }
        return listAurl;
    }
    
    
    private void fetch(SimpleFetchTest cm, List<String> aUrl) {
  		// TODO Auto-generated method stub
  		
    	String HTML;
		try {
			for(String tmpUrl : aUrl ){
				HTML = cm.getHtml(tmpUrl);
				//获取图片标签
		        List<String> imgUrl = cm.getImageUrl(HTML);
		        //获取图片src地址
		        List<String> imgSrc = cm.getImageSrc(imgUrl);
		        //下载图片
		        cm.Download(imgSrc);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
    }
}
