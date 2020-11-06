package com.xiaocai.web.demo.controller.pic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaocai.core.common.message.AjaxJson;
import com.xiaocai.web.demo.entity.pic.PicUrl;
import com.xiaocai.web.demo.service.pic.PicUrlService;
@Controller
public class PicUrlController {

	@Autowired
	PicUrlService picUrlService;
	/**
	 * 抓图数据demo页面跳转
	 * @return
	 */
	@RequestMapping("/picUrlPage")
	public String picUrlPage(){
		return "com/ssm/web/pic/picUrlAjaxlist";
	}
	
	/**
	 * 查询结果数据（分页查询）
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/picUrlAjax")
	@ResponseBody
	public AjaxJson picUrlAjax(@RequestParam(value="pn",defaultValue="1")Integer pn,@RequestParam(value="pageNum",defaultValue="5")Integer pageNum,Model model){
		//引入pageHelper分页查询
		// 查询之前调用，传入页码和每页大小
		PageHelper.startPage(pn, pageNum);
		// startPage后面紧跟查询
		List<PicUrl> urls = picUrlService.findAll();
		// 使用pageInfo包装查询结果,只要将pageInfo交给页面
		// PageInfo封装了详细的分页信息（包含查询的数据），传入list,连续显示分页数
		PageInfo pageInfo = new PageInfo<>(urls, pageNum);

		return AjaxJson.success().addData("pageInfo", pageInfo);
	}
	
	@RequestMapping(value="/urlSave",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson urlSave(PicUrl picUrl,HttpServletRequest  request){
		picUrlService.urlSave(picUrl);
		return AjaxJson.success("保存成功");
	}
	
	@RequestMapping(value="/editUrl",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson editUrl(String id,HttpServletRequest  request){
		
		PicUrl picUrl = picUrlService.selectByPrimaryKey(id);
		return AjaxJson.success().addData("picUrl", picUrl);
	}
	
	@RequestMapping(value="/delUrl",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson delUrl(String id,HttpServletRequest  request){
		try{
			picUrlService.delUrlById(id);
			return AjaxJson.success("刪除成功");
		}catch(Exception e){
			e.printStackTrace();
		}
		return AjaxJson.success("刪除失敗");
	}
	
	@RequestMapping(value="/fetchUrl",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson fetchUrl(String id,HttpServletRequest  request){
		try{
			picUrlService.fetchByUrlId(id);
			return AjaxJson.success("抓取请求成功");
		}catch(Exception e){
			e.printStackTrace();
		}
		return AjaxJson.success("抓取请求失敗");
	}
}
