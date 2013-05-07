package com.globant.gaetraining.addsincgae.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.globant.gaetraining.addsincgae.model.Product;
import com.globant.gaetraining.addsincgae.model.ProductSummary;
import com.google.appengine.api.datastore.Key;

@Repository
public class ProductSummaryDao extends GenericDao<ProductSummary> {

	/**
	 * Convert a {@link List} of {@link ProductSummary} into a {@link Map}. The
	 * Key of the map is the Key of the {@link Product}
	 * 
	 * @param products
	 *            {@link List} of {@link ProductSummary} to convert
	 * @return {@link Map} of {@link ProductSummary} converted
	 */
	public Map<Key, ProductSummary> convertToMapWithProductKey(
			List<ProductSummary> products) {
		Map<Key, ProductSummary> productsMap = new HashMap<Key, ProductSummary>();
		for (ProductSummary productSummary : products) {
			productsMap.put(productSummary.getProductKey(), productSummary);
		}
		return productsMap;
	}
}
