package com.itacademy.jd2.vv.cec.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vv.cec.dao.api.IClientDao;
import com.itacademy.jd2.vv.cec.dao.api.filter.ClientFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.IClient;
import com.itacademy.jd2.vv.cec.service.IClientService;

@Service
public class ClientServiceImpl implements IClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImpl.class);
    @Autowired
    private IClientDao dao;

    @Override
    public IClient createEntity() {
        return dao.createEntity();
    }

    @Override
    public void save(final IClient entity) {
        final Date modifedOn = new Date();
        entity.setUpdated(modifedOn);
        if (entity.getId() == null) {
            entity.setCreated(modifedOn);
            dao.insert(entity);
            LOGGER.info("new saved client: {}", entity);
        } else {
            dao.update(entity);
        }
    }

    @Override
    public IClient get(final Integer id) {
        final IClient entity = dao.get(id);
        LOGGER.debug("entityById: {}", entity);
        return entity;
    }

    @Override
    public void delete(final Integer id) {
        LOGGER.info("delete entity: {}", id);
        dao.delete(id);
    }

    @Override
    public void deleteAll() {
        LOGGER.info("delete all client entities");
        dao.deleteAll();
    }

    @Override
    public List<IClient> getAll() {
        final List<IClient> all = dao.selectAll();
        LOGGER.info("total count in DB: {}", all.size());
        return all;
    }

    @Override
    public List<IClient> find(final ClientFilter filter) {

        return dao.find(filter);
    }

    @Override
    public long getCount(final ClientFilter filter) {
        return dao.getCount(filter);
    }

}
