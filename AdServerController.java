package com.myproject.campaign.controllers;

import com.myproject.campaign.model.AdCampaign;
import com.myproject.campaign.model.AdCampaignList;
import com.myproject.campaign.service.AdCampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class AdServerController {
    @Autowired private AdCampaignService adCampaignService;

    @RequestMapping("/allCampaigns")
    public List<List<AdCampaign>> getAllCampaigns()
    {
        return adCampaignService.getAllAdCampaigns();
    }

    @RequestMapping("/ad/{partnerId}")
    public ResponseEntity<AdCampaignList> getAdCampaigns(@PathVariable String partnerId)
    {
        return  new ResponseEntity<AdCampaignList>(adCampaignService.getAdCampaign(partnerId),HttpStatus.ACCEPTED);
    }

    @RequestMapping(method= RequestMethod.POST, value="/ad")
    public ResponseEntity<AdCampaignList> addAdCampaign(@RequestBody AdCampaignList adCampaignList)
    {
        return new ResponseEntity<AdCampaignList>(adCampaignService.addAdCampaign(adCampaignList), HttpStatus.CREATED);
    }
}
