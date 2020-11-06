package com.xiaocai.web.utils.test;

import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import com.xiaocai.web.utils.common.NetUtils;
import com.xiaocai.web.utils.picUtils.SavePicUtil;

public class FetchUtilTest {
	final static String reg = "/.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/";
	static Pattern pattern = Pattern.compile(reg) ;
	
	
	public static Document  fetchPicsByUrl(String url){
		Document doc = null;
		try {
			doc = NetUtils.getDocument(url);
			if(doc==null){
				doc = NetUtils.getDocument(url);
			}
			if(doc==null){
				System.out.println(" FetchUtil.fetchPicsByUrl doc is null ");
			}
			System.out.println(doc.toString());
			return doc;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
	
	private static void analysisDoc(Document doc) throws Exception{
		Elements ptypes = doc.select("div a");
		
		for(Element ptype :ptypes){
			System.out.println("ptype : --> "+ptype);
			String webaddr = ptype.attr("abs:href");
			String imgType = ptype.text();
			System.out.println("analysisDoc abs:href :"+webaddr+" --> "+imgType+" ");

			if(StringUtils.isEmpty(webaddr)){
				continue;
			}
			analysisDocHref(webaddr);
		}
		
		Elements links = doc.select("div img");
		String linkName = "";
		for (Element link : links) {
			  String linkHref = link.attr("alt src");
			  linkName = link.text();
			  System.out.println("img alt src :"+linkName+" --> "+linkHref+" ");
			  
			  //analysisImg(linkHref,linkName);
		}
	}
	
	private static void analysisDocHref(String webaddr) throws Exception{
		
		Document doc = NetUtils.getDocument(webaddr);
		System.out.println("======================"+webaddr);
		System.out.println("======================"+doc);

		Elements links = doc.select("table tbody a");
		String linkName = "";
		for (Element link : links) {
			  System.out.println("href link  --> "+link+" ");
			  String linkHref = link.attr("abs:href");
			  linkName = link.text();
			  System.out.println("href link page:"+linkName+" --> "+linkHref+" ");
			  
			  analysisImg(linkHref,linkName);
		}
		
		
	}
	
	private static void analysisImg(String webaddr,String linkName) throws Exception {
		Document doc = NetUtils.getDocument(webaddr);
		if(doc!=null ){
			Elements imglinks = doc.select("div img");
			for (Element link : imglinks) {
				
			  String linkHref = link.attr("abs:src");
			  String suffix = linkHref.substring(linkHref.length()-4, linkHref.length()-1);
			  
			  System.out.println("img link jpg:"+linkName+" --> "+linkHref+" ");
			  
			  forsave(linkHref,linkName);
			}
		}
		
	}
	
	private static void  forsave(String linkHref, String fileName) throws Exception{
		String sufffix = linkHref.substring(linkHref.lastIndexOf("."));
		fileName = linkHref.substring(linkHref.lastIndexOf("/"));
		String filePath = SavePicUtil.save(linkHref, fileName, sufffix);
		
	}
	
	public static void main(String[] args) throws Exception {
		String url = "http://www.aisiren.com/meitui/taotu/201709/20170924214734.html";
		 Document doc = fetchPicsByUrl( url);
		 analysisDoc(doc);
	}
}
