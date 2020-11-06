package com.xiaocai.web.demo.excutor.website;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.xiaocai.web.demo.dao.website.WebCategoryMapper;
import com.xiaocai.web.demo.entity.website.WebCategory;
import com.xiaocai.web.demo.entity.website.WebPageBase;
import com.xiaocai.web.demo.service.website.FetchWebComService;

@Service
public class FetchPageUrlTaskExcutor {
	@Autowired
	private FetchWebComService fetchWebComService;
	@Autowired
	private WebCategoryMapper webCategoryMapper;
	@Autowired
	private TaskExecutor taskExecutor;
	
	
	public void addFetchPageTask(WebCategory category) {
        this.taskExecutor.execute(new FetchPageUrlThread(this.fetchWebComService,this.webCategoryMapper,category));
    }
	
	private class FetchPageUrlThread implements Runnable {

        private FetchWebComService fetchWebComService;
        private WebCategoryMapper webCategoryMapper;
        private WebCategory category;
        private FetchPageUrlThread(FetchWebComService fetchWebComService,WebCategoryMapper webCategoryMapper,WebCategory category) {
            super();
            this.fetchWebComService = fetchWebComService;
            this.webCategoryMapper =webCategoryMapper;
            this.category = category;
        }
        
        @Override
        public void run() {
            System.out.println("FetchPageUrlTaskExcutor ------ begin... run...");
            List<WebPageBase> pages = fetchWebComService.fetchPages(category);
            if(pages!=null){
            	WebCategory categoryPO = new WebCategory();
            	categoryPO.setCategoryId(category.getCategoryId());
            	categoryPO.setPageTotal((long)pages.size());
            	webCategoryMapper.updateByPrimaryKeySelective(categoryPO);
            }else{
                System.out.println("FetchPageUrlTaskExcutor-------fetching result is null ...");
            }
            System.out.println("FetchPageUrlTaskExcutor --------end... run...");
        }
	}
}
