package com.globant.gaetraining.addsincgae.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.gaetraining.addsincgae.daos.CampaignDao;
import com.globant.gaetraining.addsincgae.daos.DistributionChannelDao;
import com.globant.gaetraining.addsincgae.daos.ProductDao;
import com.globant.gaetraining.addsincgae.model.Campaign;
import com.globant.gaetraining.addsincgae.model.DistributionChannel;
import com.globant.gaetraining.addsincgae.model.Product;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Service
public class ProductService {
	
	//FIXME <SP> move to properties file
	enum TEMP_WILCARDS {
		NAME("{product.name}"), SDESC("{product.shortDescription}"), LDESC(
				"{product.longDescription}"), NAVURL("{product.navigationURL}"), VIEWURL(
				"{product.displayBreadcrumURL}");

		private String value;

		private TEMP_WILCARDS(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}
	
	private static final Logger logger = Logger.getLogger(ProductService.class
			.getCanonicalName());

	@Autowired
	private ProductDao productDao;

	@Autowired
	private DistributionChannelDao distributionChannelDao;
	
	@Autowired
	CampaignDao campaignDao;

	public Product getProduct(String productId) {
		Key id = KeyFactory.stringToKey(productId);
		Product product = productDao.findByKey(id, Product.class, null);
		return product;
	}

	public List<Product> getProducts() {
		return productDao.findAll(Product.class);
	}

	/**
	 * It returns from datastore, a list (List<AdTemplateResponseDTO>) of all products that contain the keyword in their: name, 
	 * short or long description or family products. The initial filtering is made from campaings
	 * that have relationship with the distribution channel key received.
	 * @param request HttpRequestObject
	 * @param channelKey String with entity key
	 * @param keyword String Keyword
	 * @param limit Integer Number of the max size for the list returned
	 * @return List<AdTemplateResponseDTO>
	 */
	public List<AdTemplateResponseDTO> getProductsByKeyWordAndChannelDist(
			HttpServletRequest request, String channelKey, String keyword,
			int limit) {

		List<AdTemplateResponseDTO> prodsTemplate = new ArrayList<AdTemplateResponseDTO>();

		try {
			Key keyTmp = KeyFactory.stringToKey(channelKey);
			DistributionChannel distChannel = distributionChannelDao.findByKey(
					keyTmp, DistributionChannel.class, null);

			List<Product> productsTmp = productDao
					.getProductsByKeyWordAndCampaignAndDistChannel(distChannel,
							keyword, limit);
			List<Product> products = new ArrayList<Product>();

			for (Product prodTmp : productsTmp) {
				if (this.productContainsKeyword(prodTmp, keyword))
					products.add(prodTmp);
			}

			AdTemplateResponseDTO tempDTO = null;
			String hostData = request.getScheme() + "://"
					+ request.getServerName() + ((request.getServerPort() == 80) ? "" : ":" + request.getServerPort())
					+ "/";
			for (Product tmpProd : products) {
				tempDTO = new AdTemplateResponseDTO();
				tempDTO.setProduct(tmpProd);
				tempDTO.setTemplate(this.buildTemplateProd(tmpProd,
						distChannel.getTemplate(),
						KeyFactory.keyToString(distChannel.getKey()), hostData));
				prodsTemplate.add(tempDTO);

			}
		} catch (IllegalArgumentException e) {
			logger.log(Level.SEVERE, "The inputs are not valid");
		}

		return prodsTemplate;
	}

	public void addProduct(Product product) {
		productDao.persist(product);
	}
	
	public Product addProduct(String name, String shortDescription, 
			String longDescription, String productUrl, String country,
			BlobKey productPhoto, Campaign campaign){
		
		Key keyProduct = KeyFactory.createKey(campaign.getKey(), "Product",(long)(Math.random()*2000232)+1);
		Product prod = new Product(campaign);
		prod.setName(name);
		prod.setKey(keyProduct);
		prod.setCountry(country);
		prod.setLongDescription(longDescription);
		prod.setShortDescription(shortDescription);
		prod.setProductPhoto(productPhoto);
		prod.setUrl(productUrl);
		
		if(campaign.getProduct() == null)
			campaign.setProduct(new ArrayList<Product>());
//		productDao.persist(prod);
		campaign.getProduct().add(prod);
		campaignDao.persist(campaign);		
		return prod;
	}
	
	
	/**
	 * 
	 * @param prod
	 * @param template
	 * @param channelKey
	 * @param host
	 * @return
	 */
	private String buildTemplateProd(Product prod, String template,
			String channelKey, String host) {
		StringBuilder temp = new StringBuilder(template);
		StringBuilder prodId = new StringBuilder();
		prodId.append(KeyFactory.keyToString(prod.getKey())).append("/");
		prodId.append(channelKey).append("/");

		template = template.replace(TEMP_WILCARDS.NAME.getValue(),
				prod.getName());
		template = template.replace(TEMP_WILCARDS.SDESC.getValue(),
				prod.getShortDescription());

		template = template.replace(TEMP_WILCARDS.LDESC.getValue(),
				prod.getLongDescription());

		template = template.replace(TEMP_WILCARDS.NAVURL.getValue(), host
				+ "engine/click/" + prodId.toString());

		template = template.replace(TEMP_WILCARDS.VIEWURL.getValue(), host
				+ "engine/view/" + prodId.toString());

		return template;
	}

	private boolean productContainsKeyword(Product prod, String keyword) {
		String keywordUp = keyword.toUpperCase();
		boolean like = (prod.getName().toUpperCase().indexOf(keywordUp) != -1
				|| prod.getShortDescription().toUpperCase().indexOf(keywordUp) != -1 || prod
				.getLongDescription().toUpperCase().indexOf(keywordUp) != -1);

		if (like)
			return true;

		for (String tmpFamily : prod.getProductFamily()) {
			if (tmpFamily.toUpperCase().indexOf(keywordUp) != -1)
				return true;
		}

		return false;
	}
}
