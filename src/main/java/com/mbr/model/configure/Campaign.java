package com.mbr.model.configure;

import java.util.List;

@SuppressWarnings("unused")
public class Campaign {

    private String id;
    private List<Rule> rules = null;

    public Campaign() {
    }

    public Campaign(String id, List<Rule> rules) {
        this.id = id;
        this.rules = rules;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }
}
