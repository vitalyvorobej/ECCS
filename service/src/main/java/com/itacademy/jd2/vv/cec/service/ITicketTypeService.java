package com.itacademy.jd2.vv.cec.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vv.cec.dao.api.filter.TicketTypeFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.ITicketType;

public interface ITicketTypeService {

    ITicketType get(Integer id);

    ITicketType createEntity();

    List<ITicketType> getAll();

    List<ITicketType> find(TicketTypeFilter filter);

    @Transactional
    void save(ITicketType entity);

    @Transactional
    void delete(Integer id);

    @Transactional
    void deleteAll();

    long getCount(TicketTypeFilter filter);
}
