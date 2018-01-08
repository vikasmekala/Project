package com.myproject.campaign.controllers;


import com.myproject.campaign.AdCampaignServiceApplication;
import com.myproject.campaign.model.AdCampaign;
import com.myproject.campaign.model.AdCampaignList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdCampaignServiceApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdServerIntegrationTest {

    @Autowired
    private AdServerController adServerController;
    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testAdCampaigns() {

        String adCampaignRequest = "{\"adCampaignList\":[{\"partnerId\":\"uniquePartnerId1\",\"duration\":180,\"adContent\":\"Ad5 content display for partner 1\"}]}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(adCampaignRequest, headers);

        ResponseEntity<AdCampaignList> responseEntity = restTemplate.exchange("/ad", HttpMethod.POST, requestEntity, AdCampaignList.class);
        Assert.assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        Assert.assertTrue(responseEntity.hasBody() && responseEntity.getBody().getAdCampaignList().size() == 1);

        ResponseEntity<AdCampaignList> adCampaignresponseEntity = restTemplate.exchange("/ad/uniquePartnerId1", HttpMethod.GET, null, AdCampaignList.class);
        Assert.assertTrue(adCampaignresponseEntity.getBody().getAdCampaignList().size() == 1);

        ResponseEntity<List<List<AdCampaign>>> allCampaignsresponseEntity = restTemplate.exchange("/allCampaigns", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<List<AdCampaign>>>() {
                });
        Assert.assertTrue(allCampaignsresponseEntity.getBody().size() > 0);


    }

}