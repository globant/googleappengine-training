package com.globant.gaetraining.addsincgae.controllers.binding;

import java.beans.PropertyEditorSupport;

import com.globant.gaetraining.addsincgae.model.Campaign;
import com.globant.gaetraining.addsincgae.services.CampaignService;

public class CampaignEditor extends PropertyEditorSupport {

	private CampaignService campaignService;

	public CampaignEditor(CampaignService campaignService) {
		this.campaignService = campaignService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Campaign campaign = campaignService.getCampaign(Long.valueOf(text));
        setValue(campaign);
	}

}
