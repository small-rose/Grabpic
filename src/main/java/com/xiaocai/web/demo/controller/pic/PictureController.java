package com.xiaocai.web.demo.controller.pic;

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
import com.xiaocai.web.demo.entity.pic.Picture;
import com.xiaocai.web.demo.service.pic.PictureService;
@Controller
public class PictureController {

	@Autowired
	PictureService pictureService;
	/**
	 * 抓图数据demo页面跳转
	 * @return
	 */
	@RequestMapping("/picPage")
	public String picPage(HttpServletRequest  request){
		String urlId = request.getParameter("urlId");
		request.setAttribute("urlId", urlId);
		return "com/ssm/web/pic/pictureAjaxlist";
	}
	
	/**
	 * 查询结果数据（分页查询）
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/picsAjax")
	@ResponseBody
	public AjaxJson picsAjax(@RequestParam(value="pn",defaultValue="1")Integer pn,@RequestParam(value="pageNum",defaultValue="5")Integer pageNum,String urlId,
			Model model,Picture picture ,HttpServletRequest  request){
		//引入pageHelper分页查询
		// 查询之前调用，传入页码和每页大小
		PageHelper.startPage(pn, pageNum);
		// startPage后面紧跟查询
		List<Picture> pics = pictureService.findAll(urlId, picture ,request);
		// 使用pageInfo包装查询结果,只要将pageInfo交给页面
		// PageInfo封装了详细的分页信息（包含查询的数据），传入list,连续显示分页数
		PageInfo pageInfo = new PageInfo<>(pics, pageNum);

		return AjaxJson.success().addData("pageInfo", pageInfo);
		
	}
	
	@RequestMapping(value="/editPic",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson editPic(String id,HttpServletRequest  request){
		
		Picture picture = pictureService.selectByPrimaryKey(id);
		Picture picture2 = pictureService.viewPic(picture,request);
		return AjaxJson.success().addData("picture", picture2);
	}
	
	
	@RequestMapping(value="/savePic",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson savePic(Picture picture,HttpServletRequest  request){
		pictureService.renamePicture(picture);
		return AjaxJson.success("保存成功");
	}
	

	
	@RequestMapping(value="/delPic",method=RequestMethod.DELETE)
	@ResponseBody
	public AjaxJson delPic(String ids,HttpServletRequest  request){
		try{
			pictureService.delPicByIds(ids);
			return AjaxJson.success("刪除成功");
		}catch(Exception e){
			e.printStackTrace();
		}
		return AjaxJson.success("刪除失敗");
	}
	
	@RequestMapping(value="/delPic/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public AjaxJson delAllPic(@PathVariable("ids") String ids,HttpServletRequest  request){
		try{
			pictureService.delBatchPic(ids);
			return AjaxJson.success("刪除成功");
		}catch(Exception e){
			e.printStackTrace();
		}
		return AjaxJson.success("刪除失敗");
	}
	
	
	@RequestMapping(value="/markPic",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson markPic(String id,HttpServletRequest  request){
		try{
			String msg = "";
			Picture picture = pictureService.selectByPrimaryKey(id);
			pictureService.markById(picture);
			if(ConstantHelp.Mark_nomal_1.equals(picture.getIsMark())){
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
	
	@RequestMapping("/viewPic")
	public String viewPic(String id,HttpServletRequest  request){
		Picture picture = pictureService.selectByPrimaryKey(id);
		pictureService.viewPic(picture,request);
		request.setAttribute("fileName", picture.getPicOldname());
		return "com/ssm/web/pic/ViewFile";
	}
	
	@RequestMapping("/viewPicList")
	public String viewPicList(String ids,HttpServletRequest  request){
		List<Picture> picturelist= pictureService.selectByPrimaryKeys(ids);
		pictureService.viewPicList(picturelist,request);
		
		request.setAttribute("fileNames", picturelist);
		return "com/ssm/web/pic/ViewListFile";
	}
	
	@RequestMapping("/viewRollList")
	public String viewRollList(String ids,HttpServletRequest  request){
		List<Picture> picturelist= pictureService.selectByPrimaryKeys(ids);
		pictureService.viewPicList(picturelist,request);
		
		request.setAttribute("fileNames", picturelist);
		return "com/ssm/web/pic/ViewRollListFile";
	}
	
	@RequestMapping("/downPicList")
	public void downPicList(String urlId,HttpServletRequest  request,HttpServletResponse  response){
		List<Picture> picturelist= pictureService.findAll(urlId, null ,request);
		pictureService.downPicList(picturelist,request,response);
	}
	
	
	@RequestMapping(value="/redownPic/{id}",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson redownPic(@PathVariable("id") String id,HttpServletRequest  request){
		String msg = "";
		try{
			
			if(pictureService.redownPicByPicId(id)){
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
