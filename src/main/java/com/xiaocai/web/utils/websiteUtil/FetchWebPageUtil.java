package com.xiaocai.web.utils.websiteUtil;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.xiaocai.web.demo.constans.ConstantHelp;
import com.xiaocai.web.demo.entity.website.WebCategory;
import com.xiaocai.web.demo.entity.website.WebPageBase;
import com.xiaocai.web.utils.common.DateUtils;
import com.xiaocai.web.utils.common.NetUtils;
import com.xiaocai.web.utils.common.UuidUtils;

public class FetchWebPageUtil {

	private static List<String> hisurl = new ArrayList<String>();
	private static List<String> pageList = new ArrayList<String>();
	
	//Test start
	static String TestUrl = "http://www.mmkao.org/NAKED-ART/";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebCategory category= new WebCategory();
		category.setPageTag("body .page a");
		category.setPageTagAttr("abs:href");
		category.setPageKeys("NAKED-ART");
		//analsysPage(TestUrl,category);
		for(String hisurl : hisurl){
			System.out.println(" hisurl -->"+hisurl);
		}
		category.setCategoryUrl("http://www.mmkao.org/NAKED-ART/");
		List<WebPageBase> result = analsysHtmlForPages(category);
		for(WebPageBase tmp : result){
			System.out.println("-->"+tmp);
		}
		System.out.println("-------------------------------------------");
		for(String tmp : pageList){
			System.out.println("-->"+tmp);
		}
	}
	//Test end
	
	/**
	 * 抓取网站某个分类页面所以的分页列表链接
	 * @param category
	 * @return
	 */
	public static List<WebPageBase> analsysHtmlForPages(WebCategory category){
		List<WebPageBase> list = new ArrayList<WebPageBase>();
		//pageList.add(category.getCategoryUrl());
		analsysPage(category.getCategoryUrl(),category);
		
		WebPageBase webPage = null ;
		String keys = null, pageUrl = null;
		for(int k = 0; k < pageList.size(); k ++){
			pageUrl = pageList.get(k);
			webPage = new WebPageBase();
			webPage.setPageId(UuidUtils.getUUID_32());
			webPage.setAddTime(DateUtils.date2Str(DateUtils.datetimeFormat));
			webPage.setCategoryId(category.getCategoryId());
			webPage.setPageHerf(pageUrl);
			webPage.setPageNo((long) (k+1));
			webPage.setPageMark(ConstantHelp.Mark_nostatus_0);
			webPage.setPageName("第"+(k+1)+"页");
			webPage.setLinkTag("a");//可能需要人为修改
			webPage.setLinkTagAttr("abs:href");
			//keys =  pageUrl.substring(0, pageUrl.lastIndexOf("."));
			webPage.setLinkKeys(category.getCategoryUrl());
			list.add(webPage);
		}
		hisurl.clear();
		pageList.clear();
		return list;
	}
	
	/**
	 * 递归获取分页列表的全部链接
	 * @param url
	 * @param category
	 */
	private static void analsysPage(String url,WebCategory category){
		
		if(hisurl.contains(url)){
			return;
		}
		Document doc = NetUtils.getDocument(url);
		if(doc==null){
			System.out.println("doc is null "+url);
			return;
		}
		hisurl.add(url);
		if(!pageList.contains(url)){
			pageList.add(url);
		}
		System.out.println("FetchWebPageUtil analsysPage hisurl size :"+hisurl.size());
		String tag = category.getPageTag();
		String attr = category.getPageTagAttr();
		String keys = category.getPageKeys();
		Elements elemens = doc.select(tag);
		for(Element e : elemens){
			String href = e.attr(attr);
			
			if(!href.contains(keys)){
				continue;
			}
			if(pageList.contains(href)){
				continue;
			}
			pageList.add(href);
			analsysPage(href,category);
		}
	}
}
