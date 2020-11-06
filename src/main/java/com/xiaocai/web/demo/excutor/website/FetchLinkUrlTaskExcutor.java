package com.xiaocai.web.demo.excutor.website;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.xiaocai.web.demo.dao.website.WebPageBaseMapper;
import com.xiaocai.web.demo.entity.website.WebLinkBase;
import com.xiaocai.web.demo.entity.website.WebPageBase;
import com.xiaocai.web.demo.service.website.FetchWebComService;

@Service
public class FetchLinkUrlTaskExcutor {
	@Autowired
	private FetchWebComService fetchWebComService;
	@Autowired
	private WebPageBaseMapper webPageBaseMapper;
	@Autowired
	private TaskExecutor taskExecutor;
	
	
	public void addFetchLinkTask(WebPageBase pageBase) {
        this.taskExecutor.execute(new FetchPageUrlThread(this.fetchWebComService,this.webPageBaseMapper,pageBase));
    }
	
	private class FetchPageUrlThread implements Runnable {

        private FetchWebComService fetchWebComService;
        private WebPageBaseMapper webPageBaseMapper;
        private WebPageBase pageBase;
        private FetchPageUrlThread(FetchWebComService fetchWebComService,WebPageBaseMapper webPageBaseMapper,WebPageBase pageBase) {
            super();
            this.fetchWebComService = fetchWebComService;
            this.webPageBaseMapper = webPageBaseMapper;
            this.pageBase = pageBase;
        }
        
        @SuppressWarnings("unused")
		@Override
        public void run() {
            System.out.println("FetchLinkUrlTaskExcutor ------begin... run...");
            List<WebLinkBase> pages = fetchWebComService.fetchLinks(pageBase);
            System.out.println(" pages "+pages.size());
            if(pages!=null && pages.size()>0){
            	WebPageBase webPageBase = new WebPageBase();
            	webPageBase.setCategoryId(pageBase.getCategoryId());
            	//webPageBase.setLinkTotal(pages.size());
            	//webPageBaseMapper.updateByPrimaryKeySelective(webPageBase);
            }else{
            	System.out.println("FetchLinkUrlTaskExcutor-------fetching result is null ...");
            }
            System.out.println("FetchLinkUrlTaskExcutor-------end... run...");
        }
	}
}
