package com.xiaocai.web.demo.controller.viewpic;

import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaocai.core.common.message.AjaxJson;

@Controller
public class ViewpicController {

	/**
	 * 抓图数据demo页面跳转
	 * @return
	 */
	@RequestMapping("/viewpicPage")
	public String viewpicPage(HttpServletRequest  request){
		String urlId = request.getParameter("urlId");
		request.setAttribute("urlId", urlId);
		return "com/ssm/web/viewpic/viewpicDemo";
	}
	
	
	@RequestMapping("/getImgStream")
	public void getImgStream(HttpServletRequest  request,HttpServletResponse  response){
		String urlId = request.getParameter("imgId");
		String path = "C:\\Users\\Think\\Pictures\\Camera Roll\\qingyunzhi22.jpg";
		path = "D:\\学习文档\\Java 详解 JVM 工作原理和流程.doc";
		try(FileInputStream fis = new FileInputStream(path);){
			//将byte[]转为InputStream
			//response.setContentType("image/jpeg"); 
			//response.setContentType("text/html"); 
			OutputStream outputStream = response.getOutputStream();
			byte[] content = new byte[fis.available()];
			int j = -1;
			while ((j = fis.read(content)) != -1) {
				outputStream.write(content, 0, j);
			}
			outputStream.flush();
			System.out.println(outputStream);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/getImgAjax")
	@ResponseBody
	public AjaxJson redownPic(HttpServletRequest  request){
		AjaxJson ajax = new AjaxJson();
		String msg = "";
		String path = "C:\\Users\\Think\\Pictures\\Camera Roll\\qingyunzhi22.jpg";
		try(FileInputStream fis = new FileInputStream(path);){

			return ajax.success(msg);
		}catch(Exception e){
			e.printStackTrace();
			msg = "获取图片失败";
		}
		return ajax.success(msg);
	}
	


}
