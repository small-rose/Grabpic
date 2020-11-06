package com.xiaocai.web.utils.test;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.xiaocai.web.utils.common.NetUtils;

public class HtmlAnalsysTest3 {

	static String url = "http://www.win4000.com/mt/huge.html";
	public static void main(String[] args) {
		analsysPage(url);
		for(String hurl : pageList){
			System.out.println(" hurl-->"+hurl);
		}
		analsyslink(pageList.get(0));
		
	}
	//已经抓取的用来迭代过滤
	static List<String> hisurl = new ArrayList<String>();
	//所需要分页链接集合
	static List<String> pageList = new ArrayList<String>();
	
	private static void analsysPage(String url){
		if(hisurl.contains(url)){
			System.out.println("hisurl :"+hisurl.size());
			return;
		}
		Document doc = NetUtils.getDocument(url);
		if(doc==null){
			System.out.println("doc is null "+url);
			return;
		}
		hisurl.add(url);
		String tag = "body a";
		String attr = "abs:href";
		String herfcontent = "mt/huge";//只筛选胡歌的连接
		
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
			analsysPage(href);//迭代抓取，迭代过程会自动找后后续的页面
		}
	}
	
	private static void analsyslink(String url){
		Document doc = NetUtils.getDocument(url);
		String tag = "a";
		String attr = "abs:href";
		Elements elemens = doc.select(tag);
	
		String linkcontent = "胡歌";
		for(Element e : elemens){
			
			String link = e.attr(attr);
//			if(link.endsWith(url)){
//				continue;
//			}
			if(!link.contains(linkcontent)){
				continue;
			}
			
			System.out.println("图片页面链接："+ link);
			//analsysImg(link);
		}
	}
	
	private static void analsysImg(String url){
		Document doc = NetUtils.getDocument(url);
		String tag = "body img";
		String attr = "abs:src";
		Elements elemens = doc.select(tag);

		for(Element e : elemens){
			String href = e.attr(attr);
			System.out.println( "图片链接："+href);
		}
	}
}
