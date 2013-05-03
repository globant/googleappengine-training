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
import com.globant.gaetraining.addsincgae.services.AdTemplateResponseDTO;
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
		eventsService.processEvent(EventsService.EventType.VIEW, product, distchannel, request.getRemoteAddr());
		return "forward:/resources/img/spy.jpg";
	}
	
	@RequestMapping("/click/{product}/{distchannel}")
	public String receiveClick(HttpServletRequest request, @PathVariable String product,@PathVariable String distchannel,ModelMap map){		
		return "redirect:".concat(eventsService.processEvent(EventsService.EventType.CLICK, product, distchannel, request.getRemoteAddr()));		
	}
	
	/**
	 * 
	 * @param distchannel String Distribution Channel name
	 * @param keyword String Searching will be made by this keyword
	 * @param limit Integer Number that indicates the result list size 
	 * @return
	 */
	@RequestMapping("/adsbykeyword/{channelKey}/{keyword}/{limit}")
	public ModelAndView getAdvertisementByKeyWord(HttpServletRequest request, @PathVariable String channelKey,
												@PathVariable String keyword,
												@PathVariable int limit){
		
		ModelAndView mav = new ModelAndView();
		
		List<AdTemplateResponseDTO> prodsTemplate = productService.getProductsByKeyWordAndChannelDist(request, channelKey, keyword, limit);
		
		mav.addObject(prodsTemplate);
		return mav;
	}

}