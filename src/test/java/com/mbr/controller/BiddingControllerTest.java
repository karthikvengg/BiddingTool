package com.mbr.controller;

import com.google.gson.Gson;
import com.mbr.model.configure.Campaign;
import com.mbr.model.configure.Rule;
import com.mbr.service.BiddingService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BiddingControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    @Autowired
    private BiddingController biddingController;

    @SuppressWarnings("unused")
    @Autowired
    private BiddingService biddingService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(biddingController)
                .build();
    }

    @Test
    public void createCampaignsTestCase1() throws Exception {

        biddingService.setCampaigns(new ArrayList<>());

        Rule rule1 = new Rule("a", "a1", "match");
        Rule rule2 = new Rule("b", "b1", "boost");
        Rule rule3 = new Rule("c", "c1", "match");
        Campaign campaign1 = new Campaign("campaign1", Arrays.asList(rule1, rule2));
        Campaign campaign2 = new Campaign("campaign2", Collections.singletonList(rule3));

        Gson gson = new Gson();
        String json = gson.toJson(Arrays.asList(campaign1, campaign2));

        mockMvc.perform(post("/configure")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());

        Assert.assertFalse(biddingService.getCampaigns().isEmpty());
    }

    @Test
    public void createCampaignsTestCase2() throws Exception {

        biddingService.setCampaigns(new ArrayList<>());

        //Campaign without any rules
        Campaign campaign1 = new Campaign("campaign1", Collections.emptyList());

        Gson gson = new Gson();
        String json = gson.toJson(Collections.singletonList(campaign1));

        mockMvc.perform(post("/configure")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());

        Assert.assertFalse(biddingService.getCampaigns().isEmpty());
    }

    @Test
    public void getResponseTestCase1() throws Exception {
        biddingService.setCampaigns(new ArrayList<>());
        mockCampaigns1();

        mockMvc.perform(get("/bid")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void getResponseTestCase2() throws Exception {
        biddingService.setCampaigns(new ArrayList<>());
        mockCampaigns1();

        mockMvc.perform(get("/bid?a=a1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is("campaign1")))
                .andExpect(jsonPath("$[0].boosted", is(0)));
    }

    @Test
    public void getResponseTestCase3() throws Exception {
        biddingService.setCampaigns(new ArrayList<>());
        mockCampaigns1();

        mockMvc.perform(get("/bid?a=a2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void getResponseTestCase4() throws Exception {
        biddingService.setCampaigns(new ArrayList<>());
        mockCampaigns1();

        mockMvc.perform(get("/bid?a=a1&b=b1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is("campaign1")))
                .andExpect(jsonPath("$[0].boosted", is(1)));
    }

    @Test
    public void getResponseTestCase5() throws Exception {
        biddingService.setCampaigns(new ArrayList<>());
        mockCampaigns1();

        mockMvc.perform(get("/bid?c=c1&b=b1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is("campaign2")))
                .andExpect(jsonPath("$[0].boosted", is(0)));
    }

    @Test
    public void getResponseTestCase6() throws Exception {
        biddingService.setCampaigns(new ArrayList<>());
        mockCampaigns1();

        mockMvc.perform(get("/bid?a=a1&c=c1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is("campaign1")))
                .andExpect(jsonPath("$[0].boosted", is(0)))
                .andExpect(jsonPath("$[1].id", is("campaign2")))
                .andExpect(jsonPath("$[1].boosted", is(0)));
    }

    @Test
    public void getResponseTestCase7() throws Exception {
        // Leaving the campaigns empty
        biddingService.setCampaigns(new ArrayList<>());

        mockMvc.perform(get("/bid")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void getResponseTestCase8() throws Exception {
        biddingService.setCampaigns(new ArrayList<>());
        mockCampaigns2();

        mockMvc.perform(get("/bid")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is("campaign1")))
                .andExpect(jsonPath("$[0].boosted", is(0)));
    }

    @Test
    public void getResponseTestCase9() throws Exception {
        biddingService.setCampaigns(new ArrayList<>());
        mockCampaigns2();

        mockMvc.perform(get("/bid?a=a1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is("campaign1")))
                .andExpect(jsonPath("$[0].boosted", is(0)));
    }


    private void mockCampaigns1() throws Exception {
        Rule rule1 = new Rule("a", "a1", "match");
        Rule rule2 = new Rule("b", "b1", "boost");
        Rule rule3 = new Rule("c", "c1", "match");
        Campaign campaign1 = new Campaign("campaign1", Arrays.asList(rule1, rule2));
        Campaign campaign2 = new Campaign("campaign2", Collections.singletonList(rule3));

        Gson gson = new Gson();
        String json = gson.toJson(Arrays.asList(campaign1, campaign2));

        mockMvc.perform(post("/configure")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }

    private void mockCampaigns2() throws Exception {
        //Campaign without any rules
        Campaign campaign1 = new Campaign("campaign1", Collections.emptyList());

        Gson gson = new Gson();
        String json = gson.toJson(Collections.singletonList(campaign1));

        mockMvc.perform(post("/configure")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }
}