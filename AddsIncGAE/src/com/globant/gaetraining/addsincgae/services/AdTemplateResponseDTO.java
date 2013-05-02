package com.globant.gaetraining.addsincgae.services;

import com.globant.gaetraining.addsincgae.model.Product;

public class AdTemplateResponseDTO {

	private Product product;
	
	private String template;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}
	
}
