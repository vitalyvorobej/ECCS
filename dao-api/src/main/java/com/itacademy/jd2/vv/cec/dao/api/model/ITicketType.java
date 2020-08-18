package com.itacademy.jd2.vv.cec.dao.api.model;

import com.itacademy.jd2.vv.cec.dao.api.model.base.IBaseEntity;

public interface ITicketType extends IBaseEntity {
    String getName();

    void setName(String name);

    Double getPrice();

    void setPrice(Double price);

    Boolean getDeleted();

    void setDeleted(Boolean deleted);

   /* int DEFAULT_VERSION = 1;

    Integer getVersion();

    void setVersion(final Integer version);*/

}
