package com.xiaocai.web.demo.entity.website;

import java.io.Serializable;

public class WebPageBase implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 7468050784024476902L;

	private String pageId;

    private String categoryId;

    private Long pageNo;

    private String pageHerf;

    private String pageName;

    private String addTime;

    private String pageMark;

    private String linkTag;

    private String linkTagAttr;

    private String linkKeys;
    private Long linkTotal;
    
    private String pageConetent;

    private WebCategory webCategory;
    
    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId == null ? null : pageId.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public Long getPageNo() {
        return pageNo;
    }

    public void setPageNo(Long pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageHerf() {
        return pageHerf;
    }

    public void setPageHerf(String pageHerf) {
        this.pageHerf = pageHerf == null ? null : pageHerf.trim();
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName == null ? null : pageName.trim();
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime == null ? null : addTime.trim();
    }

    public String getPageMark() {
        return pageMark;
    }

    public void setPageMark(String pageMark) {
        this.pageMark = pageMark == null ? null : pageMark.trim();
    }

    public String getPageConetent() {
        return pageConetent;
    }

    public void setPageConetent(String pageConetent) {
        this.pageConetent = pageConetent == null ? null : pageConetent.trim();
    }

	public String getLinkTag() {
		return linkTag;
	}

	public void setLinkTag(String linkTag) {
		this.linkTag = linkTag;
	}

	public String getLinkTagAttr() {
		return linkTagAttr;
	}

	public void setLinkTagAttr(String linkTagAttr) {
		this.linkTagAttr = linkTagAttr;
	}

	public String getLinkKeys() {
		return linkKeys;
	}

	public void setLinkKeys(String linkKeys) {
		this.linkKeys = linkKeys;
	}

	@Override
	public String toString() {
		return "WebPageBase [pageId=" + pageId + ", categoryId=" + categoryId + ", pageNo=" + pageNo + ", pageHerf="
				+ pageHerf + ", pageName=" + pageName + ", addTime=" + addTime + ", pageMark=" + pageMark + ", linkTag="
				+ linkTag + ", linkTagAttr=" + linkTagAttr + ", linkKeys=" + linkKeys + "]";
	}

	public WebCategory getWebCategory() {
		return webCategory;
	}

	public void setWebCategory(WebCategory webCategory) {
		this.webCategory = webCategory;
	}

	public Long getLinkTotal() {
		return linkTotal;
	}

	public void setLinkTotal(Long linkTotal) {
		this.linkTotal = linkTotal;
	}
    
}