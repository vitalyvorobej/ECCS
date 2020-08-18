package com.itacademy.jd2.vv.cec.dao.api.filter;

public class BraceletFilter extends AbstractFilter {
    private String uuid;
    private Boolean free;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }

    public Boolean getFree() {
        return free;
    }

    public void setFree(final Boolean free) {
        this.free = free;
    }

}
