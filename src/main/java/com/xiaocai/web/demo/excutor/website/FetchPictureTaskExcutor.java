package com.xiaocai.web.demo.excutor.website;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.xiaocai.web.demo.dao.website.WebLinkBaseMapper;
import com.xiaocai.web.demo.entity.website.WebLinkBase;
import com.xiaocai.web.demo.entity.website.WebPictrue;
import com.xiaocai.web.demo.service.website.FetchWebComService;

@Service
public class FetchPictureTaskExcutor {
	@Autowired
	private FetchWebComService fetchWebComService;
	@Autowired
	private WebLinkBaseMapper webLinkBaseMapper;
	@Autowired
	private TaskExecutor taskExecutor;
	
	
	public void addFetchPictrueTask(WebLinkBase linkBase) {
        this.taskExecutor.execute(new FetchPictrueThread(this.fetchWebComService,this.webLinkBaseMapper,linkBase));
    }
	
	private class FetchPictrueThread implements Runnable {

        private FetchWebComService fetchWebComService;
        private WebLinkBaseMapper webLinkBaseMapper;
        private WebLinkBase linkBase;
        private FetchPictrueThread(FetchWebComService fetchWebComService,WebLinkBaseMapper webLinkBaseMapper,WebLinkBase linkBase) {
            super();
            this.fetchWebComService = fetchWebComService;
            this.webLinkBaseMapper =webLinkBaseMapper;
            this.linkBase = linkBase;
        }
        
        @Override
        public void run() {
            System.out.println("FetchPictureTaskExcutor------begin... run...");
            List<WebPictrue> results = fetchWebComService.fetchPicsByLink(linkBase);
            if(results!=null && results.size()>0){
            	WebLinkBase linkBasePO = new WebLinkBase();
            	linkBasePO.setLinkId(linkBase.getLinkId());
            	linkBase.setPicTotal((long)results.size());
            	webLinkBaseMapper.updateByPrimaryKeySelective(linkBasePO);
            }
            System.out.println("FetchPictureTaskExcutor-------end... run...");
        }
	}
}
