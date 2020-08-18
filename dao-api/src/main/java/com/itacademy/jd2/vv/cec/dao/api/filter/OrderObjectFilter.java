package com.itacademy.jd2.vv.cec.dao.api.filter;

import java.util.Date;

public class OrderObjectFilter extends AbstractFilter {
    private Date startTime;
    private Date endTime;
    private Integer cardId;
    private Integer ticketTypeId;
    private Integer braceletId;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(final Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(final Date endTime) {
        this.endTime = endTime;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(final Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(final Integer ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public Integer getBraceletId() {
        return braceletId;
    }

    public void setBraceletId(final Integer braceletId) {
        this.braceletId = braceletId;
    }

}
