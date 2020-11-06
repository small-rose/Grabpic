package com.xiaocai.web.demo.dao.website;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaocai.web.demo.entity.website.WebCategory;
import com.xiaocai.web.demo.entity.website.WebCategoryExample;

public interface WebCategoryMapper {
    long countByExample(WebCategoryExample example);

    int deleteByExample(WebCategoryExample example);

    int deleteByPrimaryKey(String categoryId);

    int insert(WebCategory record);

    int insertSelective(WebCategory record);

    List<WebCategory> selectByExampleWithBLOBs(WebCategoryExample example);

    List<WebCategory> selectByExample(WebCategoryExample example);

    WebCategory selectByPrimaryKey(String categoryId);
    
    List<WebCategory> selectByExampleWithWebsite(WebCategoryExample example);

    WebCategory selectByPrimaryKeyWithWebsite(String categoryId);

    int updateByExampleSelective(@Param("record") WebCategory record, @Param("example") WebCategoryExample example);

    int updateByExampleWithBLOBs(@Param("record") WebCategory record, @Param("example") WebCategoryExample example);

    int updateByExample(@Param("record") WebCategory record, @Param("example") WebCategoryExample example);

    int updateByPrimaryKeySelective(WebCategory record);

    int updateByPrimaryKeyWithBLOBs(WebCategory record);

    int updateByPrimaryKey(WebCategory record);
}