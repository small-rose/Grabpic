package com.xiaocai.web.demo.dao.website;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaocai.web.demo.entity.website.WebPageBase;
import com.xiaocai.web.demo.entity.website.WebPageBaseExample;

public interface WebPageBaseMapper {
    long countByExample(WebPageBaseExample example);

    int deleteByExample(WebPageBaseExample example);

    int deleteByPrimaryKey(String pageId);

    int insert(WebPageBase record);

    int insertSelective(WebPageBase record);

    List<WebPageBase> selectByExampleWithBLOBs(WebPageBaseExample example);

    List<WebPageBase> selectByExample(WebPageBaseExample example);
    
    List<WebPageBase> selectByExampleWithCategory(WebPageBaseExample example);

    WebPageBase selectByPrimaryKey(String pageId);

    WebPageBase selectByPrimaryKeyWithCategory(String pageId);

    int updateByExampleSelective(@Param("record") WebPageBase record, @Param("example") WebPageBaseExample example);

    int updateByExampleWithBLOBs(@Param("record") WebPageBase record, @Param("example") WebPageBaseExample example);

    int updateByExample(@Param("record") WebPageBase record, @Param("example") WebPageBaseExample example);

    int updateByPrimaryKeySelective(WebPageBase record);

    int updateByPrimaryKeyWithBLOBs(WebPageBase record);

    int updateByPrimaryKey(WebPageBase record);
    
    void insertByBatch(@Param("list")List<WebPageBase> list);
}