package com.globant.gaetraining.addsincgae.controllers.rest;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.globant.gaetraining.addsincgae.model.Campaign;
import com.globant.gaetraining.addsincgae.model.Product;
import com.globant.gaetraining.addsincgae.services.EventsService;
import com.globant.gaetraining.addsincgae.services.ProductService;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;


@Controller
@RequestMapping("/engine")
public class AdEventController {
	
	private static final Logger logger = Logger.getLogger(AdEventController.class.getCanonicalName());
	
	@Autowired
	private EventsService eventsService;
	
	
	@Autowired
	private ProductService productService;
	
	
	@RequestMapping("/view/{product}/{distchannel}")
	public String receiveView(HttpServletRequest request, @PathVariable String product,@PathVariable String distchannel,ModelMap map){
		return eventsService.processEvent(EventsService.EventType.VIEW, product, distchannel, request.getRemoteAddr());
	}
	
	@RequestMapping("/click/{product}/{distchannel}")
	
	public ModelAndView receiveClick(HttpServletRequest request, @PathVariable String productId,@PathVariable String distchannel,ModelMap map){
		
		String returnUrl = eventsService.processEvent(EventsService.EventType.CLICK, productId, distchannel, request.getRemoteAddr());
		
		ModelAndView redirect = new ModelAndView(returnUrl);
		return redirect;
	}
	
	/**
	 * 
	 * @param distchannel String Distribution Channel name
	 * @param keyword String Searching will be made by this keyword
	 * @param limit Integer Number that indicates the result list size 
	 * @return
	 */
	@RequestMapping("/channelkey/{channelKey}/adskeyword/{keyword}/adslimit/{limit}")
	public ModelAndView getAdvertisementByKeyWord(@PathVariable String channelKey,
												@PathVariable String keyword,
												@PathVariable int limit){
		
		ModelAndView mav = new ModelAndView();
		
		List<Product> prods = productService.getProductsByKeyWordAndChannelDist(channelKey, keyword, limit);
		mav.addObject(prods);
		return mav;
	}

}