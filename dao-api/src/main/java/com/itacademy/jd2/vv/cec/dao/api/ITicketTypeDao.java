package com.itacademy.jd2.vv.cec.dao.api;

import java.util.List;

import com.itacademy.jd2.vv.cec.dao.api.filter.TicketTypeFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.ITicketType;

public interface ITicketTypeDao extends BaseDao<ITicketType, Integer> {
    List<ITicketType> find(TicketTypeFilter filter);

    long getCount(TicketTypeFilter filter);
}
