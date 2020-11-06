package com.xiaocai.web.demo.service.website;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xiaocai.web.demo.constans.ConstantHelp;
import com.xiaocai.web.demo.dao.website.WebPictrueMapper;
import com.xiaocai.web.demo.entity.emp.DepartmentExample.Criteria;
import com.xiaocai.web.demo.entity.website.WebPictrue;
import com.xiaocai.web.demo.entity.website.WebPictrueExample;
import com.xiaocai.web.demo.service.pic.FetchService;
import com.xiaocai.web.utils.common.AttachemnetUtil;
import com.xiaocai.web.utils.common.DateUtils;
import com.xiaocai.web.utils.common.FileOptionUtil;
import com.xiaocai.web.utils.common.RequestPathUtil;
import com.xiaocai.web.utils.common.UtilVarKey;
import com.xiaocai.web.utils.picUtils.ZipFileUtil;

@Service
public class WebPictrueService {

	@Autowired
	private WebPictrueMapper webPictrueMapper;
	@Autowired
	private FetchService fetchService;
	
	public List<WebPictrue> findAll(String linkId, WebPictrue picture, HttpServletRequest request) {
		// TODO Auto-generated method stub
		WebPictrueExample example = new WebPictrueExample();
		com.xiaocai.web.demo.entity.website.WebPictrueExample.Criteria criteria = example.createCriteria();
		if(!StringUtils.isEmpty(linkId)){
			 //Criteria criteria = example.createCriteria();
			 criteria.andLinkIdWithLinkEqualTo(linkId);
		}
		if(picture!=null){
			if(!StringUtils.isEmpty(picture.getPicOldname())){
				 criteria.andPicOldnameLike(picture.getPicOldname());
			 }
			 if(!StringUtils.isEmpty(picture.getPicName())){
				 criteria.andPicNameLike(picture.getPicName());
			 }
			 if(!StringUtils.isEmpty(picture.getPicMark())){
				 criteria.andPicMarkEqualTo(picture.getPicMark());
			 }
			 if(!StringUtils.isEmpty(picture.getPicSuffix())){
				 criteria.andPicSuffixLike(picture.getPicSuffix());
			 }
		}
		example.setOrderByClause("pic_no,add_time desc");
		List<WebPictrue> pics = webPictrueMapper.selectByExampleWithlink(example);
		String target = request.getServletContext().getRealPath("/");
		
		target += "tmpImg";
		File filed = new File(target);// 可以是任何图片格式.jpg,.png等
		if (!filed.exists()) {
			filed.mkdirs();
		}
		//System.out.println("---imgPath---" + target);
		InputStream in = null;
		for(WebPictrue tmppic :pics){
			try {
				//FileUtils.copyFile(new File(picture.getPicPath()), filed );
				byte[] content = tmppic.getPicContent();
				if(content==null){
					continue ;
				}
				File file = new File(filed.getPath() + "/" + tmppic.getPicOldname());
				if(file.exists()){
					//System.out.println("--file-"+tmppic.getPicOldname()+"---exists--");
					continue ;
				}
				in = new ByteArrayInputStream(content);
				FileOutputStream fos;

				fos = new FileOutputStream(file);
				byte[] b = new byte[1024];
				int nRead = 0;
				while ((nRead = in.read(b)) != -1) {
					fos.write(b, 0, nRead);
				}
				fos.flush();
				fos.close();
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("copyFile exveption ....");
			}
		}
		return pics;
	}

	public WebPictrue selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return webPictrueMapper.selectByPrimaryKeyWithlink(id);
	}

	public WebPictrue viewPic(WebPictrue picture, HttpServletRequest request) {
		// TODO Auto-generated method stub
		String imgName = picture.getPicOldname();
		byte[] content = picture.getPicContent();
		if(content!=null){
			String path = AttachemnetUtil.getFileByBytes(request, content, imgName);
		}

		return picture;
	}

	public void renameWebpic(WebPictrue picture) {
		// TODO Auto-generated method stub
		WebPictrue tmp = selectByPrimaryKey(picture.getPicId());
		if(!picture.getPicName().endsWith(tmp.getPicSuffix())){
			picture.setPicName(picture.getPicName()+tmp.getPicSuffix());
		}
		webPictrueMapper.updateByPrimaryKeySelective(picture);
	}

	public int delBatchWebpic(String ids) {
		// TODO Auto-generated method stub
		String[] picIds = ids.split(","); 
		List<String> values = new ArrayList<>();
		for(String picId :picIds){
			values.add(picId);
		}
		WebPictrueExample example = new WebPictrueExample();
		com.xiaocai.web.demo.entity.website.WebPictrueExample.Criteria criteria = example.createCriteria();
		criteria.andPicIdIn(values);
		return webPictrueMapper.deleteByExample(example);
	}

	public void markById(WebPictrue picture) {
		// TODO Auto-generated method stub
		WebPictrue picturePO = new WebPictrue();
		picturePO.setPicId(picture.getPicId());
		if (ConstantHelp.Mark_nomal_1.equals(picture.getPicMark())) {
			picturePO.setPicMark(ConstantHelp.Mark_nostatus_0);
		} else {
			picturePO.setPicMark(ConstantHelp.Mark_nomal_1);
		}
		webPictrueMapper.updateByPrimaryKeySelective(picturePO);
	}

	public void viewPicList(List<WebPictrue> picturelist, HttpServletRequest request) {
		// TODO Auto-generated method stub
		for(WebPictrue picture :picturelist){
			String imgName = picture.getPicOldname();
 			byte[] content = picture.getPicContent();
 			if(content==null){
 				System.out.println(" picContent is null , id="+picture.getPicId());
 				continue;
 			}
			String path = AttachemnetUtil.getFileByBytes(request, content, imgName);
			request.setAttribute("linkId", picture.getLinkId());
		}
		
	}

	public List<WebPictrue> selectByPrimaryKeys(String ids) {
		// TODO Auto-generated method stub
		String[] picIds = ids.split(","); 
		List<String> tmps = new ArrayList<>();
		for(String picId :picIds){
			tmps.add(picId);
		}
		WebPictrueExample example = new WebPictrueExample();
		com.xiaocai.web.demo.entity.website.WebPictrueExample.Criteria criteria = example.createCriteria();
		criteria.andPicIdIn(tmps);
		List<WebPictrue> list = webPictrueMapper.selectByExampleWithlink(example);
		
		List<String> values = new ArrayList<>();
		for(WebPictrue picture :list){
			if(!values.contains(picture.getLinkId())){
				values.add(picture.getLinkId());
			}
		}
		WebPictrueExample example2 = new WebPictrueExample();
		com.xiaocai.web.demo.entity.website.WebPictrueExample.Criteria criteria2 = example2.createCriteria();
		criteria2.andLinkIdWithLinkIn(values);
		example2.setOrderByClause("pic_no");
		List<WebPictrue> result = webPictrueMapper.selectByExampleWithlink(example2);
		return result;
	}

	public void downPicList(List<WebPictrue> picturelist, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		File[] srcfile = new File[picturelist.size()];
		String imgpath = RequestPathUtil.getTmpImg(request,UtilVarKey.TPM_IMG);
		if(!imgpath.endsWith("/")){
			imgpath += "/";
		}
		File zipfile = new File(imgpath + DateUtils.date2Str(DateUtils.yyyymmddhhmmss)+".zip");
		for(int k =0; k< picturelist.size() ; k++){
			srcfile[k] = new File(imgpath + picturelist.get(k).getPicOldname());
		}
		// TODO Auto-generated method stub
		ZipFileUtil.zipFiles(srcfile, zipfile);
		FileOptionUtil.fileDownload(zipfile, request, response);
	}

	public boolean redownPicByPicId(String picId) {
		// TODO Auto-generated method stub
		WebPictrue picture = webPictrueMapper.selectByPrimaryKey(picId);
		String tmppath = fetchService.fetchPicsByPicUrl(picture.getPicAddr(),picture.getPicOldname(),picture.getPicSuffix());
		File tmpFile = new File(tmppath);
		byte[] content = AttachemnetUtil.convertFileToBytes(tmpFile);
		WebPictrue pictureVO = new WebPictrue();
		pictureVO.setPicId(picture.getPicId());
		pictureVO.setPicContent(content);
		// 重新下载只更新内容
		int i = webPictrueMapper.updateByPrimaryKeySelective(pictureVO);
		if (i > 0) {
			return true;
		}
		return false;
	}

	public List<WebPictrue> selectWebpicByLinkId(String linkId) {
		// TODO Auto-generated method stub
		WebPictrueExample example2 = new WebPictrueExample();
		com.xiaocai.web.demo.entity.website.WebPictrueExample.Criteria criteria2 = example2.createCriteria();
		criteria2.andLinkIdWithLinkEqualTo(linkId);
		example2.setOrderByClause("pic_no asc ");
		List<WebPictrue> result = webPictrueMapper.selectByExampleWithlink(example2);
		return result;
	}

}
