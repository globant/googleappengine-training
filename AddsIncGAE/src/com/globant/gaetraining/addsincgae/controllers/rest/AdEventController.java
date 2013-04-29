package com.globant.gaetraining.addsincgae.controllers.rest;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;


@Controller
@RequestMapping("/engine")
public class AdEventController {
	
	private static final Logger logger = Logger.getLogger(AdEventController.class.getCanonicalName());

	@RequestMapping("/view/{product}/{distchannel}")
	public String receiveView(HttpServletRequest request, @PathVariable String product,@PathVariable String distchannel,ModelMap map){
		return processEvent(EventType.VIEW, product, distchannel, request.getRemoteAddr());
	}
	
	@RequestMapping("/click/{product}/{distchannel}")
	public String receiveClick(HttpServletRequest request, @PathVariable String product,@PathVariable String distchannel,ModelMap map){
		return processEvent(EventType.CLICK, product, distchannel, request.getRemoteAddr());
	}
	
	private String processEvent(EventType type, String ProductId,
			String distChannel, String clientIp) {
		JsonFactory f = new JsonFactory();
		StringWriter sb = new StringWriter();
		try {
			JsonGenerator g = f.createJsonGenerator(sb);
			g.writeStartObject();
			g.writeStringField("type", type.toString());
			g.writeStringField("product", ProductId);
			g.writeStringField("distributionChannel", distChannel);
			g.writeStringField("client", clientIp);

			g.writeStringField("timestamp", Calendar.getInstance().getTime()
					.toString());
			g.writeEndObject();
			g.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		Queue q = QueueFactory.getQueue("events-queue");
		TaskOptions taskOptions = TaskOptions.Builder
				.withMethod(TaskOptions.Method.PULL).payload(sb.toString())
				.tag(ProductId).tag(distChannel);
		q.add(taskOptions);
		return type.toString();

	}
}
enum EventType{
	VIEW("v"),CLICK("h");
	private String value; 
	EventType(String e){
		value = e;
	}
	@Override
	public String toString() {
		return value;
	}

}