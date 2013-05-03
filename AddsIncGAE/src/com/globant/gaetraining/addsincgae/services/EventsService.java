package com.globant.gaetraining.addsincgae.services;

import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.gaetraining.addsincgae.daos.EventsDao;
import com.globant.gaetraining.addsincgae.daos.ProductDao;
import com.globant.gaetraining.addsincgae.model.CampaignSummary;
import com.globant.gaetraining.addsincgae.model.Event;
import com.globant.gaetraining.addsincgae.model.Product;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskHandle;
import com.google.appengine.api.taskqueue.TaskOptions;

@Service
public class EventsService {

	private static final int badge = 1000;
	private static final int minutesLease = 15;
	

	@Autowired
	ProductDao productDao;
	
	@Autowired
	EventsDao eventsDao;
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
	private JsonFactory factory = new JsonFactory();
	private Map<String,CampaignSummary> campaignSummaries = new HashMap<String,CampaignSummary>();

	public String processEvent(EventType type, String ProductId,
			String distChannel, String clientIp) {
		StringWriter sb = new StringWriter();
		try {
			JsonGenerator g = factory.createJsonGenerator(sb);
			g.writeStartObject();
			g.writeStringField("type", type.toString());
			g.writeStringField("product", ProductId);
			g.writeStringField("distributionChannel", distChannel);
			g.writeStringField("client", clientIp);

			g.writeStringField("timestamp", dateFormat.format(Calendar.getInstance().getTime()));
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
		if (type.equals(EventType.VIEW)) {
			return type.toString();
		} else {
			Product p = productDao.findByKey(KeyFactory.stringToKey(ProductId),
					Product.class, null);
			return p.getUrl();

		}

	}

	public void batchEventsProcessor() {

		Queue q = QueueFactory.getQueue("events-queue");
		Event parsed = null;

		List<TaskHandle> tasks = null, toDelete = new ArrayList<TaskHandle>();
		Collection<Event> toPersist = new LinkedList<Event>();
		 while (!(tasks = q.leaseTasks(badge, TimeUnit.MINUTES, minutesLease)).isEmpty()){
			 campaignSummaries.clear();
			 toDelete.clear();
			 toPersist.clear();
			for (TaskHandle task : tasks) {
				try{
				parsed = parseTask(new String(task.getPayload()));
				} catch (ParseException | IOException e) {
					e.printStackTrace();
					parsed = null;
				}
				if (parsed!=null) {
					toPersist.add(parsed);
					addToSummaries(EventType.valueOf(parsed.getType()),parsed.getProductKey(),parsed.getDistributionChannelKey());
					toDelete.add(task);
				} else {
					q.modifyTaskLease(task, 0L, TimeUnit.SECONDS);
				}

			}
			try {
				Future<List<Boolean>> deleted = q.deleteTaskAsync(toDelete);
				saveSummaries();
				eventsDao.persistAll(toPersist);
				deleted.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

	private Event parseTask(String jsonEvent) throws JsonParseException, IOException, ParseException {
		JsonParser parser = factory.createJsonParser(jsonEvent);
		Event event = new Event();
	
		while (parser.nextToken() != JsonToken.END_OBJECT) {
			 
			String fieldname = parser.getCurrentName();
			if ("type".equals(fieldname)) {
			  parser.nextToken();
			  event.setType(parser.getText());
			}
			if ("product".equals(fieldname)) {
			  parser.nextToken();
			  event.setProductKey(KeyFactory.stringToKey(parser.getText()));
			}
			if ("distributionChannel".equals(fieldname)) {
				  parser.nextToken();
				  event.setDistributionChannelKey(KeyFactory.stringToKey(parser.getText()));
			}			
			if ("client".equals(fieldname)) {
				  parser.nextToken();
				  event.setHost(parser.getText());
			}if ("timestamp".equals(fieldname)) {
				  parser.nextToken();
				  
				  event.setTimeStamp(dateFormat.parse(parser.getText()));
			}	 
		  }
		  parser.close();
	
		return event;
	}
	
	private void addToSummaries(EventType type,Key productKey,Key distChanneKey){
		
		//TODO: calculate summaries
		
	}
	
	private void saveSummaries(){
		// Save summaries
	}

	public static enum EventType {
		VIEW("v"), CLICK("h");
		private String value;

		EventType(String e) {
			value = e;
		}

		@Override
		public String toString() {
			return value;
		}

	}
}
