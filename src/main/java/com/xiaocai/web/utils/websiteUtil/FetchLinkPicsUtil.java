package com.xiaocai.web.utils.websiteUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import com.xiaocai.web.demo.constans.ConstantHelp;
import com.xiaocai.web.demo.entity.website.WebLinkBase;
import com.xiaocai.web.demo.entity.website.WebPictrue;
import com.xiaocai.web.utils.common.AttachemnetUtil;
import com.xiaocai.web.utils.common.DateUtils;
import com.xiaocai.web.utils.common.FileOptionUtil;
import com.xiaocai.web.utils.common.NetUtils;
import com.xiaocai.web.utils.common.UuidUtils;
import com.xiaocai.web.utils.picUtils.RegexUtil;
import com.xiaocai.web.utils.picUtils.SavePicUtil;

public class FetchLinkPicsUtil {

	private static List<String> hisurl = new ArrayList<String>();
	private static List<String> picList = new ArrayList<String>();
	
	//Test start
	static String TestUrl = "http://www.mmkao.org/NAKED-ART/";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebLinkBase linkBase= new WebLinkBase();
	
		for(String hurl : picList){
			System.out.println(" hurl-->"+hurl);
		}
		linkBase.setLinkHerf("http://djc027.com/html/article/index10712.html");
		List<WebPictrue> result = analsysHtmlForPics(linkBase);
		for(WebPictrue tmp : result){
			System.out.println("-->"+tmp);
		}
		
		
	}
	//Test end
	
	/**
	 * 抓取网站某个分类页面所以的分页列表链接
	 * @param category
	 * @return
	 */
	public static List<WebPictrue> analsysHtmlForPics(WebLinkBase linkBase){
		List<WebPictrue> list = new ArrayList<WebPictrue>();
		String tmpKey = linkBase.getLinkHerf().substring(0, linkBase.getLinkHerf().lastIndexOf("."));
		System.out.println("url: "+linkBase.getLinkHerf());
		analsysImgAll(linkBase.getLinkHerf(),tmpKey);

		WebPictrue webPic = null ;
		String[] urls = null;
		String prekey =null , curkey = null, picUrl = null, oldName =null ,sufffix = "";
		byte[] content = null ;
		for(int k = 0; k < picList.size(); k ++){
			picUrl = picList.get(k);
			System.out.println(" picUrl" +picUrl);
			urls = picUrl.split(",");
			webPic = new WebPictrue();
			curkey = urls[0].substring(0, urls[0].lastIndexOf("/"));
			if(prekey!=null && !curkey.equals(prekey)){
				continue ;
			}
			System.out.println("prekey "+prekey+", curkey "+curkey);
			
			if(RegexUtil.isEndWithImgSuffix(urls[0])){
				if(urls.length>1){
					oldName = urls[1]+RegexUtil.getPicNameWithImgSuffix(urls[0]);
				}else{
					oldName = RegexUtil.getPicNameWithImgSuffix(urls[0]);
				}
				
				sufffix = oldName.substring(oldName.lastIndexOf("."));
			}else{
					if(urls.length>1 && !StringUtils.isEmpty(urls[1])){
						oldName = urls[1]+DateUtils.date2Str(DateUtils.datetimeFormat)+".jpg";
					}else{
						oldName = DateUtils.date2Str(DateUtils.datetimeFormat)+".jpg";
					}
			}
			webPic = getPicContent(linkBase.getLinkName(),urls[0], oldName, sufffix,webPic);
			if(webPic.getPicContent()==null){
				System.out.println(" 图片内容未取到 :"+webPic.getPicPath());
				continue ;
			}
			
			webPic.setPicId(UuidUtils.getUUID_32());
			webPic.setAddTime(DateUtils.date2Str(DateUtils.datetimeFormat));
			webPic.setLinkId(linkBase.getLinkId());
			webPic.setPicOldname(oldName);
			webPic.setPicAddr(urls[0]);
			webPic.setPicNo((k+1));
			webPic.setPicMark(ConstantHelp.Mark_nostatus_0);
			webPic.setPicDel(ConstantHelp.Del_nomal_1);
			if(urls.length>1){
				webPic.setPicName(urls[1]);
				webPic.setPicDesc(urls[1]);
			}
			
			list.add(webPic);
			prekey = curkey;
			System.out.println("还有："+(picList.size()-k)+"待下载");
		}
		hisurl.clear();
		picList.clear();
		return list;
	}
	
	
	
	private static void analsysImgAll(String url,String tmpKey){

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
		System.out.println("FetchLinkPicsUtil.analsysImgAll hisurl :"+hisurl.size());
		//找分页
		String hrefTag = "a";
		String hrefAttr = "abs:href";
		Elements tmps = doc.select(hrefTag);
		for(Element e : tmps){
			String href = e.attr(hrefAttr);
			if(href.equals(url)){
				continue;
			}
			if(!href.contains(tmpKey)){
				continue;
			}
			if(hisurl.contains(href)){
				continue;
			}
			System.out.println( " tmp href："+href);
			analsysImgAll(href,tmpKey);
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
			System.out.println("FetchLinkPicsUtil.analsysImg doc is null "+url);
			return;
		}
		String tag = "body img";
		String[] attr ={ "abs:src","alt src","data-src"};
		Elements elemens = doc.select(tag);
		String picName = "";
		String imgUrl = "";
		for(Element e : elemens){
			String src = e.attr(attr[0]);
			picName = e.attr("alt");
			if(StringUtils.isEmpty(src)){
				
			}
			imgUrl = src;
			if (!RegexUtil.isPicLink(imgUrl)) {
				imgUrl = e.attr(attr[1]);
				if (!RegexUtil.isPicLink(imgUrl)) {
					imgUrl = e.attr(attr[2]);
				}
			}
			System.out.println(" imgUrl+picName "+imgUrl+","+picName);
			if(!picList.contains((imgUrl+","+picName))){
				picList.add(imgUrl+","+picName);
			}
		}
	}
	
	private static WebPictrue getPicContent(String dir ,String imgsrc, String oldName, String sufffix,WebPictrue pictrue){
		String filePath = SavePicUtil.save(dir,imgsrc, oldName, sufffix);
		File file = new File(filePath);
		if(FileOptionUtil.checkImgSizePx(filePath)){
			return pictrue;
		}
		byte[] content  = AttachemnetUtil.convertFileToBytes(file);
		pictrue.setPicPath(filePath);
		pictrue.setPicContent(content);
		pictrue.setPicSize(file.length());
		pictrue.setPicSuffix(sufffix);
		return pictrue;
	}
}
