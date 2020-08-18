package com.itacademy.jd2.vv.cec.dao.api;

import java.util.List;

import com.itacademy.jd2.vv.cec.dao.api.filter.BraceletFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.IBracelet;

public interface IBraceletDao extends BaseDao<IBracelet, Integer> {

    List<IBracelet> find(BraceletFilter filter);

    long getCount(BraceletFilter filter);

}
