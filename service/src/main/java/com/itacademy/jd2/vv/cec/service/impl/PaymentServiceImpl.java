package com.itacademy.jd2.vv.cec.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vv.cec.dao.api.IPaymentDao;
import com.itacademy.jd2.vv.cec.dao.api.filter.PaymentFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.IPayment;
import com.itacademy.jd2.vv.cec.service.IPaymentService;

@Service
public class PaymentServiceImpl implements IPaymentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);
    @Autowired
    private IPaymentDao dao;

    @Override
    public IPayment get(final Integer id) {
        final IPayment entity = dao.get(id);
        LOGGER.debug("entityById: {}", entity);
        return entity;
    }

    @Override
    public IPayment creatEntity() {
        return dao.createEntity();
    }

    @Override
    public List<IPayment> getAll() {
        final List<IPayment> all = dao.selectAll();
        LOGGER.info("total count in DB : {}", all.size());
        return all;
    }

    @Override
    public void save(final IPayment entity) {
        final Date modifedOn = new Date();
        entity.setUpdated(modifedOn);
        if (entity.getId() == null) {
            entity.setCreated(modifedOn);
            dao.insert(entity);
            LOGGER.info("new saved payment: {}", entity);
        } else {
            dao.update(entity);
        }
    }

    @Override
    public void delete(final Integer id) {
        LOGGER.info("delete entite: {}", id);
        dao.delete(id);
    }

    @Override
    public void deleteAll() {
        LOGGER.info("delete all payment entities");
        dao.deleteAll();
    }

    @Override
    public List<IPayment> find(PaymentFilter filter) {
        return dao.find(filter);
    }

    @Override
    public long getCount(PaymentFilter filter) {
        return dao.getCount(filter);
    }

    @Override
    public IPayment getNewestPayment() {
        return null;
    }

}
