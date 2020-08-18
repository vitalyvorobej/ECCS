package com.itacademy.jd2.vv.cec.dao.api;

import java.util.List;

import com.itacademy.jd2.vv.cec.dao.api.filter.CardFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.ICard;

public interface ICardDao extends BaseDao<ICard, Integer> {
    long getCount(CardFilter filter);

    List<ICard> find(CardFilter filter);

    ICard getNewestCard();
}
