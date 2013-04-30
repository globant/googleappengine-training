package com.globant.gaetraining.addsincgae.services;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.springframework.stereotype.Service;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

@Service
public class EventsService {
	
	public String processEvent(EventType type, String ProductId,
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
	public static enum EventType{
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
}
