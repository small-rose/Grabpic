package com.xiaocai.web.demo.dao.pic;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaocai.web.demo.entity.pic.PicUrl;
import com.xiaocai.web.demo.entity.pic.PicUrlExample;

public interface PicUrlMapper {
    long countByExample(PicUrlExample example);

    int deleteByExample(PicUrlExample example);

    int deleteByPrimaryKey(String id);

    int insert(PicUrl record);

    int insertSelective(PicUrl record);

    List<PicUrl> selectByExample(PicUrlExample example);

    PicUrl selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PicUrl record, @Param("example") PicUrlExample example);

    int updateByExample(@Param("record") PicUrl record, @Param("example") PicUrlExample example);

    int updateByPrimaryKeySelective(PicUrl record);

    int updateByPrimaryKey(PicUrl record);
}