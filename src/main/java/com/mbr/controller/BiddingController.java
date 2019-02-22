package com.mbr.controller;

import com.mbr.service.BiddingService;
import com.mbr.model.bid.WinningBid;
import com.mbr.model.configure.Campaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
@RestController
public class BiddingController {

    @Autowired
    private BiddingService biddingService;

    @PostMapping("/configure")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCampaigns(@RequestBody List<Campaign> campaigns) {
        biddingService.configure(campaigns);
    }

    @GetMapping("/bid")
    @ResponseStatus(HttpStatus.OK)
    public List<WinningBid> getResponse(@RequestParam Map<String, String> rules) {
        return biddingService.getWinningBids(rules);
    }
}