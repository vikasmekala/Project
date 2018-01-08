package com.myproject.campaign.database;

import com.myproject.campaign.model.AdCampaign;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseRepo {

    private static Map<String, List<AdCampaign>> adCampaigns = new HashMap<String, List<AdCampaign>>();

    public static Map<String,List<AdCampaign>> getAdCampaigns()
    {
        return  adCampaigns;
    }
}
