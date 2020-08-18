package com.itacademy.jd2.vv.cec.dao.api.model;

import com.itacademy.jd2.vv.cec.dao.api.model.base.IBaseEntity;

public interface IUserAccount extends IBaseEntity {
    String getEmail();

    String getPassword();

    String getRole();

    void setEmail(String email);

    void setPassword(String password);

    void setRole(String role);

   /* int DEFAULT_VERSION = 1;

    Integer getVersion();

    void setVersion(final Integer version);*/
}
