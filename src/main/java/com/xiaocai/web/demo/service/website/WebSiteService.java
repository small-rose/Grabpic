package com.xiaocai.web.demo.service.website;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xiaocai.web.demo.dao.website.WebSiteMapper;
import com.xiaocai.web.demo.entity.emp.DepartmentExample.Criteria;
import com.xiaocai.web.demo.entity.website.WebSite;
import com.xiaocai.web.demo.entity.website.WebSiteExample;
import com.xiaocai.web.utils.common.DateUtils;
import com.xiaocai.web.utils.common.NetUtils;
import com.xiaocai.web.utils.common.UuidUtils;

@Service
public class WebSiteService {

	@Autowired
	private WebSiteMapper webSiteMapper;

	public List<WebSite> findAll(WebSite webSite, HttpServletRequest request) {
		// TODO Auto-generated method stub
		WebSiteExample example = new WebSiteExample();
		com.xiaocai.web.demo.entity.website.WebSiteExample.Criteria criteria = example.createCriteria();
		if(webSite!=null){
			if(!StringUtils.isEmpty(webSite.getWebName())){
				 criteria.andWebNameLike(webSite.getWebName());
			 }
			 if(!StringUtils.isEmpty(webSite.getWebOpen())){
				 criteria.andWebOpenEqualTo(webSite.getWebOpen());
			 }
		}
		example.setOrderByClause("add_time desc");
		List<WebSite> list = webSiteMapper.selectByExample(example);
		return list;
	}

	public WebSite selectByPrimaryKey(String webId) {
		// TODO Auto-generated method stub
		return webSiteMapper.selectByPrimaryKey(webId);
	}

	public void saveWebSite(WebSite webSite) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(webSite.getWebId())){
			webSite.setWebId(UuidUtils.getUUID_32());
			webSite.setAddTime(DateUtils.date2Str(DateUtils.datetimeFormat));
			webSiteMapper.insertSelective(webSite);
			
		}else{
			WebSite tmp = selectByPrimaryKey(webSite.getWebId());
			WebSite webSiteVO = new WebSite();
			webSiteVO.setWebId(tmp.getWebId());
			webSiteVO.setWebUrl(webSite.getWebUrl());
			webSiteVO.setWebName(webSite.getWebName());
			webSiteVO.setWebDesc(webSite.getWebDesc());
			webSiteVO.setWebOpen(webSite.getWebOpen());
			webSiteMapper.updateByPrimaryKeySelective(webSiteVO);
		}
	}

	public void delWebsiteByIds(String ids) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(ids)){
			return ;
		}
		String[] webIds = ids.split(","); 
		List<String> values = new ArrayList<>();
		for(String webId :webIds){
			values.add(webId);
		}
		WebSiteExample example = new WebSiteExample();
		com.xiaocai.web.demo.entity.website.WebSiteExample.Criteria criteria = example.createCriteria();
		criteria.andWebIdIn(values);
		webSiteMapper.deleteByExample(example);
	}

	public void fetchWebSiteById(String id) {
		// TODO Auto-generated method stub
		try {
			WebSite tmp = selectByPrimaryKey(id);
			WebSite webSitePO = new WebSite();
			Document doc = NetUtils.getDocument(tmp.getWebUrl());
			//System.out.println(doc);
			String content = null ;
			if(doc!=null ){
				content = doc.toString();
			}
			webSitePO.setWebId(tmp.getWebId());
			webSitePO.setWebContent(content);
			webSiteMapper.updateByPrimaryKeySelective(webSitePO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
}
