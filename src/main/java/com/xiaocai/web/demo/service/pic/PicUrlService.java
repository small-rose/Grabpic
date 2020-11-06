package com.xiaocai.web.demo.service.pic;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.util.StringUtil;
import com.xiaocai.web.demo.constans.ConstantHelp;
import com.xiaocai.web.demo.dao.pic.PicUrlMapper;
import com.xiaocai.web.demo.entity.pic.PicUrl;
import com.xiaocai.web.demo.entity.pic.PicUrlExample;
import com.xiaocai.web.demo.excutor.pic.FetchUrlTaskExcutor;
import com.xiaocai.web.utils.common.DateUtils;

@Service
public class PicUrlService {

	@Autowired
	PicUrlMapper picUrlMapper;
	@Autowired
	FetchUrlTaskExcutor fetchUrlTaskExcutor;
	
	public PicUrl savePicUrl(PicUrl picUrl){
		
		picUrlMapper.insert(picUrl);
		return picUrl;
	}

	public List<PicUrl> findAll() {
		// TODO Auto-generated method stub
		PicUrlExample example = new PicUrlExample();
		example.setOrderByClause("add_time desc");
		List<PicUrl>  urls = picUrlMapper.selectByExample(example);
		return urls;
	}

	public void urlSave(PicUrl picUrl) {
		String maxCode = getMaxCode();
		if(StringUtil.isEmpty(picUrl.getId()) ){
			picUrl.setId(UUID.randomUUID().toString());
			picUrl.setIsDel(ConstantHelp.Del_nomal_1);
			picUrl.setIsMark(ConstantHelp.Mark_nostatus_0);
			picUrl.setIsFetch(ConstantHelp.Fetch_no_0);
			picUrl.setAddTime(DateUtils.date2Str(DateUtils.datetimeFormat));
			picUrl.setAddrCode(maxCode);
			System.out.println("save picUrl "+picUrl);
			picUrlMapper.insertSelective(picUrl);
		}else{
			PicUrl picUrlPO = selectByPrimaryKey(picUrl.getId());
			picUrlPO.setAddrName(picUrl.getAddrName());
			picUrlPO.setAddrDesc(picUrl.getAddrDesc());
			System.out.println("update picUrl "+picUrlPO);
			picUrlMapper.updateByPrimaryKeySelective(picUrlPO);
		}
		
	}

	private String getMaxCode() {
		// TODO Auto-generated method stub
		String code = "URL_";
		long num = picUrlMapper.countByExample(null);
		String date = DateUtils.date2Str(DateUtils.yyyyMMdd);
		code += date+"_";
		if(num<10){
			code +=  "000"+num;
		}else if(num >= 10 && num < 100 ){
			code += "00"+num;
		}else if(num >= 100){
			code += "0"+num;
		}
		return code;
	}

	public PicUrl selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return picUrlMapper.selectByPrimaryKey(id);
	}

	public void delUrlById(String id) {
		// TODO Auto-generated method stub
		picUrlMapper.deleteByPrimaryKey(id);
	}

	public void fetchByUrlId(String id) {
		// TODO Auto-generated method stub
		PicUrl pic = selectByPrimaryKey(id);
		fetchUrlTaskExcutor.addFetchTask(pic.getAddrName(),pic);
	}
}
