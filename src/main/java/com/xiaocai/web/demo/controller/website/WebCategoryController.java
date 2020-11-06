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
import com.xiaocai.web.demo.entity.website.WebCategory;
import com.xiaocai.web.demo.service.website.WebCategoryService;
@Controller
public class WebCategoryController {

	@Autowired
	WebCategoryService webCategoryService;
	
	/**
	 * 抓图数据demo页面跳转
	 * @return
	 */
	@RequestMapping("/categoryPage")
	public String picPage(HttpServletRequest  request){
		
		return "com/ssm/web/website/webCategoryList";
	}
	
	/**
	 * 查询结果数据（分页查询）
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/categoryAjax")
	@ResponseBody
	public AjaxJson categoryAjax(@RequestParam(value="pn",defaultValue="1")Integer pn,@RequestParam(value="pageNum",defaultValue="5")Integer pageNum,String urlId,
			Model model,WebCategory category ,HttpServletRequest  request){
		//引入pageHelper分页查询
		// 查询之前调用，传入页码和每页大小
		PageHelper.startPage(pn, pageNum);
		// startPage后面紧跟查询
		List<WebCategory> list = webCategoryService.findAll(category ,request);
		// 使用pageInfo包装查询结果,只要将pageInfo交给页面
		// PageInfo封装了详细的分页信息（包含查询的数据），传入list,连续显示分页数
		PageInfo pageInfo = new PageInfo<>(list, pageNum);

		return AjaxJson.success().addData("pageInfo", pageInfo);
		
	}
	
	@RequestMapping(value="/editCategory",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson editCategory(String id,HttpServletRequest  request){
		
		WebCategory object = webCategoryService.selectByPrimaryKey(id);
		return AjaxJson.success().addData("object", object);
	}
	
	
	@RequestMapping(value="/saveCategory",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveCategory(WebCategory webCategory,HttpServletRequest  request){
		webCategoryService.saveCategory(webCategory);
		return AjaxJson.success("保存成功");
	}

	
	@RequestMapping(value="/delCategory/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public AjaxJson delCategory(@PathVariable("ids") String ids,HttpServletRequest  request){
		try{
			webCategoryService.delBatchCategory(ids);
			return AjaxJson.success("刪除成功");
		}catch(Exception e){
			e.printStackTrace();
		}
		return AjaxJson.success("刪除失敗");
	}
	
	@RequestMapping(value="/markCategory",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson markCategory(String id,HttpServletRequest  request){
		try{
			String msg = "";
			WebCategory category = webCategoryService.selectByPrimaryKey(id);
			webCategoryService.markById(category);
			if(ConstantHelp.Mark_nomal_1.equals(category.getCategoryMark())){
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
	
	@RequestMapping(value="/fetchList",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson fetchList(String id,HttpServletRequest  request){
		String msg = null;
		try{
			String tid = request.getRequestedSessionId();
			System.out.println("tid:"+tid);
			msg = webCategoryService.fetchListByCategoryId(id);
			
			return AjaxJson.success(msg);
		}catch(Exception e){
			e.printStackTrace();
			msg = "链接抓取出错";
		}
		return AjaxJson.success(msg);
	}
	
	@RequestMapping(value="/updatePageRules",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson updatePageRules(WebCategory webCategory,HttpServletRequest  request){
		webCategoryService.updateBatchRules(webCategory);
		return AjaxJson.success("批量修改成功");
	}
	//下拉框数据
	@RequestMapping(value = "/categorylist", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson categorylist(String webId, HttpServletRequest request) {
		List<WebCategory> list = webCategoryService.findWebSiteByWebId(webId, request);
		return AjaxJson.success().addData("list", list);
	}
}
