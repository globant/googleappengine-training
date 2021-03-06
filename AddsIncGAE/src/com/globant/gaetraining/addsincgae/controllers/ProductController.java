package com.globant.gaetraining.addsincgae.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.globant.gaetraining.addsincgae.model.Product;
import com.globant.gaetraining.addsincgae.services.CampaignService;
import com.globant.gaetraining.addsincgae.services.ProductService;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CampaignService campaignService;

	@RequestMapping(value = "/products", method = {RequestMethod.GET, RequestMethod.POST})
	public String getCustomers(Map<String, Object> model) {

		List<Product> products = productService.getProducts();

		model.put("products", products);

		return "ProductList";
	}
	
	@RequestMapping(value = "/addProduct/{campaignId}", method = RequestMethod.GET)
	public String add(@PathVariable Long campaignId, Model model){
		model.addAttribute("campaignId", campaignId);
		return "AddProduct";		
	}
	

	/**
	 * Add new product to a campaign
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addProduct/{campaignId}", method = RequestMethod.POST)
	public String addProduct(HttpServletRequest request, @PathVariable Long campaignId,
			@RequestParam("name") String name,
			@RequestParam("shortDescription") String shortDescription,
			@RequestParam("longDescription") String longDescription,
			@RequestParam("url") String url,
			@RequestParam("country") String country, Model model) {
		
		BlobstoreService blobstoreService = BlobstoreServiceFactory
				.getBlobstoreService();
		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(request);
		BlobKey productPhoto = blobs.get("productImage").get(0);
		
		productService.addProduct(name, shortDescription,
				longDescription, url, country, productPhoto, campaignId);
		
		return "redirect:/campaign/"+campaignId;
	}
	
	@RequestMapping(value = "/prodgoto/{jspname}")
	public String goTo(@PathVariable String jspname){
		return jspname; 
	}
}
