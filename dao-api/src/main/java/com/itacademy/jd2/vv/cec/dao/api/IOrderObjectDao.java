package com.itacademy.jd2.vv.cec.dao.api;

import java.util.List;

import com.itacademy.jd2.vv.cec.dao.api.filter.OrderObjectFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.IOrderObject;

public interface IOrderObjectDao extends BaseDao<IOrderObject, Integer> {

    long getCount(OrderObjectFilter filter);

    List<IOrderObject> find(OrderObjectFilter filter);

    IOrderObject getNewesOrder();
}
