package com.xiaocai.web.demo.dao.website;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaocai.web.demo.entity.website.WebLinkBase;
import com.xiaocai.web.demo.entity.website.WebLinkBaseExample;

public interface WebLinkBaseMapper {
    long countByExample(WebLinkBaseExample example);

    int deleteByExample(WebLinkBaseExample example);

    int deleteByPrimaryKey(String linkId);

    int insert(WebLinkBase record);

    int insertSelective(WebLinkBase record);

    List<WebLinkBase> selectByExampleWithBLOBs(WebLinkBaseExample example);

    List<WebLinkBase> selectByExample(WebLinkBaseExample example);
    
    List<WebLinkBase> selectByExampleWithCtPg(WebLinkBaseExample example);
    
    WebLinkBase selectByPrimaryKeyWithCtPg(String linkId);
    
    WebLinkBase selectByPrimaryKey(String linkId);

    int updateByExampleSelective(@Param("record") WebLinkBase record, @Param("example") WebLinkBaseExample example);

    int updateByExampleWithBLOBs(@Param("record") WebLinkBase record, @Param("example") WebLinkBaseExample example);

    int updateByExample(@Param("record") WebLinkBase record, @Param("example") WebLinkBaseExample example);

    int updateByPrimaryKeySelective(WebLinkBase record);

    int updateByPrimaryKeyWithBLOBs(WebLinkBase record);

    int updateByPrimaryKey(WebLinkBase record);
    
    void insertByBatch(@Param("list")List<WebLinkBase> list);

	long countLinkTotalByPageId(String id);
}