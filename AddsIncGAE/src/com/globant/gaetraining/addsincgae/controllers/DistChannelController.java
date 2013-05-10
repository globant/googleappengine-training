package com.globant.gaetraining.addsincgae.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.globant.gaetraining.addsincgae.model.DistributionChannel;
import com.globant.gaetraining.addsincgae.services.DistChannelService;
import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.KeyFactory;

@Controller
@RequestMapping("/channels")
public class DistChannelController {
	
	@Autowired
	DistChannelService distChannelService;
	
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	@ModelAttribute("distchannels")
	public List<DistributionChannel> getChannels(){
		return distChannelService.getDistributionChannels();
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String listChannels(){
		return "channels/list";
	}
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String addChannel(){
		return "channels/add";
	}
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public String receiveChannel(HttpServletRequest request, @ModelAttribute("channel")DistributionChannel channel, Model model){
		Map<String, List<BlobInfo>> blobs = blobstoreService.getBlobInfos(request);
        List<BlobInfo> blobInfos = blobs.get("logo");
        if(blobInfos!=null&&blobInfos.size()>0){
        	channel.setLogo(blobInfos.get(0).getBlobKey());
		}

		distChannelService.add(channel);
		return "channels/list";
	}
	
	@RequestMapping(value="/{channelKey}", method=RequestMethod.DELETE)
	public String delChannel(@PathVariable String channelKey){
		distChannelService.delete(KeyFactory.stringToKey(channelKey));
		return "channels";
	}
	
	@RequestMapping(value="/{channelKey}", method=RequestMethod.POST)
	public String edit(HttpServletRequest request, @PathVariable String channelKey){
		Map<String, List<BlobInfo>> blobs = blobstoreService.getBlobInfos(request);
		DistributionChannel channel= distChannelService.get(KeyFactory.stringToKey(channelKey));
        List<BlobInfo> blobInfos = blobs.get("logo");
        if(blobInfos!=null&&blobInfos.size()>0){
        	channel.setLogo(blobInfos.get(0).getBlobKey());
		}

		distChannelService.save(channel);
		return "channels/list";
	}
	
	@RequestMapping(value="/{channelKey}", method = RequestMethod.GET)
	public String prepareEdit(@ModelAttribute("channelKey") String channelKey, Model model){
		model.addAttribute("distChannel",distChannelService.get(KeyFactory.stringToKey(channelKey)));
		return "channels/edit";
	}
	
	@RequestMapping(value="/{channelKey}/logo", method = RequestMethod.GET)
	public void serveLogo(HttpServletResponse response, @PathVariable String channelKey, Model model) {
		DistributionChannel channel= distChannelService.get(KeyFactory.stringToKey(channelKey));
		try{
			blobstoreService.serve(channel.getLogo(),response);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
		
	

}
