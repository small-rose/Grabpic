package com.xiaocai.web.demo.excutor.pic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.xiaocai.web.demo.entity.pic.PicUrl;
import com.xiaocai.web.demo.service.pic.FetchService;

@Service
public class FetchUrlTaskExcutor {
	@Autowired
	private FetchService fetchService;
	@Autowired
	private TaskExecutor taskExecutor;
	
	
	public void addFetchTask(String url,PicUrl picUrl) {
        this.taskExecutor.execute(new CatchPictureThread(url,this.fetchService,picUrl));
    }
	
	private class CatchPictureThread implements Runnable {
        private String url;
        private FetchService fetchService;
        private PicUrl picUrl;
        private CatchPictureThread(String url,FetchService fetchService,PicUrl picUrl) {
            super();
            this.url = url;
            this.fetchService = fetchService;
            this.picUrl = picUrl;
        }
        
        @Override
        public void run() {
            System.out.println("begin... run...");
            fetchService.fetchPicsByUrl(url,picUrl);
            System.out.println("end... run...");
        }
	}
}
