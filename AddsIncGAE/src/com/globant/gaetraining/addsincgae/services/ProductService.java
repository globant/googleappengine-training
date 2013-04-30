package com.globant.gaetraining.addsincgae.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.gaetraining.addsincgae.daos.ProductDao;
import com.globant.gaetraining.addsincgae.model.Product;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	public Product getProduct(String productId){
		Key id = KeyFactory.stringToKey(productId);
		Product product = productDao.findByKey(id, Product.class);
		return product;
	}
	
	public List<Product> getProducts(){
		return productDao.findAll(Product.class);
	}


}