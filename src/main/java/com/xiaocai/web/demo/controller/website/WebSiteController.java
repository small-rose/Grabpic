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
import com.xiaocai.web.demo.entity.website.WebSite;
import com.xiaocai.web.demo.service.website.WebSiteService;
@Controller
public class WebSiteController {

	@Autowired
	WebSiteService webSiteService;
	/**
	 * 抓图数据demo页面跳转
	 * @return
	 */
	@RequestMapping("/sitePage")
	public String picPage(HttpServletRequest  request){
		
		return "com/ssm/web/website/webSiteList";
	}
	
	/**
	 * 查询结果数据（分页查询）
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/websiteAjax")
	@ResponseBody
	public AjaxJson websiteAjax(@RequestParam(value="pn",defaultValue="1")Integer pn,@RequestParam(value="pageNum",defaultValue="5")Integer pageNum,String urlId,
			Model model,WebSite webSite ,HttpServletRequest  request){
		//引入pageHelper分页查询
		// 查询之前调用，传入页码和每页大小
		PageHelper.startPage(pn, pageNum);
		// startPage后面紧跟查询
		List<WebSite> pics = webSiteService.findAll(webSite ,request);
		// 使用pageInfo包装查询结果,只要将pageInfo交给页面
		// PageInfo封装了详细的分页信息（包含查询的数据），传入list,连续显示分页数
		PageInfo pageInfo = new PageInfo<>(pics, pageNum);

		return AjaxJson.success().addData("pageInfo", pageInfo);
		
	}
	
	@RequestMapping(value="/editWebsite",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson editWebsite(String id,HttpServletRequest  request){
		
		WebSite webSite = webSiteService.selectByPrimaryKey(id);
		return AjaxJson.success().addData("object", webSite);
	}
	
	
	@RequestMapping(value="/saveWebsite",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveWebsite(WebSite webSite,HttpServletRequest  request){
		webSiteService.saveWebSite(webSite);
		return AjaxJson.success("保存成功");
	}
	
	
	@RequestMapping(value="/delWebsite/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public AjaxJson delAllPic(@PathVariable("ids") String ids,HttpServletRequest  request){
		try{
			webSiteService.delWebsiteByIds(ids);
			return AjaxJson.success("刪除成功");
		}catch(Exception e){
			e.printStackTrace();
		}
		return AjaxJson.success("刪除失敗");
	}
	
	@RequestMapping(value="/fetchWebSite",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson fetchWebSite(String id,HttpServletRequest  request){
		try{
			webSiteService.fetchWebSiteById(id);
			return AjaxJson.success("抓取请求成功");
		}catch(Exception e){
			e.printStackTrace();
		}
		return AjaxJson.failed("抓取请求失敗");
	}
	
	@RequestMapping(value="/viewWebContent")
	public String viewWebContent(String id,HttpServletRequest  request){
		WebSite webSite = webSiteService.selectByPrimaryKey(id);
		request.setAttribute("object", webSite);
		return "com/ssm/web/website/webSiteConent";
	}
	
	//下拉框数据
	@RequestMapping(value="/websiteSelect",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson websiteSelect(String id,HttpServletRequest  request){
		WebSite webSite = new WebSite();
		List<WebSite> list = webSiteService.findAll(webSite ,request);
		return AjaxJson.success().addData("list", list);
	}
	
	
}
