package com.globant.gaetraining.addsincgae.daos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.globant.gaetraining.addsincgae.model.Product;
import com.google.appengine.api.datastore.Key;

@Repository
public class ProductDao extends GenericDao<Product> {

	public List<Product> findTopNMostViewedByCustomerKey(Key customerKey) {
		return null;
	}

	public List<Product> findTopNMostAccessedByCustomerKey(Key customerKey) {
		return null;
	}
}