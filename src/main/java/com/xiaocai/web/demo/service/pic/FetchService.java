package com.xiaocai.web.demo.service.pic;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xiaocai.web.demo.entity.pic.PicUrl;
import com.xiaocai.web.utils.common.NetUtils;
import com.xiaocai.web.utils.picUtils.RegexUtil;
import com.xiaocai.web.utils.picUtils.SavePicUtil;

@Service
public class FetchService {
	
	@Autowired
	private PictureService pictureService;
	

	/**
	 * get document
	 * @param url
	 * @param picUrl
	 */
	public  void  fetchPicsByUrl(String url,PicUrl picUrl){
		Document doc = null;
		try {
			doc = NetUtils.getDocument(url);
			if(doc==null){
				doc = NetUtils.getDocument(url);
			}
			if(doc==null){
				System.out.println(" FetchService.fetchPicsByUrl doc is null ");
			}
			//System.out.println(doc.toString());
			//analysis doc
			analysisDoc(doc, picUrl); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * analysis doc 
	 * @param doc
	 * @param picUrl
	 * @throws Exception
	 */
	private  void analysisDoc(Document doc,PicUrl picUrl) throws Exception{
		//analysis doc <a></a>
		Elements ptypes = doc.select("body div a");
		for(Element ptype :ptypes){
			String webaddr = ptype.attr("abs:href");
			String imgType = ptype.text();
			//System.out.println("analysisDoc abs:href :"+webaddr+" --> "+imgType+" ");
			if(!RegexUtil.isUrl(webaddr)){
				continue;
			}
			//analysis doc <a></a> --> doc
			analysisDocHref(webaddr, picUrl);
		}
		//analysis doc <img/>
		Elements links = doc.select("div img");
		String linkName = "";
		String imgUrl = "";
		for (Element link : links) {
			  String linkHref = link.attr("abs:src");
			  linkName = link.text();

			  String altSrc = link.attr("alt src");
			  String dataSrc = link.attr("data-src");
			  //System.out.println("analysisDoc img abs src link  "+linkName+"--> "+linkHref+ "-->"+RegexUtil.isPicLink(linkHref));
			  //System.out.println("analysisDoc img alt src link  "+linkName+"--> "+altSrc+ "-->"+RegexUtil.isPicLink(altSrc));
			  //System.out.println("analysisDoc img data src link  "+linkName+"--> "+dataSrc+ "-->"+RegexUtil.isPicLink(dataSrc));
			  if(StringUtils.isEmpty(linkName) || "".equals(linkName.trim())){
					linkName = link.attr("alt");
			  }
			  imgUrl = linkHref;
			  if(!RegexUtil.isPicLink(imgUrl)){
				  imgUrl = dataSrc;
				  if(!RegexUtil.isPicLink(imgUrl)){
					  imgUrl = altSrc;
				  }
			  }
			  
			  if(!RegexUtil.isPicLink(imgUrl)){
				  continue;
			  }
			  forsave(imgUrl,linkName, picUrl,linkName);
		}
	}
	
	/**
	 * the doc of <a></a>
	 * @param webaddr
	 * @param picUrl
	 * @throws Exception
	 */
	private  void analysisDocHref(String webaddr,PicUrl picUrl) throws Exception{
		
		Document doc = NetUtils.getDocument(webaddr);
		System.out.println("=========scend  fetch============="+webaddr);
		//System.out.println("======================"+doc);
		if(doc!= null){
			Elements links = doc.select("table tbody  a");
			String linkName = "";
			for (Element link : links) {
				  String linkHref = link.attr("abs:href");
				  //System.out.println("analysisDocHref abs:href link page:"+linkName+" --> "+linkHref);
				  linkName = link.text();
				  if(!RegexUtil.isPicLink(linkHref)){
					  continue;
				  }
				  System.out.println("analysisDocHref abs:href link page:"+linkName+" --> "+linkHref);
				  analysisImg(linkHref, picUrl);
			}
			
			//links = doc.select("div img");
			Elements imgs = doc.getElementsByTag("img");
			String imgUrl = "";
			for (Element link : imgs) {
				  String linkHref = link.attr("abs:src");
				  String altSrc = link.attr("alt src");
				  String dataSrc = link.attr("data-src");
				  linkName = link.text();
//				  System.out.println("analysisDocHref img abs src link  "+linkName+"--> "+linkHref+ "-->"+RegexUtil.isPicLink(linkHref));
//				  System.out.println("analysisDocHref img alt src link  "+linkName+"--> "+altSrc+ "-->"+RegexUtil.isPicLink(altSrc));
//				  System.out.println("analysisDocHref img data src link  "+linkName+"--> "+dataSrc+ "-->"+RegexUtil.isPicLink(dataSrc));
				  if(StringUtils.isEmpty(linkName) || "".equals(linkName.trim())){
					  linkName = link.attr("alt");
					  
			  	  }
				  imgUrl = linkHref;
				  if(!RegexUtil.isPicLink(imgUrl)){
					  imgUrl = dataSrc;
					  if(!RegexUtil.isPicLink(imgUrl)){
						  imgUrl = altSrc;
					  }
				  }
				  
				  if(!RegexUtil.isPicLink(imgUrl)){
					  continue;
				  }
				  forsave(imgUrl,linkName, picUrl,linkName);
			}
		}
	}
	
	public  void analysisImg(String webaddr,PicUrl picUrl) throws Exception {
		Document doc = NetUtils.getDocument(webaddr);
		if(doc!=null ){
			System.out.println(" ---- analysisImg ----");
			Elements imglinks = doc.select("div img");
			String imgUrl = "";
			String linkName = "";
			for (Element link : imglinks) {

				String linkHref = link.attr("abs:src");
				String altSrc = link.attr("alt src");
				String dataSrc = link.attr("data-src");
				linkName = link.text();
				if(StringUtils.isEmpty(linkName) || "".equals(linkName.trim())){
					linkName = link.attr("alt");
				}
//				System.out.println("analysisImg img abs src link:" + linkName + " --> " + linkHref + "-->"+ RegexUtil.isPicLink(linkHref));
//				System.out.println("analysisImg img alt src link  " + linkName + "--> " + altSrc + "-->" + RegexUtil.isPicLink(altSrc));
//				System.out.println("analysisImg img data src link  " + linkName + "--> " + dataSrc + "-->"	+ RegexUtil.isPicLink(dataSrc));

				imgUrl = linkHref;
				if (!RegexUtil.isPicLink(imgUrl)) {
					imgUrl = dataSrc;
					if (!RegexUtil.isPicLink(imgUrl)) {
						imgUrl = altSrc;
					}
				}

				if (!RegexUtil.isPicLink(imgUrl)) {
					continue;
				}

				forsave(linkHref, linkName, picUrl,linkName);
			}
		}
		
	}
	
	private  void  forsave(String linkHref, String fileName,PicUrl picUrl,String alt) throws Exception{
		//System.out.println( "---fileName "+fileName);
		String sufffix = linkHref.substring(linkHref.lastIndexOf("."));
		//if(StringUtils.isEmpty(fileName)){
			String urlFileName = linkHref.substring(linkHref.lastIndexOf("/")+1,linkHref.length()-4);
		//}
		fileName += urlFileName;
		String filePath = SavePicUtil.save(linkHref, fileName, sufffix);
		System.out.println(" linkHref--> "+linkHref +", filePath "+filePath +", picUrl "+picUrl);
		pictureService.savePicture(linkHref,filePath,picUrl,alt);
	}
	

	/**
	 * get document
	 * @param url
	 * @param picUrl
	 */
	public  String  fetchPicsByPicUrl(String url, String fileName,String suffix){
		return SavePicUtil.save(url ,fileName, suffix);
	}
}
