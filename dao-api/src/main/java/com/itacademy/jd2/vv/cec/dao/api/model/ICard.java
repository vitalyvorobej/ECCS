package com.itacademy.jd2.vv.cec.dao.api.model;

import java.util.Date;

import com.itacademy.jd2.vv.cec.dao.api.model.base.IBaseEntity;

public interface ICard extends IBaseEntity {

  /*  int DEFAULT_VERSION = 1;

    Integer getVersion();

    void setVersion(Integer version);
*/
    Date getDateRegistration();

    void setDateRegistration(Date dateRegistration);

    Boolean getActive();

    void setActive(Boolean active);

    IClient getClient();

    void setClient(IClient client);

}
