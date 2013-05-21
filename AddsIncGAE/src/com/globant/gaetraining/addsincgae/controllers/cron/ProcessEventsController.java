package com.globant.gaetraining.addsincgae.controllers.cron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.globant.gaetraining.addsincgae.services.EventsService;

@Controller
public class ProcessEventsController {
	
	@Autowired
	EventsService eventService;
	
	@RequestMapping("/process_events")
	public void processEventsBatch(){
		eventService.batchEventsProcessor();
	}
	
}
