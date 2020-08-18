package com.itacademy.jd2.vv.cec.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vv.cec.dao.api.IBraceletDao;
import com.itacademy.jd2.vv.cec.dao.api.filter.BraceletFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.IBracelet;
import com.itacademy.jd2.vv.cec.service.IBraceletService;

@Service
public class BraceletServiceImpl implements IBraceletService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BraceletServiceImpl.class);
    @Autowired
    private IBraceletDao dao;

    @Override
    public IBracelet get(final Integer id) {
        final IBracelet entity = dao.get(id);
        LOGGER.debug("entity_by_id:{}", entity);
        return entity;
    }

    @Override
    public IBracelet createEntity() {
        return dao.createEntity();
    }

    @Override
    public List<IBracelet> getAll() {
        final List<IBracelet> all = dao.selectAll();
        LOGGER.debug("total count in DB: {}", all.size());
        return all;
    }

    @Override
    public void save(final IBracelet entity) {
        final Date modifedOn = new Date();
        entity.setUpdated(modifedOn);
        if (entity.getId() == null) {
            entity.setCreated(modifedOn);
            dao.insert(entity);
            LOGGER.info("new saved bracelet: {}", entity);
        } else {
            dao.update(entity);
        }

    }

    @Override
    public void delete(final Integer id) {
        LOGGER.info("delete entity: {}", id);
        final IBracelet iBracelet = dao.get(id);
        dao.update(iBracelet);
        dao.delete(id);
    }

    @Override
    public void deleteAll() {
        LOGGER.info("delete all bracelet entities");
        dao.deleteAll();
    }

    @Override
    public List<IBracelet> find(final BraceletFilter filter) {
        return dao.find(filter);
    }

    @Override
    public long getCount(final BraceletFilter filter) {
        return dao.getCount(filter);
    }

}
