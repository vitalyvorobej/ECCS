package com.itacademy.jd2.vv.cec.dao.api.filter;

import java.util.Date;

public class CardFilter extends AbstractFilter {

    private Date dateRegistration;
    private Integer clientId;
    private Boolean active;

    public Boolean getActive() {
        return active;
    }

    public void setActive(final Boolean active) {
        this.active = active;
    }

    public Date getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(final Date dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(final Integer clientId) {
        this.clientId = clientId;
    }

}
