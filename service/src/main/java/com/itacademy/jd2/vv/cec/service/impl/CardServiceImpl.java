package com.itacademy.jd2.vv.cec.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vv.cec.dao.api.ICardDao;
import com.itacademy.jd2.vv.cec.dao.api.filter.CardFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.ICard;
import com.itacademy.jd2.vv.cec.service.ICardService;

@Service
public class CardServiceImpl implements ICardService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CardServiceImpl.class);
    @Autowired
    private ICardDao dao;

    @Override
    public ICard createEntity() {
        return dao.createEntity();
    }

    @Override
    public void save(final ICard entity) {
        final Date modifedOn = new Date();
        entity.setUpdated(modifedOn);
        if (entity.getId() == null) {
            entity.setCreated(modifedOn);
            dao.insert(entity);
            LOGGER.info("new saved card: {}", entity);
        } else {
            dao.update(entity);
        }
    }

    @Override
    public ICard get(final Integer id) {
        final ICard entity = dao.get(id);
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
        LOGGER.info("delete all card entities");
        dao.deleteAll();
    }

    @Override
    public List<ICard> getAll() {
        final List<ICard> all = dao.selectAll();
        LOGGER.info("total count in DB: {}", all.size());
        return all;
    }

    @Override
    public List<ICard> find(CardFilter filter) {
        return dao.find(filter);
    }

    @Override
    public long getCount(CardFilter filter) {
        return dao.getCount(filter);
    }

    @Override
    public ICard getNewestCard() {
        return dao.getNewestCard();
    }

}
