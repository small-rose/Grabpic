package com.xiaocai.web.demo.controller.website;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.xiaocai.web.demo.entity.website.WebPictrue;
import com.xiaocai.web.demo.service.website.WebPictrueService;
@Controller
public class WebPictureController {

	@Autowired
	WebPictrueService webPictrueService;
	/**
	 * 抓图数据demo页面跳转
	 * @return
	 */
	@RequestMapping("/webpicPage")
	public String picPage(HttpServletRequest  request){
		String linkId = request.getParameter("linkId");
		request.setAttribute("linkId", linkId);
		return "com/ssm/web/website/webPictureList";
	}
	
	/**
	 * 查询结果数据（分页查询）
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/webpicsAjax")
	@ResponseBody
	public AjaxJson webpicsAjax(@RequestParam(value="pn",defaultValue="1")Integer pn,@RequestParam(value="pageNum",defaultValue="5")Integer pageNum,
			Model model,WebPictrue picture ,HttpServletRequest  request){
		String linkId = request.getParameter("linkId");
		//引入pageHelper分页查询
		// 查询之前调用，传入页码和每页大小
		PageHelper.startPage(pn, pageNum);
		// startPage后面紧跟查询
		List<WebPictrue> pics = webPictrueService.findAll(linkId, picture ,request);
		// 使用pageInfo包装查询结果,只要将pageInfo交给页面
		// PageInfo封装了详细的分页信息（包含查询的数据），传入list,连续显示分页数
		PageInfo pageInfo = new PageInfo<>(pics, pageNum);

		return AjaxJson.success().addData("pageInfo", pageInfo);
		
	}
	
	@RequestMapping(value="/editWebpic",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson editWebpic(String id,HttpServletRequest  request){
		
		WebPictrue picture = webPictrueService.selectByPrimaryKey(id);
		WebPictrue picture2 = webPictrueService.viewPic(picture,request);
		return AjaxJson.success().addData("picture", picture2);
	}
	
	
	@RequestMapping(value="/saveWebpic",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson savePic(WebPictrue picture,HttpServletRequest  request){
		webPictrueService.renameWebpic(picture);
		return AjaxJson.success("保存成功");
	}
	

	
	@RequestMapping(value="/delWebpic/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public AjaxJson delAllWebpic(@PathVariable("ids") String ids,HttpServletRequest  request){
		try{
			int all = webPictrueService.delBatchWebpic(ids);
			return AjaxJson.success(all+"条记录刪除成功");
		}catch(Exception e){
			e.printStackTrace();
		}
		return AjaxJson.success("刪除失敗");
	}
	
	
	@RequestMapping(value="/markWebpic",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson markWebpic(String id,HttpServletRequest  request){
		try{
			String msg = "";
			WebPictrue picture = webPictrueService.selectByPrimaryKey(id);
			webPictrueService.markById(picture);
			if(ConstantHelp.Mark_nomal_1.equals(picture.getPicMark())){
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
	
	@RequestMapping("/viewWebpic")
	public String viewPic(String id,HttpServletRequest  request){
		WebPictrue picture = webPictrueService.selectByPrimaryKey(id);
		webPictrueService.viewPic(picture,request);
		request.setAttribute("fileName", picture.getPicOldname());
		return "com/ssm/web/pic/ViewFile";
	}
	
	
	@RequestMapping("/viewByLink")
	public String viewByLink(String linkId,HttpServletRequest  request){
		List<WebPictrue> picturelist= webPictrueService.selectWebpicByLinkId(linkId);
		webPictrueService.viewPicList(picturelist,request);
		
		request.setAttribute("fileNames", picturelist);
		return "com/ssm/web/pic/ViewListFile";
	}
	
	@RequestMapping("/viewWebPicList")
	public String viewWebPicList(String ids,HttpServletRequest  request){
		List<WebPictrue> picturelist= webPictrueService.selectByPrimaryKeys(ids);
		webPictrueService.viewPicList(picturelist,request);
		
		request.setAttribute("fileNames", picturelist);
		return "com/ssm/web/website/ViewListFile";
	}
	
	@RequestMapping("/viewWebpicRollList")
	public String viewRollList(String ids,HttpServletRequest  request){
		List<WebPictrue> picturelist= webPictrueService.selectByPrimaryKeys(ids);
		webPictrueService.viewPicList(picturelist,request);
		
		request.setAttribute("fileNames", picturelist);
		return "com/ssm/web/website/ViewRollListFile";
	}
	
	@RequestMapping("/downWebpicList")
	public void downPicList(String linkId,HttpServletRequest  request,HttpServletResponse  response){
		List<WebPictrue> picturelist= webPictrueService.findAll(linkId, null ,request);
		webPictrueService.downPicList(picturelist,request,response);
	}
	
	
	@RequestMapping(value="/redownWebpic/{id}",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson redownPic(@PathVariable("id") String id,HttpServletRequest  request){
		String msg = "";
		try{
			
			if(webPictrueService.redownPicByPicId(id)){
				msg = "获取成功";
			}
			return AjaxJson.success(msg);
		}catch(Exception e){
			e.printStackTrace();
			msg = "获取成功";
		}
		return AjaxJson.success(msg);
	}
}
