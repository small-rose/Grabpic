package com.xiaocai.web.utils.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.util.StringUtils;

/**
 *fetch pic
 * 
 * @author zzy
 * 
 */
public class NetUtils {
	/**
	 * GET
	 * 
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		try {
			conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("GET");
			conn.setReadTimeout(5000);
			conn.setDoInput(true);
			conn.connect();

			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} catch (Exception e) {
			System.err.println(String.format("%s not connect ", url));
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return null;
	}

	/**
	 * html
	 * 
	 * @param reffere
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static Document getDocument(String url) {
		Document doc = null;
		try{
			//System.out.println("get : url : "+url);
			trustEveryone();
			doc = Jsoup.connect(url).timeout(15000).userAgent(ConstantUtils.getAgent()).get();
			return doc;
		}catch (HttpStatusException e) {
			// TODO: handle exception
			int code = e.getStatusCode();
			if(code ==404){
				return doc;
			}
		}catch (SocketTimeoutException e) {
			// TODO: handle exception
			try {
				doc = Jsoup.connect(url).timeout(15000).userAgent(ConstantUtils.getAgent()).get();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return doc;
		}
		catch (UnknownHostException e) {
			// TODO: handle exception
			try {
				doc = Jsoup.connect(url).timeout(15000).userAgent(ConstantUtils.getAgent()).get();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return doc;
		}
		catch(IOException e){
			System.out.println("IOException : url : "+url);

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return doc;
	}

	/**
	 *html
	 * 
	 * @param reffere
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static Document getDocumentPost(String url) throws IOException {
		trustEveryone();
		Document doc = Jsoup.connect(url).timeout(10000).userAgent(ConstantUtils.getAgent()).post();
		return doc;
	}
	

	private static String filePath = "F:\\pic_html\\";
	
	/** downloadImg
	 * @param url
	 * @param params
	 * @return imgpath
	 */
	public static String  downloadImgByNet(String imgSrc, String title) {
		String result = null;
		try{
			URL url = new URL(imgSrc);
			URLConnection conn = url.openConnection();
			//设置超时
			conn.setConnectTimeout(10*1000);
			//设置代理
			//conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			conn.setRequestProperty("User-Agent", ConstantUtils.getAgent());
			//获取响应
			InputStream str = conn.getInputStream();
			
			String fileName = imgSrc.substring(imgSrc.lastIndexOf("/"), imgSrc.length()-5);
			if(!StringUtils.isEmpty(title)){
				fileName = title + fileName.substring(fileName.lastIndexOf("."));
			}
			System.out.println("fileName:"+fileName);
			//缓冲区
			byte[] bs = new byte[1024];
			//长度
			int len = 0;

			//保存文件到本地
			File saveDir = new File(filePath);  
			if(!saveDir.exists()){  
			    saveDir.mkdir();  
			}  
			File file = new File(saveDir+File.separator+fileName); 
			System.out.println("filepath : "+file.getAbsolutePath());
			result = file.getAbsolutePath();
			if(file.exists()) {
				System.out.println("文件已经存在");
				return result;
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

			System.out.println("down pic success");
			result = file.getAbsolutePath();
			return result;
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("down pic failed");
		}

		return result;
	}


	public static void trustEveryone() {   
        try {    
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {    
                public boolean verify(String hostname, SSLSession session) {    
                    return true;    
                }    
            });    
    
            SSLContext context = SSLContext.getInstance("TLS");  
            SSLContext sc = SSLContext.getInstance("SSL");
            context.init(null, new X509TrustManager[] { new X509TrustManager() {    
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {    
                }    
    
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {    
                }    
    
                public X509Certificate[] getAcceptedIssuers() {    
                    return new X509Certificate[0];    
                }    
            } }, new SecureRandom());    
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());    
            //HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());    
        } catch (Exception e) {    
            e.printStackTrace();    
        }    
    }    
}
