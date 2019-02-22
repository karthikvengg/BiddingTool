package com.mbr.controller;

import com.mbr.service.BiddingService;
import com.mbr.model.bid.WinningBid;
import com.mbr.model.configure.Campaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
@RestController
public class BiddingController {

    @Autowired
    private BiddingService biddingService;

    @PostMapping("/configure")
    public ResponseEntity<Void> createCampaigns(@RequestBody List<Campaign> campaigns) {
        biddingService.configure(campaigns);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/bid")
    public ResponseEntity<List<WinningBid>> getResponse(@RequestParam Map<String, String> rules) {
        return new ResponseEntity<>(biddingService.getWinningBids(rules),HttpStatus.OK);
    }
}