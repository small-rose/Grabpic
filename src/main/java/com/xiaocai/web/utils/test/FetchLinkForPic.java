package com.xiaocai.web.utils.test;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.xiaocai.web.utils.common.Log4JUtils;
import com.xiaocai.web.utils.common.NetUtils;

public class FetchLinkForPic {

	static String URL = "http://djc027.com/html/article/index";
	static String END = ".html";
	public static void main(String[] args) {
		int start = 1575;
		
		int end = 11208;
		int tmpEnd = 0,tmpstart=0;
		while(start <=end){
			tmpstart = start;
			tmpEnd = start +1000;
			
			String link = null;
			for(;start <= tmpEnd ;start++){
				if(start>end){
					System.out.println(" start-->"+start);
					break;
				}
				link = URL + start + END;
				System.out.println(link);
				analsysImg(link);
				System.out.println(" picList-->"+picList.size());
			}
			hisurl.clear();
			String filepath = "F:\\pic_html\\"+tmpstart+"TO"+tmpEnd+".txt";
			Log4JUtils.toLogFile(filepath);
			for(String src : picList){
				System.out.println(" src-->"+src);
			}
			System.out.println("picList :"+picList.size());
		}
		
	}
	
	static List<String> hisurl = new ArrayList<String>();
	static List<String> pageList = new ArrayList<String>();
	static List<String> linkList = new ArrayList<String>();
	static List<String> picList = new ArrayList<String>();
	
	
	/**
	 * 抓取指定页面图片，不含其他分页
	 * @param url
	 */
	private static void analsysImg(String url){
		
		Document doc = NetUtils.getDocument(url);
		if(doc==null){
			System.out.println("doc is null "+url);
			return;
		}
		
		String tag = "body .main img";
		String attr = "abs:src";
		Elements titles = doc.select("div .main .title");
		if(titles==null || titles.size()==0){
			return ;
		}
		String title = titles.get(0).text();
		Elements elemens = doc.select(tag);
		if(elemens.size()>0){
			//System.out.println("title "+title+" imgs : "+elemens.size());
			linkList.add(url);
		}
		for(Element e : elemens){
			String src = e.attr(attr);
			
			if(!picList.contains(src)){
				picList.add(src);
			}
		}
	}
	
	private static void analsysImgAll(String url){

		if(hisurl.contains(url)){
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
		System.out.println("analsysImgAll hisurl :"+hisurl.size());
		
	}
	
}

