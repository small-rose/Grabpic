package com.xiaocai.web.demo.entity.website;

import java.io.Serializable;

public class WebCategory implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 7555313946794131028L;

	private String categoryId;

    private String webId;

    private String categoryUrl;

    private String categoryName;

    private String parentId;

    private String categoryMark;

    private String addTime;

    private String pageTag;
    private String pageTagAttr;
    private String pageKeys;
    private Long pageTotal;
    
    private String categoryContent;

    private WebSite webSite ;
    
    private WebCategory parent ;
    
    public WebSite getWebSite() {
		return webSite;
	}

	public void setWebSite(WebSite webSite) {
		this.webSite = webSite;
	}

	public WebCategory getParent() {
		return parent;
	}

	public void setParent(WebCategory parent) {
		this.parent = parent;
	}

	public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getWebId() {
        return webId;
    }

    public void setWebId(String webId) {
        this.webId = webId == null ? null : webId.trim();
    }

    public String getCategoryUrl() {
        return categoryUrl;
    }

    public void setCategoryUrl(String categoryUrl) {
        this.categoryUrl = categoryUrl == null ? null : categoryUrl.trim();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getCategoryMark() {
        return categoryMark;
    }

    public void setCategoryMark(String categoryMark) {
        this.categoryMark = categoryMark == null ? null : categoryMark.trim();
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime == null ? null : addTime.trim();
    }

    public String getCategoryContent() {
        return categoryContent;
    }

    public void setCategoryContent(String categoryContent) {
        this.categoryContent = categoryContent == null ? null : categoryContent.trim();
    }


	public String getPageTag() {
		return pageTag;
	}

	public void setPageTag(String pageTag) {
		this.pageTag = pageTag;
	}

	public String getPageTagAttr() {
		return pageTagAttr;
	}

	public void setPageTagAttr(String pageTagAttr) {
		this.pageTagAttr = pageTagAttr;
	}

	public String getPageKeys() {
		return pageKeys;
	}

	public void setPageKeys(String pageKeys) {
		this.pageKeys = pageKeys;
	}

	public Long getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(Long pageTotal) {
		this.pageTotal = pageTotal;
	}

	@Override
	public String toString() {
		return "WebCategory [categoryId=" + categoryId + ", webId=" + webId + ", categoryUrl=" + categoryUrl
				+ ", categoryName=" + categoryName + ", parentId=" + parentId + ", categoryMark=" + categoryMark
				+ ", addTime=" + addTime + ", pageTag=" + pageTag + ", pageTagAttr=" + pageTagAttr + ", pageKeys="
				+ pageKeys + ", webSite=" + webSite + ", parent=" + parent + "]";
	}
    
    
}