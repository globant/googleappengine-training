package com.globant.gaetraining.addsincgae.services;

import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.gaetraining.addsincgae.daos.CampaignDao;
import com.globant.gaetraining.addsincgae.daos.CustomerDao;
import com.globant.gaetraining.addsincgae.daos.DistributionChannelDao;
import com.globant.gaetraining.addsincgae.daos.ProductDao;
import com.globant.gaetraining.addsincgae.model.Campaign;
import com.globant.gaetraining.addsincgae.model.Customer;
import com.globant.gaetraining.addsincgae.model.DistributionChannel;
import com.globant.gaetraining.addsincgae.model.Product;
import com.globant.gaetraining.addsincgae.services.EventsService.EventType;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

@Service
public class HomeService {

	@Autowired
	CampaignDao campaignDao;

	@Autowired
	DistributionChannelDao distChannelDao;

	@Autowired
	ProductDao productDao;
	
	@Autowired
	private CustomerDao daoCustomer = new CustomerDao();

	public void populate() {
		
		String templateChannel = "<div><h4>{product.name}</h4><p>{product.shortDescription}</p><p>{product.longDescription}</p><p><a href=\"{product.navigationURL}\">Product URL Navigation</a></p><p><a href=\"{product.displayBreadcrumURL}\">Display Product</a></p></div>";
		// DistChannel
		DistributionChannel distChannel = new DistributionChannel();
		Key keyDist = KeyFactory.createKey("DistributionChannel",
				1000000L);
		distChannel.setKey(keyDist);
		distChannel.setName("Mockito");
		distChannel.setMediaType("TV");
		distChannel
				.setTemplate(templateChannel);
		distChannelDao.persist(distChannel);

		DistributionChannel distChannel2 = new DistributionChannel();
		Key keyDist2 = KeyFactory.createKey("DistributionChannel",
				1000001L);
		distChannel2.setKey(keyDist2);
		distChannel2.setName("Mockito 2");
		distChannel2.setMediaType("Web");
		distChannel2
				.setTemplate(templateChannel);
		distChannelDao.persist(distChannel2);
		
		Customer customer = new Customer();
		customer.setName("Dummy Man !!!");
		customer = this.daoCustomer.persist(customer);

		for (int i = 1; i <= 8; ++i) {
			// Campaign
			Campaign campaign = new Campaign(customer.getKey());
			Key keyCamp = KeyFactory.createKey("Campaign",
					2000000L + i);
			campaign.setKey(keyCamp);
			campaign.setCustomerKey(customer.getKey());
			campaign.setName("Mock " + i);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 30);
			campaign.setEndDate(cal.getTime());
			cal.add(Calendar.YEAR, -1);
			campaign.setStartDate(cal.getTime());
			campaign.setDistributionChannelKeys(new ArrayList<Key>());
			campaign.getDistributionChannelKeys().add(
					i % 2 == 0 ? keyDist : keyDist2);
			campaign.setProduct(new ArrayList<Product>());

			for (int j = 1; j < 5; ++j) {
				// Product
				Key keyProduct = KeyFactory.createKey(campaign.getKey(),
						 "Product", 3000000L + i*10 +  j);
				Product product = new Product(campaign);
				product.setKey(keyProduct);
		product.setName("Mockiproduct_" + i + "_" + j);
				product.setShortDescription("Short Desc_" + i + "_" + j);
				product.setLongDescription("The long description here " + i + "_" + j);
				product.setUrl("http://mock.globant.com/");
				
				if (j % 2 == 0) {
					product.setCountry("Colombia");
				} else {
					product.setCountry("Brazil");
				}
				campaign.getProduct().add(product);

			}
			campaignDao.persist(campaign);
		}

	}
	
		public void dummyEventTasks(long[] distChannel, long[][] product) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
			String prodToTask, distChannelTask;
				for (int i = 0; i < 10000; i++) {
					prodToTask = KeyFactory.keyToString(KeyFactory.createKey(KeyFactory.createKey("Campaign", product[i % product.length][0]),"Product",product[i % product.length][1]));
					distChannelTask = KeyFactory.keyToString(KeyFactory.createKey("DistributionChannel",distChannel[i%distChannel.length]));
		
					JsonFactory f = new JsonFactory();
					StringWriter sb = new StringWriter();
					try {
						JsonGenerator g = f.createJsonGenerator(sb);
						g.writeStartObject();
						g.writeStringField("type", EventType.CLICK.toString());
						g.writeStringField("product", prodToTask);
						g.writeStringField("distributionChannel", distChannelTask);
						g.writeStringField("client", "222.2.22." + i % 100);
		
						g.writeStringField("timestamp", dateFormat.format(Calendar.getInstance()
								.getTime()));
						g.writeEndObject();
						g.close();
		
					} catch (IOException e) {
						e.printStackTrace();
					}
					Queue q = QueueFactory.getQueue("events-queue");
					TaskOptions taskOptions = TaskOptions.Builder
							.withMethod(TaskOptions.Method.PULL).payload(sb.toString());
					q.add(taskOptions);
				}
			}


	public List<Product> getProducts() {
		return productDao.findAll(Product.class);

	}

	public List<DistributionChannel> getChannels() {
		return distChannelDao.findAll(DistributionChannel.class);
	}

}
