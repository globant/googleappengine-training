package com.globant.gaetraining.addsincgae.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.globant.gaetraining.addsincgae.model.DistributionChannel;
import com.globant.gaetraining.addsincgae.services.DistChannelService;
import com.google.appengine.api.datastore.KeyFactory;

@Controller
@RequestMapping("/channels")
public class DistChannelController {
	
	@Autowired
	DistChannelService distChannelService;
	
	@ModelAttribute("distchannels")
	public List<DistributionChannel> getChannels(){
		return distChannelService.getDistributionChannels();
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String listChannels(){
		return "channels/list";
	}
	
	@RequestMapping("add")
	public String addChannel(){
		return "channels/add";
	}
	
	@RequestMapping(value="/{channelKey}", method=RequestMethod.DELETE)
	public String delChannel(@PathVariable String channelKey){
		distChannelService.delete(KeyFactory.stringToKey(channelKey));
		return "channels";
	}
		
	

}
