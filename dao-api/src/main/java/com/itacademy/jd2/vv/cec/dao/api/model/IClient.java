package com.itacademy.jd2.vv.cec.dao.api.model;

import java.util.Date;

import com.itacademy.jd2.vv.cec.dao.api.model.base.IBaseEntity;

public interface IClient extends IBaseEntity {
    String getFirstName();

    String getLastName();

    String getPhoneNumber();

    Date getBirthdayDate();

   /* int DEFAULT_VERSION = 1;

    Integer getVersion();

    void setVersion(final Integer version);*/

    void setBirthdayDate(Date birthdayDayte);

    void setPhoneNumber(String phoneNumber);

    void setFirstName(String firstName);

    void setLastName(String lastName);
}
