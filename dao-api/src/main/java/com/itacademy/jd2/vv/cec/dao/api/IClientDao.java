package com.itacademy.jd2.vv.cec.dao.api;

import java.util.List;

import com.itacademy.jd2.vv.cec.dao.api.filter.ClientFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.IClient;

public interface IClientDao extends BaseDao<IClient, Integer> {
    List<IClient> find(ClientFilter filter);

    long getCount(ClientFilter filter);

}
