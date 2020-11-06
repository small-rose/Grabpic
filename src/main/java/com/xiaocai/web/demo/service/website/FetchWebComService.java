package com.xiaocai.web.demo.service.website;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaocai.web.demo.dao.website.WebCategoryMapper;
import com.xiaocai.web.demo.dao.website.WebLinkBaseMapper;
import com.xiaocai.web.demo.dao.website.WebPageBaseMapper;
import com.xiaocai.web.demo.dao.website.WebPictrueMapper;
import com.xiaocai.web.demo.entity.emp.DepartmentExample.Criteria;
import com.xiaocai.web.demo.entity.website.WebCategory;
import com.xiaocai.web.demo.entity.website.WebLinkBase;
import com.xiaocai.web.demo.entity.website.WebLinkBaseExample;
import com.xiaocai.web.demo.entity.website.WebPageBase;
import com.xiaocai.web.demo.entity.website.WebPageBaseExample;
import com.xiaocai.web.demo.entity.website.WebPictrue;
import com.xiaocai.web.demo.entity.website.WebPictrueExample;
import com.xiaocai.web.utils.websiteUtil.FetchLinkPicsUtil;
import com.xiaocai.web.utils.websiteUtil.FetchPageLinkUtil;
import com.xiaocai.web.utils.websiteUtil.FetchWebPageUtil;

@Service
public class FetchWebComService {
	@Autowired
	private WebCategoryMapper webCategoryMapper;
	@Autowired
	private WebPageBaseMapper webPageBaseMapper;
	@Autowired
	private WebLinkBaseMapper webLinkBaseMapper;
	@Autowired
	private WebPictrueMapper webPictrueMapper;
	
	/**
	 * 将抓取的page集合组装成List
	 * @param category
	 * @return
	 */
	public List<WebPageBase> fetchPages(WebCategory category){
		List<WebPageBase> pages = null;
		List<WebPageBase> newResult = new ArrayList<>();
		if(category!=null && category.getCategoryUrl()!=null){
			pages = FetchWebPageUtil.analsysHtmlForPages(category);
			//抓到结果的处理
			if(pages!=null && !pages.isEmpty()){
				//查到分类下所有分页
				//删除页面链接，重新插入
//				WebPageBaseExample queryExample = new WebPageBaseExample();
//				com.ssm.web.demo.entity.website.WebPageBaseExample.Criteria criteria1 = queryExample.createCriteria();
//				criteria1.andCategoryIdEqualTo(category.getCategoryId());
//				
//				List<WebPageBase> pagelist = webPageBaseMapper.selectByExample(queryExample);
//				List<String> pageIdList = new ArrayList<>();
//				for(WebPageBase pageBase : pagelist){
//					pageIdList.add(pageBase.getPageId());
//				}
				//删除图页相关的链接
//				WebLinkBaseExample webLinkBaseExample = new WebLinkBaseExample();
//				Criteria linkquery = webLinkBaseExample.createCriteria();
//				if(pageIdList.size()>0){
//					linkquery.andPageIdIn(pageIdList);
//				}
//				List<WebLinkBase> linklist = webLinkBaseMapper.selectByExample(webLinkBaseExample);
//				List<String> linkIdList = new ArrayList<>();
//				for(WebLinkBase linkBase : linklist){
//					linkIdList.add(linkBase.getLinkId());
//				}
//				
				//删除图的链接
//				WebPictrueExample picExample = new WebPictrueExample();
//				com.ssm.web.demo.entity.website.WebPictrueExample.Criteria picquery = picExample.createCriteria();
//				if(linkIdList.size()>0){
//					picquery.andLinkIdIn(linkIdList);
//				}
//				webPictrueMapper.deleteByExample(picExample);
//				webLinkBaseMapper.deleteByExample(webLinkBaseExample);
//				webPageBaseMapper.deleteByExample(queryExample);
//				webPageBaseMapper.insertByBatch(pages);
				
				//再筛选一次已爬取的
				for(WebPageBase pageBase :pages){
					WebPageBaseExample queryExample = new WebPageBaseExample();
					com.xiaocai.web.demo.entity.website.WebPageBaseExample.Criteria criteria1 = queryExample.createCriteria();
					criteria1.andCategoryIdEqualTo(category.getCategoryId());
					criteria1.andPageHerfEqualTo(pageBase.getPageHerf());
					List<WebPageBase> existpages = webPageBaseMapper.selectByExample(queryExample);
					if(existpages!=null && existpages.size()>0){
						continue;
					}
					newResult.add(pageBase);
				}
				if(!newResult.isEmpty()){
					System.out.println("pagelist newResult size is "+newResult.size());
					webPageBaseMapper.insertByBatch(newResult);
				}else{
					System.out.println("pagelist newResult is empty !");
				}
			}
			
		}
		return pages;

	}
	/**
	 * 将抓取的Link集合组装成List save
	 * @param pageBase
	 * @return
	 */
	public List<WebLinkBase> fetchLinks(WebPageBase pageBase){
		List<WebLinkBase> pages = null;
		List<WebLinkBase> newResult = new ArrayList<>();
		if(pageBase!=null && pageBase.getPageHerf()!=null){
			//System.out.println(" pageBase " +pageBase.toString());
			pages = FetchPageLinkUtil.analsysPageForLinks(pageBase);
			
			if(pages!=null && pages.size()>0){
//				WebLinkBaseExample webLinkBaseExample = new WebLinkBaseExample();
//				Criteria criteria = webLinkBaseExample.createCriteria();
//				criteria.andPageIdEqualTo(pageBase.getPageId());
//				List<WebLinkBase> linklist = webLinkBaseMapper.selectByExample(webLinkBaseExample);
//				List<String> linkIdList = new ArrayList<>();
//				for(WebLinkBase linkBase : linklist){
//					linkIdList.add(linkBase.getLinkId());
//				}
				
				//删除图的链接
//				WebPictrueExample picExample = new WebPictrueExample();
//				com.ssm.web.demo.entity.website.WebPictrueExample.Criteria picquery = picExample.createCriteria();
//				if(linkIdList.size()>0){
//					picquery.andLinkIdIn(linkIdList);
//				}
				
				
				//webPictrueMapper.deleteByExample(picExample);
				
				//webLinkBaseMapper.deleteByExample(webLinkBaseExample);
				
				
				
				//再筛选一次已爬取的
				for(WebLinkBase linkBase :pages){
					WebLinkBaseExample webLinkBaseExample = new WebLinkBaseExample();
					com.xiaocai.web.demo.entity.website.WebLinkBaseExample.Criteria criteria = webLinkBaseExample.createCriteria();
					criteria.andPageIdEqualTo(pageBase.getPageId());
					criteria.andLinkHerfEqualTo(linkBase.getLinkHerf());
					//criteria.andLinkNameEqualTo(linkBase.getLinkName());
					List<WebLinkBase> existLinks = webLinkBaseMapper.selectByExample(webLinkBaseExample);
					if(existLinks!=null && existLinks.size()>0){
						continue;
					}
					newResult.add(linkBase);
				}
				if(!newResult.isEmpty()){
					System.out.println("linkList newResult size is "+newResult.size());
					webLinkBaseMapper.insertByBatch(newResult);
				}else{
					System.out.println("linkList newResult is empty !");
				}
				
			}
			
		}
		return newResult;
	}
	
	/**
	 * 将抓取的pic集合组装成List
	 * @param category
	 * @return
	 */
	public List<WebPictrue> fetchPicsByLink(WebLinkBase link){
		List<WebPictrue> pages = null;
		if(link!=null && link.getLinkHerf()!=null){
			pages = FetchLinkPicsUtil.analsysHtmlForPics(link);
			if(pages!=null && !pages.isEmpty()){
				WebPictrueExample example = new WebPictrueExample();
				com.xiaocai.web.demo.entity.website.WebPictrueExample.Criteria criteria1 = example.createCriteria();
				criteria1.andLinkIdEqualTo(link.getLinkId());
				webPictrueMapper.deleteByExample(example);
				
				webPictrueMapper.insertByBatch(pages);
			}
		}
		return pages;

	}
	
	
}
