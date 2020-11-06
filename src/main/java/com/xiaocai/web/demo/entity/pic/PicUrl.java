package com.xiaocai.web.demo.entity.pic;

public class PicUrl implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2501119182181072665L;

	private String id;

    private String addrName;

    private String addrDesc;

    private String isDel;

    private String isMark;
    private String isFetch;
    
    private String addTime;
    private String addrCode;
    
    public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getAddrCode() {
		return addrCode;
	}

	public void setAddrCode(String addrCode) {
		this.addrCode = addrCode;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAddrName() {
        return addrName;
    }

    public void setAddrName(String addrName) {
        this.addrName = addrName == null ? null : addrName.trim();
    }

    public String getAddrDesc() {
        return addrDesc;
    }

    public void setAddrDesc(String addrDesc) {
        this.addrDesc = addrDesc == null ? null : addrDesc.trim();
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

	public String getIsFetch() {
		return isFetch;
	}

	public void setIsFetch(String isFetch) {
		this.isFetch = isFetch;
	}

	@Override
	public String toString() {
		return "PicUrl [id=" + id + ", addrName=" + addrName + ", addrDesc=" + addrDesc + ", isDel=" + isDel
				+ ", isMark=" + isMark + ", isFetch=" + isFetch + "]";
	}
    
}