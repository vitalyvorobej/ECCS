package com.itacademy.jd2.vv.cec.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vv.cec.dao.api.IOrderObjectDao;
import com.itacademy.jd2.vv.cec.dao.api.filter.OrderObjectFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.IOrderObject;
import com.itacademy.jd2.vv.cec.service.IOrderObjectService;

@Service
public class OrderObjectServiceImpl implements IOrderObjectService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderObjectServiceImpl.class);
    @Autowired
    private IOrderObjectDao orderObjectDao;

    @Override
    public IOrderObject createEntity() {
        return orderObjectDao.createEntity();
    }

    @Override
    public List<IOrderObject> getAll() {
        final List<IOrderObject> all = orderObjectDao.selectAll();
        LOGGER.info("total count in DB:{}", all.size());
        return all;

    }

    @Override
    public IOrderObject get(final Integer id) {
        final IOrderObject entity = orderObjectDao.get(id);
        LOGGER.debug("entity_by_id:{}", entity);
        return entity;
    }

    @Override
    public void save(final IOrderObject entity) {
        final Date modifedOn = new Date();
        entity.setUpdated(modifedOn);
        if (entity.getId() == null) {
            entity.setCreated(modifedOn);
            orderObjectDao.insert(entity);
            LOGGER.info("new saved order_object:{}", entity);
        } else {
            orderObjectDao.update(entity);
        }
    }

    @Override
    public void delete(final Integer id) {
        LOGGER.info("delete entity:{}", id);
        orderObjectDao.delete(id);
    }

    @Override
    public void deleteAll() {
        LOGGER.info("delete all order_object entities");
        orderObjectDao.deleteAll();
    }

    @Override
    public List<IOrderObject> find(OrderObjectFilter filter) {
        return orderObjectDao.find(filter);
    }

    @Override
    public long getCount(OrderObjectFilter filter) {
        return orderObjectDao.getCount(filter);
    }

    @Override
    public IOrderObject getNewesOrder() {

        return orderObjectDao.getNewesOrder();
    }

}
