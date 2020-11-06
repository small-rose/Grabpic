package com.xiaocai.web.utils.common;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

public class RequestPathUtil {

	public static String getTmpImg(HttpServletRequest req,String path){
		String imgPath = req.getServletContext().getRealPath("/");
		imgPath += path;
		File filed = new File(imgPath+"/");// 可以是任何
		if (!filed.exists()) {
			filed.mkdirs();
		}
		System.out.println(" imgPath[ "+path+" ] is [ "+ imgPath +" ]");
		return imgPath;
	}
}
