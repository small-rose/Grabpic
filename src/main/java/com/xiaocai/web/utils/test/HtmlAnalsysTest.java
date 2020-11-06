package com.xiaocai.web.utils.test;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.xiaocai.web.utils.common.NetUtils;

public class HtmlAnalsysTest {

	static String url = "http://www.mmkao.org/NAKED-ART/";
	public static void main(String[] args) {
		
		//analsysPage(url);
		for(String hurl : pageList){
			System.out.println(" hurl-->"+hurl);
		}
		url = "https://www.ninilao.com/arthtml/52607.html";
		analsyslink(url);
		for(String link : linkList){
			System.out.println(" link-->"+link);
		}
		
//		url = "https://www.ninilao.com/arthtml/52609.html";
//
//		Document doc = NetUtils.getDocument(url);
//		System.out.println(doc.toString());
//		String tag = "body img";
//		Elements elemens = doc.select(tag);
//		for(Element e : elemens){
//			System.out.println(e.toString());
//		}
		
		url = "https://www.ninilao.com/arthtml/52607.html";
		
//		analsysImg(url);
//		for(String src : picList){
//			System.out.println(" src-->"+src);
//		}
		
		//分页取全部的图片链接
		//analsysImgAll(url);
		for(String link : linkList){
			System.out.println(" link-->"+link);
		}
		
		for(String src : picList){
			System.out.println(" src-->"+src);
		}
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
		System.out.println("hisurl :"+hisurl.size());
		String tag = "body .page a";
		String attr = "abs:href";
		String herfcontent = "NAKED-ART";
		//System.out.println(doc);
		Elements elemens = doc.select(tag);
		for(Element e : elemens){
			String href = e.attr(attr);
			
			if(!href.contains(herfcontent)){
				continue;
			}
			if(!pageList.contains(href)){
				pageList.add(href);
			}
			System.out.println("页面page :"+ href);
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
		System.out.println("hisurl :"+hisurl.size());
		String tag = ".k_list-1a a";
		String attr = "abs:href";
		Elements elemens = doc.select(tag);
		String linkcontent = "arthtml";
		for(Element e : elemens){
			
			String link = e.attr(attr);
			if(link.equals(url)){
				continue;
			}
			if(!link.contains(linkcontent)){
				continue;
			}
			if(!linkList.contains(link)){
				linkList.add(link);
			}
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
