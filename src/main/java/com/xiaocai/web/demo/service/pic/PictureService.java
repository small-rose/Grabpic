package com.xiaocai.web.demo.service.pic;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xiaocai.web.demo.constans.ConstantHelp;
import com.xiaocai.web.demo.dao.pic.PictureMapper;
import com.xiaocai.web.demo.entity.pic.PicUrl;
import com.xiaocai.web.demo.entity.pic.Picture;
import com.xiaocai.web.demo.entity.pic.PictureExample;
import com.xiaocai.web.demo.entity.website.WebPageBaseExample.Criteria;
import com.xiaocai.web.utils.common.AttachemnetUtil;
import com.xiaocai.web.utils.common.DateUtils;
import com.xiaocai.web.utils.common.FileOptionUtil;
import com.xiaocai.web.utils.common.RequestPathUtil;
import com.xiaocai.web.utils.common.UtilVarKey;
import com.xiaocai.web.utils.picUtils.ZipFileUtil;

@Service
public class PictureService {

	@Autowired
	private PictureMapper pictureMapper;
	@Autowired
	private FetchService fetchService;
	
	public void savePicture(String picAddr,String filePath,PicUrl picUrl,String picDesc){
		//检查图片是否存在、大小是否合适、宽高大小
		if(FileOptionUtil.checkImgSizePx(filePath)){
			return ;
		}
		File file = new File(filePath);
		String fileName = filePath.substring(filePath.lastIndexOf("\\")+1);
		//MultipartFile multipartFile = null;
		try {
			PictureExample example = new PictureExample();
			com.xiaocai.web.demo.entity.pic.PictureExample.Criteria criteria = example.createCriteria();
			criteria.andPicOldnameEqualTo(fileName);
			criteria.andPicAddrEqualTo(picAddr);
			List<Picture>  piclist = pictureMapper.selectByExample(example);
			if(piclist!=null && piclist.size()>0){
				System.out.println(" file exist ....");
				return ;
			}
			
			PictureExample example2 = new PictureExample();
			com.xiaocai.web.demo.entity.pic.PictureExample.Criteria criteria2 = example.createCriteria();
			criteria2.andPicAddrEqualTo(picAddr);
			List<Picture>  allList = pictureMapper.selectByExample(example2);
			Long no = (long) (allList!=null ? allList.size() : 1);
			//multipartFile = new MockMultipartFile(file.getName(),new FileInputStream(file));
			byte[] content  = AttachemnetUtil.convertFileToBytes(file);
			Picture picture = new Picture();
			picture.setPicContent(content);
			picture.setPicPath(filePath);
			picture.setPicOldname(fileName);
			picture.setPicSize(file.length());
			picture.setPicDesc(picDesc);
			String suffix = null;
			if(fileName.contains(".")){
			    suffix = fileName.substring(fileName.lastIndexOf("."));
			}else{
				suffix = filePath.substring(filePath.lastIndexOf("."));
			}
			
			picture.setPicSuffix(suffix);
			picture.setIsDel(ConstantHelp.Del_nomal_1);
			picture.setIsMark(ConstantHelp.Mark_nostatus_0);
			picture.setPicId(UUID.randomUUID().toString());
			picture.setUrlId(picUrl.getId());
			picture.setPicUrl(picUrl);
			picture.setPicAddr(picAddr);
			picture.setAddTime(DateUtils.date2Str(DateUtils.datetimeFormat));
			picture.setPicNo(no);
			System.out.println(" picture --->" + picture);
			pictureMapper.insertSelective(picture);
			//转移文件到指定目录

			
			FileOptionUtil.fileMove(file, UtilVarKey.TPM_IMG_SAVE,picDesc,fileName);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("file save exception ....");
		}
	}
	

	public List<Picture> findAll(String urlId,Picture picture,HttpServletRequest  request) {
		// TODO Auto-generated method stub
		PictureExample example = new PictureExample();
		com.xiaocai.web.demo.entity.pic.PictureExample.Criteria criteria = example.createCriteria();
		if(!StringUtils.isEmpty(urlId)){
			 //Criteria criteria = example.createCriteria();
			 criteria.andUrlIdEqualTo(urlId);
		}
		if(picture!=null){
			if(!StringUtils.isEmpty(picture.getPicOldname())){
				 criteria.andPicOldnameLike(picture.getPicOldname());
			 }
			 if(!StringUtils.isEmpty(picture.getPicName())){
				 criteria.andPicNameLike(picture.getPicName());
			 }
			 if(!StringUtils.isEmpty(picture.getIsMark())){
				 criteria.andIsMarkEqualTo(picture.getIsMark());
			 }
			 if(!StringUtils.isEmpty(picture.getPicSuffix())){
				 criteria.andPicSuffixLike(picture.getPicSuffix());
			 }
		}
		example.setOrderByClause("pic_no,add_time desc");
		List<Picture> pics = pictureMapper.selectByExampleWithUrl(example);
		String target = request.getServletContext().getRealPath("/");
		
		target += "tmpImg";
		File filed = new File(target);// 可以是任何图片格式.jpg,.png等
		if (!filed.exists()) {
			filed.mkdirs();
		}
		//System.out.println("---imgPath---" + target);
		InputStream in = null;
		for(Picture tmppic :pics){
			try {
				//FileUtils.copyFile(new File(picture.getPicPath()), filed );
				byte[] content = tmppic.getPicContent();
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


	public Picture selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return pictureMapper.selectByPrimaryKey(id);
	}


	public void delUrlById(String id) {
		// TODO Auto-generated method stub
		pictureMapper.deleteByPrimaryKey(id);
	}


	public void renamePicture(Picture picture) {
		// TODO Auto-generated method stub
		Picture tmp = selectByPrimaryKey(picture.getPicId());
		if(!picture.getPicName().endsWith(tmp.getPicSuffix())){
			picture.setPicName(picture.getPicName()+tmp.getPicSuffix());
		}
		
		pictureMapper.updateByPrimaryKeySelective(picture);
	}


	public Picture viewPic(Picture picture,HttpServletRequest  request) {
		// TODO Auto-generated method stub
		
		//File file = new File(picture.getPicPath());
//		if(!file.exists()){
			String imgName = picture.getPicOldname();
 			byte[] content = picture.getPicContent();
			String path = AttachemnetUtil.getFileByBytes(request, content, imgName);
			//picture.setPicPath(path);
//		}else{
//			
//			picture.setPicPath(path);
//		}
		return picture;
	}


	public void markById(Picture picture) {
		// TODO Auto-generated method stub
		Picture picturePO = new Picture();
		picturePO.setPicId(picture.getPicId());
		if(ConstantHelp.Mark_nomal_1.equals(picture.getIsMark())){
			picturePO.setIsMark(ConstantHelp.Mark_nostatus_0);
		}else{
			picturePO.setIsMark(ConstantHelp.Mark_nomal_1);
		}
		pictureMapper.updateByPrimaryKeySelective(picturePO);
	}


	public void delPicByIds(String ids) {
		// TODO Auto-generated method stub
		String[] picIds = ids.split(","); 
		for(String picId :picIds){
			pictureMapper.deleteByPrimaryKey(picId);
		}
	}
	
	public void delBatchPic(String ids) {
		// TODO Auto-generated method stub
		String[] picIds = ids.split(","); 
		List<String> values = new ArrayList<>();
		for(String picId :picIds){
			values.add(picId);
		}
		PictureExample example = new PictureExample();
		com.xiaocai.web.demo.entity.pic.PictureExample.Criteria criteria = example.createCriteria();
		criteria.andPicIdIn(values);
		pictureMapper.deleteByExample(example);
	}


	public List<Picture> selectByPrimaryKeys(String ids) {
		// TODO Auto-generated method stub
		String[] picIds = ids.split(","); 
		List<String> tmps = new ArrayList<>();
		for(String picId :picIds){
			tmps.add(picId);
		}
		PictureExample example = new PictureExample();
		com.xiaocai.web.demo.entity.pic.PictureExample.Criteria criteria = example.createCriteria();
		criteria.andPicIdIn(tmps);
		List<Picture> list = pictureMapper.selectByExampleWithUrl(example);
		
		List<String> values = new ArrayList<>();
		for(Picture picture :list){
			values.add(picture.getUrlId());
		}
		PictureExample example2 = new PictureExample();
		com.xiaocai.web.demo.entity.pic.PictureExample.Criteria criteria2 = example2.createCriteria();
		criteria2.andUrlIdIn(values);
		example2.setOrderByClause("pic_no");
		List<Picture> result = pictureMapper.selectByExampleWithUrl(example2);
		return result;
	}


	public void viewPicList(List<Picture> picturelist, HttpServletRequest request) {
		// TODO Auto-generated method stub
		for(Picture picture :picturelist){
			String imgName = picture.getPicOldname();
 			byte[] content = picture.getPicContent();
			String path = AttachemnetUtil.getFileByBytes(request, content, imgName);
			request.setAttribute("urlId", picture.getUrlId());
		}
	}


	public void downPicList(List<Picture> picturelist, HttpServletRequest request, HttpServletResponse response) {
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
		Picture picture = pictureMapper.selectByPrimaryKey(picId);
		String tmppath = fetchService.fetchPicsByPicUrl(picture.getPicAddr(),picture.getPicOldname(),picture.getPicSuffix());
		File tmpFile = new File(tmppath);
		byte[] content  = AttachemnetUtil.convertFileToBytes(tmpFile);
		Picture pictureVO = new Picture();
		pictureVO.setPicId(picture.getPicId());
		pictureVO.setPicContent(content);
		//重新下载只更新内容
		int i=pictureMapper.updateByPrimaryKeySelective(pictureVO);
		if(i>0){
			return true;
		}
		return false;
	}
}
