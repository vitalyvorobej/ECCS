package com.itacademy.jd2.vv.cec.dao.api.model;

import com.itacademy.jd2.vv.cec.dao.api.model.base.IBaseEntity;

public interface IPayment extends IBaseEntity {
    Double getAmount();

    void setAmount(Double amount);

    IOrderObject getOrder();

    void setOrder(IOrderObject order);

    String getPaymentType();

    void setPaymentType(String paymenttype);
/*
    int DEFAULT_VERSION = 1;

    Integer getVersion();

    void setVersion(final Integer version);*/
}
