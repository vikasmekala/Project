package com.myproject.campaign.service;

import com.myproject.campaign.database.DatabaseRepo;
import com.myproject.campaign.exception.AdServerException;
import com.myproject.campaign.model.AdCampaign;
import com.myproject.campaign.model.AdCampaignList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest(DatabaseRepo.class)
public class AdCampaignServiceTest {

    Map<String, List<AdCampaign>> mockAdcampaigns;


    @Before
    public void setUp() throws Exception {

        mockAdcampaigns = new HashMap<>();
        mockAdcampaigns.put("foocampaign1", new ArrayList<>(Arrays.asList(new AdCampaign("foocampaign1", 20, "foo campaign ad 1"))));
        mockAdcampaigns.put("foocampaign2", new ArrayList<>(Arrays.asList(new AdCampaign("foocampaign2", 20, "foo campaign ad 2"))));
        PowerMockito.mockStatic(DatabaseRepo.class);
        PowerMockito.when(DatabaseRepo.getAdCampaigns()).thenReturn(mockAdcampaigns);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetAllAdCampaigns() {

        AdCampaignService adCampaignService = new AdCampaignService();
        List<List<AdCampaign>> allAdCampaignsList = adCampaignService.getAllAdCampaigns();
        Assert.assertTrue(allAdCampaignsList.size() == 2);

    }

    @Test(expected = AdServerException.class)
    public void testgetNoActiveAdCampaign() {
        AdCampaignService adCampaignService = new AdCampaignService();

        mockAdcampaigns.put("foocampaign1", new ArrayList<>(Arrays.asList(new AdCampaign("foocampaign1", 2, "foo campaign ad 1"))));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        adCampaignService.getAdCampaign("foocampaign1");
    }

    @Test
    public void testAtleastOneActiveAdCampaign() {
        AdCampaignService adCampaignService = new AdCampaignService();
        mockAdcampaigns.put("foocampaign1", new ArrayList<>(Arrays.asList(new AdCampaign("foocampaign1", 2, "foo campaign ad 1"), new AdCampaign("foocampaign1", 30, "foo campaign ad 2"))));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AdCampaignList activeAdCampaignList = adCampaignService.getAdCampaign("foocampaign1");
        if (activeAdCampaignList != null)
            Assert.assertTrue(activeAdCampaignList.getAdCampaignList().size() == 1);


    }

    @Test
    public void testMoreThanOneActiveAdCampaign() {

        AdCampaignService adCampaignService = new AdCampaignService();
        mockAdcampaigns.put("foocampaign1", new ArrayList<>(Arrays.asList(new AdCampaign("foocampaign1", 15, "foo campaign ad 1"), new AdCampaign("foocampaign1", 30, "foo campaign ad 2"), new AdCampaign("foocampaign1", 50, "foo campaign ad 2"))));
        AdCampaignList adCampaignList = adCampaignService.getAdCampaign("foocampaign1");
        Assert.assertTrue(adCampaignList.getAdCampaignList().size() > 1);

    }

    @Test
    public void testSuccesfulAddAdCampaign() {

        AdCampaignService adCampaignService = new AdCampaignService();

        mockAdcampaigns.put("foocampaign1", new ArrayList<>(Arrays.asList(new AdCampaign("foocampaign1", 2, "foo campaign ad 1"))));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AdCampaignList requestAdCampaignList = new AdCampaignList();
        requestAdCampaignList.setAdCampaignList(new ArrayList<>(Arrays.asList(new AdCampaign("foocampaign1", 2, "foo campaign new ad 2"))));

        adCampaignService.addAdCampaign(requestAdCampaignList);
        Assert.assertTrue(mockAdcampaigns.get("foocampaign1").size() == 2);
    }

    @Test(expected = AdServerException.class)
    public void testFailedAddAdCampaign() {

        AdCampaignService adCampaignService = new AdCampaignService();
        mockAdcampaigns.put("foocampaign1", new ArrayList<>(Arrays.asList(new AdCampaign("foocampaign1", 30, "foo campaign ad 1"))));
        AdCampaignList requestAdCampaignList = new AdCampaignList();
        requestAdCampaignList.setAdCampaignList(new ArrayList<>(Arrays.asList(new AdCampaign("foocampaign1", 2, "foo campaign new ad 2"))));
        adCampaignService.addAdCampaign(requestAdCampaignList);
    }
}
