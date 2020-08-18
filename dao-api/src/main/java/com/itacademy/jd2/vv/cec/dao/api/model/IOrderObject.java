package com.itacademy.jd2.vv.cec.dao.api.model;

import java.util.Date;

import com.itacademy.jd2.vv.cec.dao.api.model.base.IBaseEntity;

public interface IOrderObject extends IBaseEntity {
    Date getStartTime();

    void setStartTime(Date startTime);

    Date getEndTime();

    void setEndTime(Date endTime);

    ICard getCard();

    void setCard(ICard card);

    ITicketType getTicketType();

    void setTicketType(ITicketType ticketType);

    IBracelet getBracelet();

    void setBracelet(IBracelet bracelet);

    Double getTicketPrice();

    void setTicketPrice(Double ticketPrice);

  /*  int DEFAULT_VERSION = 1;

    Integer getVersion();

    void setVersion(final Integer version);*/
}
