package com.globant.gaetraining.addsincgae.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.globant.gaetraining.addsincgae.model.DistributionChannel;
import com.globant.gaetraining.addsincgae.model.Product;
import com.globant.gaetraining.addsincgae.services.HomeService;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	HomeService homeService;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String goGreetings(Map<String, Object> model) {
		String greeting = "Hola gente";
		model.put("greet", greeting);
		System.out.println("Pasa HomeController");
		homeService.populate();
		String[] prods = {
				"ag5hZHNpbmMtZ2xvYmFudHI5CxIIQ2FtcGFpZ24iDm1vY2tfY2FtcGFpZ24xDAsSB1Byb2R1Y3QiEG1vY2tfcHJvZHVjdDFfXzEM",
				"ag5hZHNpbmMtZ2xvYmFudHI5CxIIQ2FtcGFpZ24iDm1vY2tfY2FtcGFpZ24xDAsSB1Byb2R1Y3QiEG1vY2tfcHJvZHVjdDFfXzIM",
				"ag5hZHNpbmMtZ2xvYmFudHI5CxIIQ2FtcGFpZ24iDm1vY2tfY2FtcGFpZ24yDAsSB1Byb2R1Y3QiEG1vY2tfcHJvZHVjdDJfXzEM",
				"ag5hZHNpbmMtZ2xvYmFudHI5CxIIQ2FtcGFpZ24iDm1vY2tfY2FtcGFpZ24yDAsSB1Byb2R1Y3QiEG1vY2tfcHJvZHVjdDJfXzIM",
				"ag5hZHNpbmMtZ2xvYmFudHI5CxIIQ2FtcGFpZ24iDm1vY2tfY2FtcGFpZ24zDAsSB1Byb2R1Y3QiEG1vY2tfcHJvZHVjdDNfXzEM",
				"ag5hZHNpbmMtZ2xvYmFudHI5CxIIQ2FtcGFpZ24iDm1vY2tfY2FtcGFpZ24zDAsSB1Byb2R1Y3QiEG1vY2tfcHJvZHVjdDNfXzIM",
				"ag5hZHNpbmMtZ2xvYmFudHI5CxIIQ2FtcGFpZ24iDm1vY2tfY2FtcGFpZ240DAsSB1Byb2R1Y3QiEG1vY2tfcHJvZHVjdDRfXzEM",
				"ag5hZHNpbmMtZ2xvYmFudHI5CxIIQ2FtcGFpZ24iDm1vY2tfY2FtcGFpZ240DAsSB1Byb2R1Y3QiEG1vY2tfcHJvZHVjdDRfXzIM",
				"ag5hZHNpbmMtZ2xvYmFudHI5CxIIQ2FtcGFpZ24iDm1vY2tfY2FtcGFpZ241DAsSB1Byb2R1Y3QiEG1vY2tfcHJvZHVjdDVfXzEM",
				"ag5hZHNpbmMtZ2xvYmFudHI5CxIIQ2FtcGFpZ24iDm1vY2tfY2FtcGFpZ241DAsSB1Byb2R1Y3QiEG1vY2tfcHJvZHVjdDVfXzIM",
				"ag5hZHNpbmMtZ2xvYmFudHI5CxIIQ2FtcGFpZ24iDm1vY2tfY2FtcGFpZ242DAsSB1Byb2R1Y3QiEG1vY2tfcHJvZHVjdDZfXzEM",
				"ag5hZHNpbmMtZ2xvYmFudHI5CxIIQ2FtcGFpZ24iDm1vY2tfY2FtcGFpZ242DAsSB1Byb2R1Y3QiEG1vY2tfcHJvZHVjdDZfXzIM",
				"ag5hZHNpbmMtZ2xvYmFudHI5CxIIQ2FtcGFpZ24iDm1vY2tfY2FtcGFpZ243DAsSB1Byb2R1Y3QiEG1vY2tfcHJvZHVjdDdfXzEM",
				"ag5hZHNpbmMtZ2xvYmFudHI5CxIIQ2FtcGFpZ24iDm1vY2tfY2FtcGFpZ243DAsSB1Byb2R1Y3QiEG1vY2tfcHJvZHVjdDdfXzIM",
				"ag5hZHNpbmMtZ2xvYmFudHI5CxIIQ2FtcGFpZ24iDm1vY2tfY2FtcGFpZ244DAsSB1Byb2R1Y3QiEG1vY2tfcHJvZHVjdDhfXzEM",
				"ag5hZHNpbmMtZ2xvYmFudHI5CxIIQ2FtcGFpZ24iDm1vY2tfY2FtcGFpZ244DAsSB1Byb2R1Y3QiEG1vY2tfcHJvZHVjdDhfXzIM" };
		String[] channels = {
				"ag5hZHNpbmMtZ2xvYmFudHIxCxITRGlzdHJpYnV0aW9uQ2hhbm5lbCIYbW9ja19kaXN0cmlidXRpb25jaGFubmVsDA",
				"ag5hZHNpbmMtZ2xvYmFudHIyCxITRGlzdHJpYnV0aW9uQ2hhbm5lbCIZbW9ja19kaXN0cmlidXRpb25jaGFubmVsMgw" };
		homeService.dummyEventTasks(channels, prods);
		return "home";
	}

	@RequestMapping("/products")
	public List<Product> getProducts() {
		return homeService.getProducts();
	}

	@RequestMapping("/channels")
	public List<DistributionChannel> getChannels() {
		return homeService.getChannels();
	}

}
