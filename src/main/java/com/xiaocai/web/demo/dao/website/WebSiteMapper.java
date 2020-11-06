package com.xiaocai.web.demo.dao.website;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaocai.web.demo.entity.website.WebSite;
import com.xiaocai.web.demo.entity.website.WebSiteExample;

public interface WebSiteMapper {
    long countByExample(WebSiteExample example);

    int deleteByExample(WebSiteExample example);

    int deleteByPrimaryKey(String webId);

    int insert(WebSite record);

    int insertSelective(WebSite record);

    List<WebSite> selectByExampleWithBLOBs(WebSiteExample example);

    List<WebSite> selectByExample(WebSiteExample example);

    WebSite selectByPrimaryKey(String webId);

    int updateByExampleSelective(@Param("record") WebSite record, @Param("example") WebSiteExample example);

    int updateByExampleWithBLOBs(@Param("record") WebSite record, @Param("example") WebSiteExample example);

    int updateByExample(@Param("record") WebSite record, @Param("example") WebSiteExample example);

    int updateByPrimaryKeySelective(WebSite record);

    int updateByPrimaryKeyWithBLOBs(WebSite record);

    int updateByPrimaryKey(WebSite record);
}