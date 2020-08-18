package com.itacademy.jd2.vv.cec.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vv.cec.dao.api.filter.CardFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.ICard;

public interface ICardService {

	ICard get(Integer id);

	@Transactional
	void save(ICard entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	ICard createEntity();

	List<ICard> getAll();

	List<ICard> find(CardFilter filter);

	long getCount(CardFilter filter);

	ICard getNewestCard();

}
