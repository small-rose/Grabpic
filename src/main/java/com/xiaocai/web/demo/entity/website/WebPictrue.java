package com.xiaocai.web.demo.entity.website;

import java.io.Serializable;
import java.util.Arrays;

public class WebPictrue implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7118977047003665765L;

	private String picId;

    private String linkId;

    private String picAddr;

    private String picName;

    private String picOldname;

    private String picDesc;

    private String picSuffix;

    private String picType;

    private Long picSize;

    private String picPath;

    private String picDel;

    private String picMark;

    private Integer picNo;

    private String addTime;

    private byte[] picContent;
    private WebLinkBase webLinkBase;
    
    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId == null ? null : picId.trim();
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId == null ? null : linkId.trim();
    }

    public String getPicAddr() {
        return picAddr;
    }

    public void setPicAddr(String picAddr) {
        this.picAddr = picAddr == null ? null : picAddr.trim();
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName == null ? null : picName.trim();
    }

    public String getPicOldname() {
        return picOldname;
    }

    public void setPicOldname(String picOldname) {
        this.picOldname = picOldname == null ? null : picOldname.trim();
    }

    public String getPicDesc() {
        return picDesc;
    }

    public void setPicDesc(String picDesc) {
        this.picDesc = picDesc == null ? null : picDesc.trim();
    }

    public String getPicSuffix() {
        return picSuffix;
    }

    public void setPicSuffix(String picSuffix) {
        this.picSuffix = picSuffix == null ? null : picSuffix.trim();
    }

    public String getPicType() {
        return picType;
    }

    public void setPicType(String picType) {
        this.picType = picType == null ? null : picType.trim();
    }

    public Long getPicSize() {
        return picSize;
    }

    public void setPicSize(Long picSize) {
        this.picSize = picSize;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath == null ? null : picPath.trim();
    }

    public String getPicDel() {
        return picDel;
    }

    public void setPicDel(String picDel) {
        this.picDel = picDel == null ? null : picDel.trim();
    }

    public String getPicMark() {
        return picMark;
    }

    public void setPicMark(String picMark) {
        this.picMark = picMark == null ? null : picMark.trim();
    }

    public Integer getPicNo() {
        return picNo;
    }

    public void setPicNo(Integer picNo) {
        this.picNo = picNo;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime == null ? null : addTime.trim();
    }

    public byte[] getPicContent() {
        return picContent;
    }

    public void setPicContent(byte[] picContent) {
        this.picContent = picContent;
    }


	public WebLinkBase getWebLinkBase() {
		return webLinkBase;
	}

	public void setWebLinkBase(WebLinkBase webLinkBase) {
		this.webLinkBase = webLinkBase;
	}

	@Override
	public String toString() {
		return "WebPictrue [picId=" + picId + ", linkId=" + linkId + ", picAddr=" + picAddr + ", picName=" + picName
				+ ", picOldname=" + picOldname + ", picDesc=" + picDesc + ", picSuffix=" + picSuffix + ", picType="
				+ picType + ", picSize=" + picSize + ", picPath=" + picPath + ", picDel=" + picDel + ", picMark="
				+ picMark + ", picNo=" + picNo + ", addTime=" + addTime + ", picContent=" + Arrays.toString(picContent)
				+ ", webLinkBase=" + webLinkBase + "]";
	}
    
    
}