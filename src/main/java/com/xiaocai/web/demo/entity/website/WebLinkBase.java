package com.xiaocai.web.demo.entity.website;

import java.io.Serializable;

public class WebLinkBase implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4678997360423485005L;

	private String linkId;

    private String categoryId;
    
    private String pageId;

    private Long linkNo;

    private String linkHerf;

    private String linkName;

    private String linkValid;

    private String addTime;

    private String linkConetent;
    
    private String linkMark ;
    
    private String imgTag ;
    private String imgTagAttr ;
    private Long picTotal;
    
    private WebPageBase webPageBase;
    private WebCategory webCategory;
    
    public String getLinkMark() {
		return linkMark;
	}

	public void setLinkMark(String linkMark) {
		this.linkMark = linkMark;
	}

	public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId == null ? null : linkId.trim();
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId == null ? null : pageId.trim();
    }

    public Long getLinkNo() {
        return linkNo;
    }

    public void setLinkNo(Long linkNo) {
        this.linkNo = linkNo;
    }

    public String getLinkHerf() {
        return linkHerf;
    }

    public void setLinkHerf(String linkHerf) {
        this.linkHerf = linkHerf == null ? null : linkHerf.trim();
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName == null ? null : linkName.trim();
    }

    public String getLinkValid() {
        return linkValid;
    }

    public void setLinkValid(String linkValid) {
        this.linkValid = linkValid == null ? null : linkValid.trim();
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime == null ? null : addTime.trim();
    }

    public String getLinkConetent() {
        return linkConetent;
    }

    public void setLinkConetent(String linkConetent) {
        this.linkConetent = linkConetent == null ? null : linkConetent.trim();
    }

	public WebCategory getWebCategory() {
		return webCategory;
	}

	public void setWebCategory(WebCategory webCategory) {
		this.webCategory = webCategory;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public WebPageBase getWebPageBase() {
		return webPageBase;
	}

	public void setWebPageBase(WebPageBase webPageBase) {
		this.webPageBase = webPageBase;
	}

	@Override
	public String toString() {
		return "WebLinkBase [linkId=" + linkId + ", pageId=" + pageId + ", linkNo=" + linkNo + ", linkHerf=" + linkHerf
				+ ", linkName=" + linkName + ", linkValid=" + linkValid + ", addTime=" + addTime + ", linkMark="
				+ linkMark + ", webCategory=" + webCategory + "]";
	}

	public String getImgTag() {
		return imgTag;
	}

	public void setImgTag(String imgTag) {
		this.imgTag = imgTag;
	}

	public String getImgTagAttr() {
		return imgTagAttr;
	}

	public void setImgTagAttr(String imgTagAttr) {
		this.imgTagAttr = imgTagAttr;
	}

	public Long getPicTotal() {
		return picTotal;
	}

	public void setPicTotal(Long picTotal) {
		this.picTotal = picTotal;
	}
    
}