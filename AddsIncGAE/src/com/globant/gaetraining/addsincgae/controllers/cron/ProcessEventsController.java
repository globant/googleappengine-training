package com.globant.gaetraining.addsincgae.controllers.cron;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.appengine.api.taskqueue.LeaseOptions;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskHandle;

@Controller
public class ProcessEventsController {
	private static final int badge = 5000;
	private static final int minutesLease = 15;
	
	@RequestMapping("/process_events")
	public void processEventsBatch(){
		Queue q = QueueFactory.getQueue("events-queue");

		List<TaskHandle> tasks = null, toDelete = new ArrayList<TaskHandle>(), toRetry = new ArrayList<TaskHandle>();
		do{
			for(TaskHandle task: tasks){
				if(processTask(new String(task.getPayload()))){
					toDelete.add(task);
				}else{
					q.modifyTaskLease(task, 0L, TimeUnit.SECONDS);
				}
				
			}
			q.deleteTaskAsync(toDelete);
			tasks = q.leaseTasks(badge, TimeUnit.MINUTES, minutesLease);
		}while(tasks!=null&&!tasks.isEmpty());

	}
	
	private boolean processTask(String jsonEvent){
		//TODO: Process events, save them in batch process, calculate summaries, try to process grouped by tag and do transactional.
		return true;
	}
}
