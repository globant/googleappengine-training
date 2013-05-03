package com.globant.gaetraining.addsincgae.controllers.cron;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.globant.gaetraining.addsincgae.services.EventsService;
import com.google.appengine.api.taskqueue.LeaseOptions;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskHandle;

@Controller
public class ProcessEventsController {
	
	@Autowired
	EventsService eventService;
	
	@RequestMapping("/process_events")
	public void processEventsBatch(){
		eventService.batchEventsProcessor();
	}
	
}
