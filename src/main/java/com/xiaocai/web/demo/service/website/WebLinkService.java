package com.xiaocai.web.demo.service.website;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xiaocai.web.demo.constans.ConstantHelp;
import com.xiaocai.web.demo.dao.website.WebLinkBaseMapper;
import com.xiaocai.web.demo.dao.website.WebPageBaseMapper;
import com.xiaocai.web.demo.entity.emp.DepartmentExample.Criteria;
import com.xiaocai.web.demo.entity.website.WebLinkBase;
import com.xiaocai.web.demo.entity.website.WebLinkBaseExample;
import com.xiaocai.web.demo.entity.website.WebPageBase;
import com.xiaocai.web.demo.entity.website.WebPageBaseExample;
import com.xiaocai.web.demo.entity.website.WebPictrue;
import com.xiaocai.web.demo.excutor.website.FetchPictureTaskExcutor;
import com.xiaocai.web.utils.common.DateUtils;
import com.xiaocai.web.utils.common.UuidUtils;

@Service
public class WebLinkService {

	@Autowired
	private WebLinkBaseMapper webLinkBaseMapper;
	@Autowired
	private FetchPictureTaskExcutor fetchPictureTaskExcutor;
	@Autowired
	private FetchWebComService fetchWebComService;
	@Autowired
	private WebPageBaseMapper webPageBaseMapper;
	
	
	public List<WebLinkBase> findAll(String pageId, WebLinkBase link, HttpServletRequest request) {
		// TODO Auto-generated method stub
		WebLinkBaseExample example = new WebLinkBaseExample();
		com.xiaocai.web.demo.entity.website.WebLinkBaseExample.Criteria criteria = example.createCriteria();
		if(!StringUtils.isEmpty(pageId)){
			criteria.andPageIdWithPageEqualTo(pageId);
		}
		if(link!=null){
			if(!StringUtils.isEmpty(link.getLinkName())){
				 criteria.andLinkNameLike(link.getLinkName());
			 }
			 if(!StringUtils.isEmpty(link.getLinkMark())){
				 criteria.andLinkMarkEqualTo(link.getLinkMark());
			 }
			 
		}
		example.setOrderByClause("p.page_no,l.link_no asc,l.add_time desc");
		List<WebLinkBase> list = webLinkBaseMapper.selectByExampleWithCtPg(example);

		return list;
	}

	public WebLinkBase selectByPrimaryKey(String linkId) {
		// TODO Auto-generated method stub
		return webLinkBaseMapper.selectByPrimaryKeyWithCtPg(linkId);
	}

	public void saveLink(WebLinkBase object) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(object.getLinkId())){
			object.setLinkId(UuidUtils.getUUID_32());
			object.setAddTime(DateUtils.date2Str(DateUtils.datetimeFormat));
			object.setLinkMark(ConstantHelp.Mark_nostatus_0);
			System.out.println("INSERT : "+object);
			webLinkBaseMapper.insertSelective(object);
		}else{
			WebLinkBase tmp = selectByPrimaryKey(object.getLinkId());
			WebLinkBase objectPO = new WebLinkBase();
			objectPO.setLinkId(tmp.getLinkId());
			objectPO.setLinkHerf(object.getLinkHerf());
			objectPO.setLinkNo(object.getLinkNo());
			objectPO.setLinkName(object.getLinkName());
			objectPO.setImgTag(object.getImgTag());
			objectPO.setImgTagAttr(object.getImgTagAttr());
			
			System.out.println("UPDATE : "+objectPO);
			webLinkBaseMapper.updateByPrimaryKeySelective(objectPO);
		}
	}

	public void delBatchLink(String ids) {
		// TODO Auto-generated method stub
		String[] webIds = ids.split(","); 
		List<String> values = new ArrayList<>();
		for(String webId :webIds){
			values.add(webId);
		}
		WebLinkBaseExample example = new WebLinkBaseExample();
		com.xiaocai.web.demo.entity.website.WebLinkBaseExample.Criteria criteria = example.createCriteria();
		criteria.andLinkIdIn(values);
		webLinkBaseMapper.deleteByExample(example);
	}

	public void markById(WebLinkBase link) {
		// TODO Auto-generated method stub
		WebLinkBase linkPO = new WebLinkBase();
		linkPO.setLinkId(link.getLinkId());
		if(ConstantHelp.Mark_nomal_1.equals(link.getLinkMark())){
			linkPO.setLinkMark(ConstantHelp.Mark_nostatus_0);
		}else{
			linkPO.setLinkMark(ConstantHelp.Mark_nomal_1);
		}
		webLinkBaseMapper.updateByPrimaryKeySelective(linkPO);
	}
	
	/**
	 * 抓图片
	 * @param id
	 */
	public String fetchPicsByLinkId(String id) {
		// TODO Auto-generated method stub
		String msg = "抓取图片成功";
		WebLinkBase webLinkBase = webLinkBaseMapper.selectByPrimaryKey(id);
		//使用线程抓取
		//fetchPictureTaskExcutor.addFetchPictrueTask(webLinkBase);
		
		System.out.println("FetchPictureTaskExcutor------fetching  start...");
        List<WebPictrue> results = fetchWebComService.fetchPicsByLink(webLinkBase);
        if(results!=null && results.size()>0){
        	WebLinkBase linkBasePO = new WebLinkBase();
        	linkBasePO.setLinkId(webLinkBase.getLinkId());
        	linkBasePO.setPicTotal((long)results.size());
        	webLinkBaseMapper.updateByPrimaryKeySelective(linkBasePO);
        }else{
        	msg = "抓取图片失败";
        }
        System.out.println("FetchPictureTaskExcutor-------fetching  end...");
        return msg;
	}


	public String fetchBatchByLink(String webId, String categoryId, String pageId) {
		// TODO Auto-generated method stub
		String result = null;
		//按分页抓
		if(!StringUtils.isEmpty(pageId)){
			//WebPageBase webPage = webPageBaseMapper.selectByPrimaryKey(pageId);
			WebLinkBaseExample example = new WebLinkBaseExample();
			com.xiaocai.web.demo.entity.website.WebLinkBaseExample.Criteria criteria = example.createCriteria();
			criteria.andPageIdWithPageEqualTo(pageId);
			List<WebLinkBase> linkBases = webLinkBaseMapper.selectByExampleWithCtPg(example);
			System.out.println(" linkBases "+linkBases);
			if(linkBases==null || linkBases.isEmpty()){
				result = "该分页暂无链接，暂不支持自动抓取，请先手动执行抓链接";
				return result;
			}
			String msg = null;
			List<String> success = new ArrayList<>();
			List<String> failed = new ArrayList<>();
 			for(WebLinkBase linkBase :linkBases){
 				if(linkBase.getPicTotal()!=null && linkBase.getPicTotal()>0){
					continue;
				 }
				 msg = fetchPicsByLinkId(linkBase.getLinkId());
				 if(msg!=null && msg.contains("成功")){
					 success.add(msg);
				 }else{
					 failed.add(linkBase.getLinkId());
				 }
			}
 			result = "批量抓取总数："+linkBases.size()+",其中成功："+success.size()+",失败 "+failed.size();
		}else{
			//按分类抓
			if(!StringUtils.isEmpty(categoryId)){
				WebPageBaseExample example = new WebPageBaseExample();
				com.xiaocai.web.demo.entity.website.WebPageBaseExample.Criteria criteria = example.createCriteria();
				criteria.andCategoryIdWithCategoryEqualTo(categoryId);
				List<WebPageBase> webPageBases = webPageBaseMapper.selectByExampleWithCategory(example);
				String msg = null;
				List<String> success = new ArrayList<>();
				List<String> failed = new ArrayList<>();
				for(WebPageBase tmppageBase :webPageBases){
	
					if(tmppageBase.getLinkTotal()!=null && tmppageBase.getLinkTotal()>0){
						continue;
					 }
					 
					WebLinkBaseExample linkBaseExample = new WebLinkBaseExample();
					com.xiaocai.web.demo.entity.website.WebLinkBaseExample.Criteria criteria2 = linkBaseExample.createCriteria();
					criteria2.andPageIdWithPageEqualTo(pageId);
					List<WebLinkBase> linkBases = webLinkBaseMapper.selectByExampleWithCtPg(linkBaseExample);
					System.out.println(" linkBases " + linkBases);
					if (linkBases == null || linkBases.isEmpty()) {
						result += "该分页"+tmppageBase.getPageName()+"暂无链接,";
					}
					for (WebLinkBase linkBase : linkBases) {
						if (linkBase.getPicTotal() != null && linkBase.getPicTotal() > 0) {
							continue;
						}
						msg = fetchPicsByLinkId(linkBase.getLinkId());
						if (msg != null && msg.contains("成功")) {
							success.add(msg);
						} else {
							failed.add(linkBase.getLinkId());
						}
					}
				}
	 			result += "批量抓取总页数："+webPageBases.size()+",其中成功："+success.size()+",失败 "+failed.size();
			}
		}
		return result;
	}

	

}
