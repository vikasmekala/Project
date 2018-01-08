package com.myproject.campaign.service;

import com.myproject.campaign.database.DatabaseRepo;
import com.myproject.campaign.exception.AdServerException;
import com.myproject.campaign.model.AdCampaign;
import com.myproject.campaign.model.AdCampaignList;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class AdCampaignService {

    private Map<String, List<AdCampaign>> adCampaigns = DatabaseRepo.getAdCampaigns();

    public AdCampaignService() {
        
    }

    public List<List<AdCampaign>> getAllAdCampaigns() {
        return new ArrayList<List<AdCampaign>>(adCampaigns.values());
    }

    public AdCampaignList getAdCampaign(String partnerId) {
        AdCampaignList adCampaignList = new AdCampaignList();
        List<AdCampaign> existingAdCampaignList = adCampaigns.get(partnerId);
        List<AdCampaign> activeAdcampaignList = new ArrayList<AdCampaign>();
        for (AdCampaign adCampaign : existingAdCampaignList) {
            if (isCampaignActive(adCampaign)) {
                activeAdcampaignList.add(adCampaign);
            }

        }
        if (activeAdcampaignList.isEmpty())
            throw new AdServerException("No Active Ad campaigns found for given partner id");

        adCampaignList.setAdCampaignList(activeAdcampaignList);

        return adCampaignList;
    }

    //public List<AdCampaign> addAdCampaign(List<AdCampaign> adCampaignList) {
    public AdCampaignList addAdCampaign(AdCampaignList adCampaignList) {

        // to support multiple adcampaign
        if (!adCampaignList.getAdCampaignList().isEmpty()) {

            for (AdCampaign adCampaign : adCampaignList.getAdCampaignList()) {

                List<AdCampaign> existingAdcampaigns = adCampaigns.get(adCampaign.getPartnerId());

                if (existingAdcampaigns != null) {
                    for (AdCampaign existingAdCampaign : existingAdcampaigns) {
                        if (isCampaignActive(existingAdCampaign)) {
                            throw new AdServerException("Cannot add ad campaign, active ad for given partner already exists");
                        }
                    }
                } else {
                    adCampaigns.put(adCampaign.getPartnerId(), new ArrayList<>());
                }
                adCampaign.setCreatedDate(new Date());
                List<AdCampaign> adCampaignRepo = adCampaigns.get(adCampaign.getPartnerId());
                adCampaignRepo.add(adCampaign);

            }
        }
        
        return adCampaignList;
    }

    public boolean isCampaignActive(AdCampaign adCampaign) {
        final long currentTime = new Date().getTime();
        final long createdTime = adCampaign.getCreatedDate().getTime();
        final int adDuration = adCampaign.getDuration();
        return (currentTime - createdTime) < (adDuration * 1000);
    }

}
