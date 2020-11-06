package com.xiaocai.web.demo.service.website;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xiaocai.web.demo.constans.ConstantHelp;
import com.xiaocai.web.demo.dao.website.WebCategoryMapper;
import com.xiaocai.web.demo.dao.website.WebLinkBaseMapper;
import com.xiaocai.web.demo.dao.website.WebPageBaseMapper;
import com.xiaocai.web.demo.entity.emp.DepartmentExample.Criteria;
import com.xiaocai.web.demo.entity.website.WebCategory;
import com.xiaocai.web.demo.entity.website.WebLinkBase;
import com.xiaocai.web.demo.entity.website.WebPageBase;
import com.xiaocai.web.demo.entity.website.WebPageBaseExample;
import com.xiaocai.web.demo.excutor.website.FetchLinkUrlTaskExcutor;
import com.xiaocai.web.utils.common.DateUtils;
import com.xiaocai.web.utils.common.Log4JUtils;
import com.xiaocai.web.utils.common.UuidUtils;

@Service
public class WebPageService {

	@Autowired
	private WebPageBaseMapper webPageBaseMapper;
	@Autowired
	private WebCategoryMapper webCategoryMapper;
	@Autowired
	private FetchLinkUrlTaskExcutor fetchLinkUrlTaskExcutor;
	@Autowired
	private FetchWebComService fetchWebComService;
	@Autowired
	private WebLinkBaseMapper webLinkBaseMapper;
	
	public List<WebPageBase> findAll(String categoryid,WebPageBase object, HttpServletRequest request) {
		// TODO Auto-generated method stub
		WebPageBaseExample example = new WebPageBaseExample();
		if(!StringUtils.isEmpty(categoryid)){
			com.xiaocai.web.demo.entity.website.WebPageBaseExample.Criteria query = example.createCriteria();
			query.andCategoryIdWithCategoryEqualTo(categoryid);
		}
		example.setOrderByClause("c.category_name, p.page_no asc ,p.add_time desc");
		List<WebPageBase> list = webPageBaseMapper.selectByExampleWithCategory(example);
		return list;
	}

	public WebPageBase selectByPrimaryKey(String pageId) {
		// TODO Auto-generated method stub
		 return webPageBaseMapper.selectByPrimaryKeyWithCategory(pageId);
	}

	public void savePage(WebPageBase object) {
		// TODO Auto-generated method stub
		System.out.println("id :"+object.getPageId());
		if(StringUtils.isEmpty(object.getPageId())){
			object.setPageId(UuidUtils.getUUID_32());
			object.setAddTime(DateUtils.date2Str(DateUtils.datetimeFormat));
			object.setPageMark(ConstantHelp.Mark_nostatus_0);
			object.setPageName("第"+object.getPageNo()+"页");
			System.out.println("INSERT : "+object);
			webPageBaseMapper.insertSelective(object);
		}else{
			WebPageBase tmp = selectByPrimaryKey(object.getPageId());
			WebPageBase objectPO = new WebPageBase();
			objectPO.setPageId(tmp.getPageId());
			objectPO.setPageHerf(object.getPageHerf());
			objectPO.setPageNo(object.getPageNo());
			objectPO.setPageName("第"+object.getPageNo()+"页");
			objectPO.setLinkTag(object.getLinkTag());
			objectPO.setLinkTagAttr(object.getLinkTagAttr());
			objectPO.setLinkKeys(object.getLinkKeys());
			System.out.println("UPDATE : "+objectPO);
			webPageBaseMapper.updateByPrimaryKeySelective(objectPO);
		}
	}

	public void delBatchPage(String ids) {
		String[] ctIds = ids.split(",");
		List<String> values = new ArrayList<>();
		List<String> categoryIds = new ArrayList<>();
		WebPageBase tmp = null;
		for (String objId : ctIds) {
			values.add(objId);
			tmp = selectByPrimaryKey(objId);
			if(!categoryIds.contains(tmp.getCategoryId())){
				categoryIds.add(tmp.getCategoryId());
			}
		}
		WebPageBaseExample example = new WebPageBaseExample();
		com.xiaocai.web.demo.entity.website.WebPageBaseExample.Criteria criteria = example.createCriteria();
		criteria.andPageIdIn(values);
		webPageBaseMapper.deleteByExample(example);
		
		//更新pageTotal
		WebPageBaseExample example2 = new WebPageBaseExample();
		com.xiaocai.web.demo.entity.website.WebPageBaseExample.Criteria criteria2 = example2.createCriteria();
		for (String categoryId : categoryIds) {
			criteria2.andCategoryIdEqualTo(categoryId);
			long size = webPageBaseMapper.countByExample(example2);
			updatePageTatal(categoryId,size);
		}
	}

	private void updatePageTatal(String categoryId,long size){
		WebCategory webCategoryPO = new WebCategory();
		webCategoryPO.setCategoryId(categoryId);
		webCategoryPO.setPageTotal((long)size);
		webCategoryMapper.updateByPrimaryKeySelective(webCategoryPO);
	}
	
	public void markById(WebPageBase object) {
		// TODO Auto-generated method stub
		WebPageBase objectPO = new WebPageBase();
		objectPO.setPageId(object.getPageId());
		if (ConstantHelp.Mark_nomal_1.equals(object.getPageMark())) {
			objectPO.setPageMark(ConstantHelp.Mark_nostatus_0);
		} else {
			objectPO.setPageMark(ConstantHelp.Mark_nomal_1);
		}
		webPageBaseMapper.updateByPrimaryKeySelective(objectPO);		
	}
	
	public List<WebPageBase> findPagesByCategoryId(String categoryId, HttpServletRequest request) {
		// TODO Auto-generated method stub
		WebPageBaseExample example = new WebPageBaseExample();
		com.xiaocai.web.demo.entity.website.WebPageBaseExample.Criteria criteria = example.createCriteria();
		if(!StringUtils.isEmpty(categoryId)){
			criteria.andCategoryIdEqualTo(categoryId);
		}
		return webPageBaseMapper.selectByExample(example);
	}

	public String fetchLinksByPageId(String id) {
		// TODO Auto-generated method stub
		Log4JUtils.toLog();
		String msg = "抓取连接成功";
		WebPageBase pageBase = webPageBaseMapper.selectByPrimaryKey(id);
		//fetchLinkUrlTaskExcutor.addFetchLinkTask(pageBase);
		System.out.println("fetchLinksByPageId-------fetching start  ...");
		List<WebLinkBase> pages = fetchWebComService.fetchLinks(pageBase);
		if(pages!=null && pages.size()>0){
			//重新计算链接数
			long total = webLinkBaseMapper.countLinkTotalByPageId(pageBase.getPageId());
        	WebPageBase webPageBase = new WebPageBase();
        	webPageBase.setPageId(pageBase.getPageId());
        	webPageBase.setLinkTotal(total);
        	webPageBaseMapper.updateByPrimaryKeySelective(webPageBase);
        }else{
        	 msg = "未抓取到新连接";
        }
        System.out.println("fetchLinksByPageId-------fetching  end....");
        return msg;
	}

	/**
	 * 批量更新规则
	 * @param webPageBase
	 */
	public void updateBatchRules(WebPageBase webPageBase) {
		// TODO Auto-generated method stub
		String categoryId = webPageBase.getCategoryId();
		WebPageBaseExample example = new WebPageBaseExample();
		com.xiaocai.web.demo.entity.website.WebPageBaseExample.Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		WebPageBase record  = new WebPageBase();
		if(webPageBase.getPageId()!=null){
			record.setPageId(webPageBase.getPageId());
		}
		record.setLinkTag(webPageBase.getLinkTag());
		record.setLinkTagAttr(webPageBase.getLinkTagAttr());
		record.setLinkKeys(webPageBase.getLinkKeys());
		webPageBaseMapper.updateByExampleSelective(record , example);
	}

	public String fetchBatchByPages(String webId, String categoryId, String pageId) {
		// TODO Auto-generated method stub
		String result = null;
		//按分页抓
		if(!StringUtils.isEmpty(pageId)){
			//WebPageBase webPage = webPageBaseMapper.selectByPrimaryKey(pageId);
			System.out.println("抓取指定页："+pageId);
			result = fetchLinksByPageId(pageId);
		}else{
			//按分类抓
			if(!StringUtils.isEmpty(categoryId)){
				WebPageBaseExample example = new WebPageBaseExample();
				com.xiaocai.web.demo.entity.website.WebPageBaseExample.Criteria criteria = example.createCriteria();
				criteria.andCategoryIdWithCategoryEqualTo(categoryId);
				List<WebPageBase> webPageBases = webPageBaseMapper.selectByExampleWithCategory(example);
				System.out.println("抓取全部页："+webPageBases.size());
				String msg = null;
				List<String> success = new ArrayList<>();
				List<String> failed = new ArrayList<>();
				for(WebPageBase pageBase :webPageBases){
					 if(pageBase.getLinkTotal()!=null && pageBase.getLinkTotal()>0){
						continue;
					 }
					 msg = fetchLinksByPageId(pageBase.getPageId());
					 if(msg!=null && msg.contains("成功")){
						 success.add(msg);
					 }else{
						 failed.add(pageBase.getPageId());
					 }
				}
	 			result = "批量抓取总数："+webPageBases.size()+",其中成功："+success.size()+",失败 "+failed.size();
			}
		}
		return result;
	}
}
