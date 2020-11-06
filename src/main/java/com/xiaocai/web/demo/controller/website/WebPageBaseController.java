package com.xiaocai.web.demo.controller.website;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaocai.core.common.message.AjaxJson;
import com.xiaocai.web.demo.constans.ConstantHelp;
import com.xiaocai.web.demo.entity.website.WebPageBase;
import com.xiaocai.web.demo.service.website.FetchWebComService;
import com.xiaocai.web.demo.service.website.WebPageService;
@Controller
public class WebPageBaseController {

	@Autowired
	private WebPageService webPageService;
	@Autowired
	private FetchWebComService fetchWebComService;
	/**
	 * 抓图数据demo页面跳转
	 * @return
	 */
	@RequestMapping("/webpagePage")
	public String picPage(HttpServletRequest  request){
		String categoryid = request.getParameter("categoryid");
		request.setAttribute("categoryid", categoryid);
		return "com/ssm/web/website/webPageList";
	}
	
	/**
	 * 查询结果数据（分页查询）
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/pagesAjax")
	@ResponseBody
	public AjaxJson picsAjax(@RequestParam(value="pn",defaultValue="1")Integer pn,@RequestParam(value="pageNum",defaultValue="5")Integer pageNum,String urlId,
			Model model,WebPageBase pageBase ,HttpServletRequest  request){
		String categoryid = request.getParameter("categoryid");
		//引入pageHelper分页查询
		// 查询之前调用，传入页码和每页大小
		PageHelper.startPage(pn, pageNum);
		// startPage后面紧跟查询
		List<WebPageBase> list = webPageService.findAll(categoryid ,pageBase ,request);
		// 使用pageInfo包装查询结果,只要将pageInfo交给页面
		// PageInfo封装了详细的分页信息（包含查询的数据），传入list,连续显示分页数
		PageInfo pageInfo = new PageInfo<>(list, pageNum);

		return AjaxJson.success().addData("pageInfo", pageInfo);
		
	}
	
	@RequestMapping(value="/editPage",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson editLink(String id,HttpServletRequest  request){
		
		WebPageBase webPageBase = webPageService.selectByPrimaryKey(id);
		return AjaxJson.success().addData("object", webPageBase);
	}
	
	
	@RequestMapping(value="/savePage",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveLink(WebPageBase pageBase,HttpServletRequest  request){
		
		webPageService.savePage(pageBase);
		return AjaxJson.success("保存成功");
	}
	
	
	@RequestMapping(value="/delPages/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public AjaxJson delAllLink(@PathVariable("ids") String ids,HttpServletRequest  request){
		try{
			webPageService.delBatchPage(ids);
			return AjaxJson.success("刪除成功");
		}catch(Exception e){
			e.printStackTrace();
		}
		return AjaxJson.success("刪除失敗");
	}
	
	
	@RequestMapping(value="/markPage",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson markPage(String id,HttpServletRequest  request){
		try{
			String msg = "";
			WebPageBase pageBase = webPageService.selectByPrimaryKey(id);
			webPageService.markById(pageBase);
			if(ConstantHelp.Mark_nomal_1.equals(pageBase.getPageMark())){
				msg = "取消收藏成功";
			}else{
				msg = "收藏成功";
			}
			return AjaxJson.success(msg);
		}catch(Exception e){
			e.printStackTrace();
		}
		return AjaxJson.success("操作失败");
	}
	
	@RequestMapping(value="/fetchPages",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson fetchPages(String id,HttpServletRequest  request){
		String msg = null;
		try{
			//String tid = request.getRequestedSessionId();
			//System.err.println("tid:"+tid);
			msg = webPageService.fetchLinksByPageId(id);
			return AjaxJson.success(msg);
		}catch(Exception e){
			e.printStackTrace();
			msg = "系统抓取出错";
		}
		return AjaxJson.failed(msg);
	}
	
	@RequestMapping(value="/fetchBatchByPages",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson fetchBatchByPages(String webId,String categoryId,String pageId,HttpServletRequest  request){
		String msg = null;
		try{
			msg = webPageService.fetchBatchByPages(webId,categoryId,pageId);
			return AjaxJson.success(msg);
		}catch(Exception e){
			e.printStackTrace();
			msg = "系统抓取出错";
		}
		return AjaxJson.failed(msg);
	}
	
	@RequestMapping(value="/updateLinkRules",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson updateLinkRules(WebPageBase webPageBase,HttpServletRequest  request){
		webPageService.updateBatchRules(webPageBase);
		return AjaxJson.success("批量修改成功");
	}
	
	//下拉框数据
	@RequestMapping(value = "/pagelist", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson pagelist(String categoryId, HttpServletRequest request) {

		List<WebPageBase> list = webPageService.findPagesByCategoryId(categoryId, request);
		return AjaxJson.success().addData("list", list);
	}
}
