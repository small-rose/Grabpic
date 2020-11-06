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
import com.xiaocai.web.demo.entity.website.WebLinkBase;
import com.xiaocai.web.demo.service.website.WebLinkService;
@Controller
public class WebLinkBaseController {

	@Autowired
	WebLinkService webLinkService;
	/**
	 * 抓图数据demo页面跳转
	 * @return
	 */
	@RequestMapping("/linkPage")
	public String picPage(HttpServletRequest  request){
		String pageId = request.getParameter("pageId");
		request.setAttribute("pageId", pageId);
		return "com/ssm/web/website/webLinkList";
	}
	
	/**
	 * 查询结果数据（分页查询）
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/linksAjax")
	@ResponseBody
	public AjaxJson linksAjax(@RequestParam(value="pn",defaultValue="1")Integer pn,@RequestParam(value="pageNum",defaultValue="5")Integer pageNum,String urlId,
			Model model,WebLinkBase link ,HttpServletRequest  request){
		String pageId = request.getParameter("pageId");
		//引入pageHelper分页查询
		// 查询之前调用，传入页码和每页大小
		PageHelper.startPage(pn, pageNum);
		// startPage后面紧跟查询
		List<WebLinkBase> list = webLinkService.findAll(pageId,link ,request);
		// 使用pageInfo包装查询结果,只要将pageInfo交给页面
		// PageInfo封装了详细的分页信息（包含查询的数据），传入list,连续显示分页数
		PageInfo pageInfo = new PageInfo<>(list, pageNum);

		return AjaxJson.success().addData("pageInfo", pageInfo);
		
	}
	
	@RequestMapping(value="/editLink",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson editLink(String id,HttpServletRequest  request){
		
		WebLinkBase link = webLinkService.selectByPrimaryKey(id);
		return AjaxJson.success().addData("object", link);
	}
	
	
	@RequestMapping(value="/saveLink",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveLink(WebLinkBase link,HttpServletRequest  request){
		webLinkService.saveLink(link);
		return AjaxJson.success("保存成功");
	}
	
	
	@RequestMapping(value="/delLink/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public AjaxJson delAllLink(@PathVariable("ids") String ids,HttpServletRequest  request){
		try{
			webLinkService.delBatchLink(ids);
			return AjaxJson.success("刪除成功");
		}catch(Exception e){
			e.printStackTrace();
		}
		return AjaxJson.success("刪除失敗");
	}
	
	
	@RequestMapping(value="/markLink",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson markLink(String id,HttpServletRequest  request){
		try{
			String msg = "";
			WebLinkBase link = webLinkService.selectByPrimaryKey(id);
			webLinkService.markById(link);
			if(ConstantHelp.Mark_nomal_1.equals(link.getLinkMark())){
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
	
	@RequestMapping(value="/fetchLink",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson fetchLink(String id,HttpServletRequest  request){
		String msg = "";
		try{
			msg = webLinkService.fetchPicsByLinkId(id);
			return AjaxJson.success(msg);
		}catch(Exception e){
			e.printStackTrace();
			msg = "系统抓取出错";
		}
		return AjaxJson.failed(msg);
	}
	
	@RequestMapping(value="/fetchBatchByLink",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson fetchBatchByLink(String webId,String categoryId,String pageId,HttpServletRequest  request){
		String msg = "";
		try{
			msg = webLinkService.fetchBatchByLink(webId,categoryId,pageId);
			return AjaxJson.success(msg);
		}catch(Exception e){
			e.printStackTrace();
			msg = "批量抓取系统出错";
		}
		return AjaxJson.failed(msg);
	}
	
}
