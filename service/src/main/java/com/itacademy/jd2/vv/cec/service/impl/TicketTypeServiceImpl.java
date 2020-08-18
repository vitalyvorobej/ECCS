package com.itacademy.jd2.vv.cec.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vv.cec.dao.api.ITicketTypeDao;
import com.itacademy.jd2.vv.cec.dao.api.filter.TicketTypeFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.ITicketType;
import com.itacademy.jd2.vv.cec.service.ITicketTypeService;

@Service
public class TicketTypeServiceImpl implements ITicketTypeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TicketTypeServiceImpl.class);
    @Autowired
    private ITicketTypeDao dao;

    @Override
    public ITicketType get(final Integer id) {
        final ITicketType entity = dao.get(id);
        LOGGER.debug("entityById:{}", entity);
        return entity;
    }

    @Override
    public ITicketType createEntity() {
        return dao.createEntity();
    }

    @Override
    public List<ITicketType> getAll() {
        final List<ITicketType> all = dao.selectAll();
        LOGGER.info("total count in DB:{}", all.size());
        return all;
    }

    @Override
    public void save(final ITicketType entity) {
        final Date modifedOn = new Date();
        entity.setUpdated(modifedOn);
        if (entity.getId() == null) {
            entity.setCreated(modifedOn);
            dao.insert(entity);
            LOGGER.info("new saved ticket_type:{}", entity);
        } else {
            dao.update(entity);
        }
    }

    @Override
    public void delete(final Integer id) {
        LOGGER.info("delete entity:{}", id);
        dao.delete(id);
    }

    @Override
    public void deleteAll() {
        LOGGER.info("delete all ticket_type entities");
        dao.deleteAll();
    }

    @Override
    public List<ITicketType> find(TicketTypeFilter filter) {
        return dao.find(filter);
    }

    @Override
    public long getCount(final TicketTypeFilter filter) {
        return dao.getCount(filter);
    }

}
