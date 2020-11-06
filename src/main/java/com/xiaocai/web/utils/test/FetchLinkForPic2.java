package com.xiaocai.web.utils.test;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.xiaocai.web.utils.common.NetUtils;

public class FetchLinkForPic2 {

	static String url = "http://djc027.com/html/part/index49.html";
	public static void main(String[] args) {
		
		analsysPage(url);
		hisurl.clear();
		for(String hurl : pageList){
			System.out.println(" hurl-->"+hurl);
		}	
		System.out.println("pageList size :"+pageList.size());
		for(String pageurl : pageList){
			analsyslink(pageurl);
		}
		hisurl.clear();
		for(String link : linkList){
			System.out.println(" link-->"+link);
		}
		System.out.println("linkList size :"+linkList.size());
		/*
		for(String link : linkList){
			//分页取全部的图片链接
			analsysImgAll(link);
		}
		hisurl.clear();
		for(String src : picList){
			System.out.println(" src-->"+src);
		}*/
	}
	
	static List<String> hisurl = new ArrayList<String>();
	static List<String> pageList = new ArrayList<String>();
	static List<String> linkList = new ArrayList<String>();
	static List<String> picList = new ArrayList<String>();
	
	private static void analsysPage(String url){
		
		if(hisurl.contains(url)){
			return;
		}
		Document doc = NetUtils.getDocument(url);
		if(doc==null){
			System.out.println("doc is null "+url);
			return;
		}
		hisurl.add(url);
		System.out.println("analsysPage hisurl :"+hisurl.size());
		String tag = "body .page a";
		String attr = "abs:href";
		String herfcontent = "part/index49";
		Elements elemens = doc.select(tag);
		for(Element e : elemens){
			String href = e.attr(attr);
			
			if(!href.contains(herfcontent)){
				continue;
			}
			//System.out.println("页面page :"+ href);
			if(!pageList.contains(href)){
				pageList.add(href);
			}
			analsysPage(href);
		}
	}
	
	private static void analsyslink(String url){
		
		if(hisurl.contains(url)){
			return;
		}
		Document doc = NetUtils.getDocument(url);
		if(doc==null){
			System.out.println("doc is null "+url);
			return;
		}
		hisurl.add(url);
		System.out.println("analsyslink hisurl :"+hisurl.size());
		String tag = "body .main a";
		String attr = "abs:href";
		Elements elemens = doc.select(tag);
		String linkcontent = "article/index";
		for(Element e : elemens){
			
			String link = e.attr(attr);
			if(link.equals(url)){
				continue;
			}
			if(!link.contains(linkcontent)){
				continue;
			}
			//System.out.println("图片页面链接："+ link);
			//analsysImg(link);
			if(!linkList.contains(link+","+e.text())){
				linkList.add(link+","+e.text());
			}
			analsyslink(link);
		}
	}
	
	/**
	 * 抓取指定页面图片，不含其他分页
	 * @param url
	 */
	private static void analsysImg(String url){
		if(picList.contains(url)){
			System.out.println("picList :"+picList.size());
			return;
		}
		Document doc = NetUtils.getDocument(url);
		if(doc==null){
			System.out.println("doc is null "+url);
			return;
		}
		String tag = "body img";
		String attr = "abs:src";
		Elements elemens = doc.select(tag);
		for(Element e : elemens){
			String src = e.attr(attr);
			if(!picList.contains(src)){
				picList.add(src);
			}
		}
	}
	
	private static void analsysImgAll(String url){

		if(hisurl.contains(url)){
			System.out.println("hisurl :"+hisurl.size());
			return;
		}
		//处理当前页
		analsysImg(url);
		
		Document doc = NetUtils.getDocument(url);
		if(doc==null){
			System.out.println("doc is null "+url);
			return;
		}
		hisurl.add(url);
		//找分页
		String hrefTag = "a";
		String hrefAttr = "abs:href";
		Elements tmps = doc.select(hrefTag);
		String piclinkcontent = "arthtml/52607";
		for(Element e : tmps){
			String href = e.attr(hrefAttr);
			if(href.equals(url)){
				continue;
			}
			if(!href.contains(piclinkcontent)){
				continue;
			}
			
			if(hisurl.contains(href)){
				continue;
			}
			System.out.println( " tmp href："+href);
			analsysImgAll(href);
		}
	}
	
}

