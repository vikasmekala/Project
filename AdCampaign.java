package com.myproject.campaign.model;

import java.util.Date;


public class AdCampaign {
    private String partnerId;
    private int duration;
    private String adContent;
    private Date createdDate;

    public AdCampaign() {}
    public AdCampaign(String partnerId, int duration, String adContent) {
        super();
        this.partnerId = partnerId;
        this.duration = duration;
        this.adContent = adContent;
        this.createdDate = new Date();
    }


    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAdContent() {
        return adContent;
    }

    public void setAdContent(String adContent) {
        this.adContent = adContent;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
