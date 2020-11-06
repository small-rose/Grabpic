package com.xiaocai.web.demo.entity.pic;

public class Picture implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2656184856382533406L;

	private String picId;

	private String urlId;

	private String picAddr;

	private String picName;

	private String picOldname;

	private String picSuffix;

	private String picType;

	private Long picSize;

	private String picPath;

	private String isDel;

	private String isMark;

	private String addTime;

	private Long picNo;
	private String picDesc;
	private byte[] picContent;

	private PicUrl picUrl;

	public Picture() {

	}

	public Picture(String picId, String urlId, String picAddr, String picName, String picOldname, String picSuffix,
			String picType, Long picSize, String picPath, String isDel, String isMark, String addTime,
			byte[] picContent, PicUrl picUrl) {
		super();
		this.picId = picId;
		this.urlId = urlId;
		this.picAddr = picAddr;
		this.picName = picName;
		this.picOldname = picOldname;
		this.picSuffix = picSuffix;
		this.picType = picType;
		this.picSize = picSize;
		this.picPath = picPath;
		this.isDel = isDel;
		this.isMark = isMark;
		this.addTime = addTime;
		this.picContent = picContent;
		this.picUrl = picUrl;
	}

	public String getPicAddr() {
		return picAddr;
	}

	public void setPicAddr(String picAddr) {
		this.picAddr = picAddr;
	}

	public PicUrl getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(PicUrl picUrl) {
		this.picUrl = picUrl;
	}

	public String getPicId() {
		return picId;
	}

	public void setPicId(String picId) {
		this.picId = picId == null ? null : picId.trim();
	}

	public String getUrlId() {
		return urlId;
	}

	public void setUrlId(String urlId) {
		this.urlId = urlId == null ? null : urlId.trim();
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

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel == null ? null : isDel.trim();
	}

	public String getIsMark() {
		return isMark;
	}

	public void setIsMark(String isMark) {
		this.isMark = isMark == null ? null : isMark.trim();
	}

	public byte[] getPicContent() {
		return picContent;
	}

	public void setPicContent(byte[] picContent) {
		this.picContent = picContent;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Long getPicNo() {
		return picNo;
	}

	public void setPicNo(Long picNo) {
		this.picNo = picNo;
	}

	public String getPicDesc() {
		return picDesc;
	}

	public void setPicDesc(String picDesc) {
		this.picDesc = picDesc;
	}

	@Override
	public String toString() {
		return "Picture [picId=" + picId + ", urlId=" + urlId + ", picAddr=" + picAddr + ", picName=" + picName
				+ ", picOldname=" + picOldname + ", picSuffix=" + picSuffix + ", picType=" + picType + ", picSize="
				+ picSize + ", picPath=" + picPath + ", isDel=" + isDel + ", isMark=" + isMark + ", addTime=" + addTime
				+ ", picNo=" + picNo + ", picDesc=" + picDesc + ", picUrl=" + picUrl + "]";
	}
	
}