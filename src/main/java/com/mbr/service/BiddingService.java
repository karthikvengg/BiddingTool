package com.mbr.service;

import com.mbr.model.bid.WinningBid;
import com.mbr.model.configure.Campaign;
import com.mbr.model.configure.Rule;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class BiddingService {
    private static final String MATCH = "match";
    private static final String BOOST = "boost";

    //Using concurrent collection here for multi-threaded post operations
    private List<Campaign> campaigns = new CopyOnWriteArrayList<>();
    private List<WinningBid> winningBids;

    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    public void configure(List<Campaign> campaigns) {
        this.campaigns.addAll(campaigns);
    }

    public List<WinningBid> getWinningBids(@RequestParam Map<String, String> rules) {
        winningBids = new ArrayList<>();
        campaigns.forEach(campaign -> findBids(rules, campaign));
        return winningBids;
    }

    private void findBids(@RequestParam Map<String, String> rules, Campaign campaign) {
        boolean match = false;
        int boostCounter = 0;

        for (Rule rule : campaign.getRules()) {
            if (rules.containsKey(rule.getKey()) && rules.get(rule.getKey()).equals(rule.getValue())) {
                if (rule.getBehavior().equals(MATCH)) {
                    match = true;
                } else if (rule.getBehavior().equals(BOOST)) {
                    boostCounter++;
                }
            }
        }

        if (match || campaign.getRules().isEmpty()) {
            winningBids.add(new WinningBid(campaign.getId(), boostCounter));
        }
    }
}