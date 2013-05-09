package com.globant.gaetraining.addsincgae.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.gaetraining.addsincgae.daos.DistributionChannelDao;
import com.globant.gaetraining.addsincgae.model.DistributionChannel;
import com.google.appengine.api.datastore.Key;

@Service
public class DistChannelService {
	
	@Autowired
	DistributionChannelDao distChannelDao;
	
	public List<DistributionChannel> getDistributionChannels(){
		return distChannelDao.findAll(DistributionChannel.class);
	} 
	
	public boolean delete(Key keyChannel){
		distChannelDao.delete(DistributionChannel.class,keyChannel);
		return true;
	}

}
