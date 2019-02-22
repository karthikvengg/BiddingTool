package com.mbr.model.bid;

@SuppressWarnings("unused")
public class WinningBid {
    private String id;
    private Integer boosted;

    public WinningBid(String id, Integer boosted) {
        this.id = id;
        this.boosted = boosted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getBoosted() {
        return boosted;
    }

    public void setBoosted(Integer boosted) {
        this.boosted = boosted;
    }
}
