package com.globant.gaetraining.addsincgae.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.gaetraining.addsincgae.daos.DistributionChannelDao;
import com.globant.gaetraining.addsincgae.daos.ProductDao;
import com.globant.gaetraining.addsincgae.model.DistributionChannel;
import com.globant.gaetraining.addsincgae.model.Product;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Service
public class ProductService {
	
	enum TEMP_WILCARDS{
		NAME("{product.name}"), SDESC("{product.shortDescription}"),
		LDESC("{product.longDescription}"), NAVURL("{product.navigationURL}"),
		VIEWURL("{product.displayBreadcrumURL}");
		
		private String value;
		
		private TEMP_WILCARDS(String value){
			this.value = value;
		}
		
		public String getValue(){
			return this.value;
		}
	}
		
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private DistributionChannelDao distributionChannelDao;
	
	public Product getProduct(String productId){
		Key id = KeyFactory.stringToKey(productId);
		Product product = productDao.findByKey(id, Product.class);
		return product;
	}
	
	public List<Product> getProducts(){
		return productDao.findAll(Product.class);
	}

	
	public List<AdTemplateResponseDTO> getProductsByKeyWordAndChannelDist(HttpServletRequest request, 
									String channelKey, String keyword, int limit){
		
		Key keyTmp = KeyFactory.stringToKey(channelKey);
		DistributionChannel distChannel = distributionChannelDao.findByKey(keyTmp, DistributionChannel.class);
		
		List<Product> productsTmp = productDao.getProductsByKeyWordAndCampaignAndDistChannel(distChannel, keyword, limit);
		List<Product> products = new ArrayList<Product>();
		
		for (Product prodTmp : productsTmp) {
			if(this.productContainsKeyword(prodTmp, keyword))
				products.add(prodTmp);
		}
		
		List<AdTemplateResponseDTO> prodsTemplate = new ArrayList<AdTemplateResponseDTO>();
		AdTemplateResponseDTO tempDTO = new AdTemplateResponseDTO();
		String hostData = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
		for(Product tmpProd : products){
			tempDTO.setProduct(tmpProd);
			tempDTO.setTemplate( this.buildTemplateProd(tmpProd, distChannel.getTemplate(),
	                                                    KeyFactory.keyToString(distChannel.getKey()),hostData) );
			prodsTemplate.add(tempDTO);
			
		}
		
		return prodsTemplate;
	}
	
	public void addProduct(Product product) {
		productDao.persist(product);
	}

	private String buildTemplateProd(Product prod, String template, String channelKey,
										String host){
		StringBuilder temp = new StringBuilder(template);
		StringBuilder prodId = new StringBuilder();
		prodId.append(KeyFactory.keyToString(prod.getKey())).append("/");
		prodId.append(channelKey).append("/");
		
		template = template.replace(TEMP_WILCARDS.NAME.getValue(), prod.getName());
		template = template.replace(TEMP_WILCARDS.SDESC.getValue(), prod.getShortDescription());
		
		template = template.replace(TEMP_WILCARDS.LDESC.getValue(), prod.getLongDescription());
		
		template = template.replace(TEMP_WILCARDS.NAVURL.getValue(), 
				 host + "/engine/click/"+ prodId.toString());
		
		template = template.replace(TEMP_WILCARDS.VIEWURL.getValue(), 
				host + "/engine/view/"+ prodId.toString());
		
		return template;
	}
	
	private boolean productContainsKeyword(Product prod, String keyword){
		String keywordUp = keyword.toUpperCase();
		boolean like = (prod.getName().toUpperCase().indexOf(keywordUp) != -1 || prod.getShortDescription().toUpperCase().indexOf(keywordUp) != -1 
				|| prod.getLongDescription().toUpperCase().indexOf(keywordUp) != -1);
		
		if( like )
			return true;
		
		for(String tmpFamily : prod.getProductFamily()){
			if(tmpFamily.toUpperCase().indexOf(keywordUp) != -1)
				return true;
		}
		
		return false;
	}
}
