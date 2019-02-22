package com.mbr.model.configure;

@SuppressWarnings("unused")
public class Rule {

    private String key;
    private String value;
    private String behavior;

    public Rule() {
    }

    public Rule(String key, String value, String behavior) {
        this.key = key;
        this.value = value;
        this.behavior = behavior;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }
}
