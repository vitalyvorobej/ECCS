package com.itacademy.jd2.vv.cec.dao.api.model;

import com.itacademy.jd2.vv.cec.dao.api.model.base.IBaseEntity;

public interface IBracelet extends IBaseEntity {

    /*int DEFAULT_VERSION = 1;*/

    /*Integer getVersion();*/
    
    /*void setVersion(final Integer version);*/

    String getUuId();

    Boolean getFree();

    void setUuId(String uuid);

    void setFree(Boolean free);
}
