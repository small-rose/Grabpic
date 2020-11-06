package com.xiaocai.web.demo.service.website;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xiaocai.web.demo.constans.ConstantHelp;
import com.xiaocai.web.demo.dao.website.WebCategoryMapper;
import com.xiaocai.web.demo.dao.website.WebSiteMapper;
import com.xiaocai.web.demo.entity.emp.DepartmentExample.Criteria;
import com.xiaocai.web.demo.entity.website.WebCategory;
import com.xiaocai.web.demo.entity.website.WebCategoryExample;
import com.xiaocai.web.demo.entity.website.WebPageBase;
import com.xiaocai.web.demo.entity.website.WebSite;
import com.xiaocai.web.demo.entity.website.WebSiteExample;
import com.xiaocai.web.demo.excutor.website.FetchPageUrlTaskExcutor;
import com.xiaocai.web.utils.common.DateUtils;
import com.xiaocai.web.utils.common.Log4JUtils;
import com.xiaocai.web.utils.common.UuidUtils;

@Service
public class WebCategoryService {

	@Autowired
	private WebCategoryMapper webCategoryMapper;
	@Autowired
	private WebSiteMapper webSiteMapper;
	@Autowired
	private FetchPageUrlTaskExcutor fetchPageUrlTaskExcutor;
	@Autowired
	private FetchWebComService fetchWebComService;
	
	public List<WebCategory> findAll(WebCategory category, HttpServletRequest request) {
		// TODO Auto-generated method stub 
		WebCategoryExample example = new WebCategoryExample();
		com.xiaocai.web.demo.entity.website.WebCategoryExample.Criteria criteria = example.createCriteria();
		if(category!=null){
			if(!StringUtils.isEmpty(category.getCategoryName())){
				 criteria.andCategoryNameLike(category.getCategoryName());
			 }
			if(category.getWebSite()!=null && !StringUtils.isEmpty(category.getWebSite().getWebName())){
				 WebSiteExample webSiteExample = new WebSiteExample();
				  com.xiaocai.web.demo.entity.website.WebSiteExample.Criteria criteriaWs = webSiteExample.createCriteria();
				 criteriaWs.andWebNameLike(category.getWebSite().getWebName());
				 List<WebSite> websites = webSiteMapper.selectByExample(webSiteExample);
				 List<String> values = new ArrayList<>();
				 for(WebSite webSite :websites){
					 values.add(webSite.getWebId());
				 }
				 criteria.andWebIdIn(values);
				 System.out.println("values ：" +values);
			 }
			if(!StringUtils.isEmpty(category.getCategoryMark())){
				criteria.andCategoryMarkEqualTo(category.getCategoryMark());
			}
		}
		example.setOrderByClause("add_time desc");
		List<WebCategory> list = webCategoryMapper.selectByExampleWithWebsite(example);
		return list;
	}

	public WebCategory selectByPrimaryKey(String categoryId) {
		// TODO Auto-generated method stub
		return webCategoryMapper.selectByPrimaryKey(categoryId);
	}

	public void saveCategory(WebCategory webCategory) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(webCategory.getCategoryId())){
			webCategory.setCategoryId(UuidUtils.getUUID_32());
			webCategory.setAddTime(DateUtils.date2Str(DateUtils.datetimeFormat));
			webCategory.setCategoryMark(ConstantHelp.Mark_nostatus_0);
			System.out.println("INSERT : "+webCategory);
			webCategoryMapper.insertSelective(webCategory);
		}else{
			WebCategory tmp = selectByPrimaryKey(webCategory.getCategoryId());
			WebCategory webCategoryPO = new WebCategory();
			webCategoryPO.setCategoryId(tmp.getCategoryId());
			webCategoryPO.setCategoryUrl(webCategory.getCategoryUrl());
			webCategoryPO.setCategoryName(webCategory.getCategoryName());
			webCategoryPO.setPageTag(webCategory.getPageTag());
			webCategoryPO.setPageTagAttr(webCategory.getPageTagAttr());
			webCategoryPO.setPageKeys(webCategory.getPageKeys());
			System.out.println("UPDATE : "+webCategoryPO);
			webCategoryMapper.updateByPrimaryKeySelective(webCategoryPO);
		}
		
	}

	public void delBatchCategory(String ids) {
		// TODO Auto-generated method stub
		String[] ctIds = ids.split(",");
		List<String> values = new ArrayList<>();
		for (String objId : ctIds) {
			values.add(objId);
		}
		WebCategoryExample example = new WebCategoryExample();
		com.xiaocai.web.demo.entity.website.WebCategoryExample.Criteria criteria = example.createCriteria();
		criteria.andCategoryIdIn(values);
		webCategoryMapper.deleteByExample(example);
	}

	public void markById(WebCategory category) {
		// TODO Auto-generated method stub
		WebCategory categoryPO = new WebCategory();
		categoryPO.setCategoryId(category.getCategoryId());
		if (ConstantHelp.Mark_nomal_1.equals(category.getCategoryMark())) {
			categoryPO.setCategoryMark(ConstantHelp.Mark_nostatus_0);
		} else {
			categoryPO.setCategoryMark(ConstantHelp.Mark_nomal_1);
		}
		webCategoryMapper.updateByPrimaryKeySelective(categoryPO);
	}

	
	public List<WebCategory> findWebSiteByWebId(String webId, HttpServletRequest request) {
		// TODO Auto-generated method stub
		WebCategoryExample example = new WebCategoryExample();
		com.xiaocai.web.demo.entity.website.WebCategoryExample.Criteria criteria = example.createCriteria();
		if(!StringUtils.isEmpty(webId)){
			criteria.andWebIdEqualTo(webId);
		}
		return webCategoryMapper.selectByExample(example);
	}

	public String fetchListByCategoryId(String id) {
		// TODO Auto-generated method stub
		Log4JUtils.toLog();
		String msg = "链接获取成功";
		WebCategory webCategory = webCategoryMapper.selectByPrimaryKey(id);
		//fetchPageUrlTaskExcutor.addFetchPageTask(webCategory);
		Log4JUtils.toLog("FetchPageUrlTaskExcutor-------fetching start ...");
		List<WebPageBase> resultList = fetchWebComService.fetchPages(webCategory);
		if(resultList!=null && resultList.size()>0 ){
        	WebCategory categoryPO = new WebCategory();
        	categoryPO.setCategoryId(webCategory.getCategoryId());
        	categoryPO.setPageTotal((long)resultList.size());
        	webCategoryMapper.updateByPrimaryKeySelective(categoryPO);
        	
        }else{
        	msg = "链接获取失败！";
        }
        System.out.println("FetchPageUrlTaskExcutor -------fetching end...");
        return msg;
	}

	public void updateBatchRules(WebCategory webCategory) {
		// TODO Auto-generated method stub
		String webId = webCategory.getWebId();
		WebCategoryExample example = new WebCategoryExample();
		com.xiaocai.web.demo.entity.website.WebCategoryExample.Criteria criteria = example.createCriteria();
		criteria.andWebIdEqualTo(webId);
		WebCategory record  = new WebCategory();
		if(webCategory.getCategoryId()!=null){
			record.setCategoryId(webCategory.getCategoryId());
		}
		record.setPageTag(webCategory.getPageTag());
		record.setPageTagAttr(webCategory.getPageTagAttr());
		record.setPageKeys(webCategory.getPageKeys());
		webCategoryMapper.updateByExampleSelective(record , example);
	}


}
