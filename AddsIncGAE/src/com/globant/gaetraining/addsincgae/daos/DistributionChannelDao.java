package com.globant.gaetraining.addsincgae.daos;

import org.springframework.stereotype.Repository;

import com.globant.gaetraining.addsincgae.model.DistributionChannel;

@Repository
public class DistributionChannelDao extends GenericDao<DistributionChannel> {

	public DistributionChannel add(DistributionChannel distributionChannel) {
		return this.persist(distributionChannel);
	}

}
