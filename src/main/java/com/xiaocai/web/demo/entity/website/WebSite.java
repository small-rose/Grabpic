package com.xiaocai.web.demo.entity.website;

import java.io.Serializable;

public class WebSite implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1258560816074422898L;

	private String webId;

    private String webUrl;

    private String webName;

    private String webDesc;

    private String webOpen;

    private String addTime;

    private String webContent;

    public String getWebId() {
        return webId;
    }

    public void setWebId(String webId) {
        this.webId = webId == null ? null : webId.trim();
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl == null ? null : webUrl.trim();
    }

    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        this.webName = webName == null ? null : webName.trim();
    }

    public String getWebDesc() {
        return webDesc;
    }

    public void setWebDesc(String webDesc) {
        this.webDesc = webDesc == null ? null : webDesc.trim();
    }

    public String getWebOpen() {
        return webOpen;
    }

    public void setWebOpen(String webOpen) {
        this.webOpen = webOpen == null ? null : webOpen.trim();
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime == null ? null : addTime.trim();
    }

    public String getWebContent() {
        return webContent;
    }

    public void setWebContent(String webContent) {
        this.webContent = webContent == null ? null : webContent.trim();
    }
}