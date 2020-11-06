package com.xiaocai.web.demo.dao.website;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaocai.web.demo.entity.website.WebPictrue;
import com.xiaocai.web.demo.entity.website.WebPictrueExample;

public interface WebPictrueMapper {
    long countByExample(WebPictrueExample example);

    int deleteByExample(WebPictrueExample example);

    int deleteByPrimaryKey(String picId);

    int insert(WebPictrue record);

    int insertSelective(WebPictrue record);

    List<WebPictrue> selectByExampleWithBLOBs(WebPictrueExample example);

    List<WebPictrue> selectByExample(WebPictrueExample example);

    WebPictrue selectByPrimaryKey(String picId);

    int updateByExampleSelective(@Param("record") WebPictrue record, @Param("example") WebPictrueExample example);

    int updateByExampleWithBLOBs(@Param("record") WebPictrue record, @Param("example") WebPictrueExample example);

    int updateByExample(@Param("record") WebPictrue record, @Param("example") WebPictrueExample example);

    int updateByPrimaryKeySelective(WebPictrue record);

    int updateByPrimaryKeyWithBLOBs(WebPictrue record);

    int updateByPrimaryKey(WebPictrue record);

	List<WebPictrue> selectByExampleWithlink(WebPictrueExample example);
	
	WebPictrue selectByPrimaryKeyWithlink(String picId);
	
	void insertByBatch(@Param("list")List<WebPictrue> webPictrues);
}