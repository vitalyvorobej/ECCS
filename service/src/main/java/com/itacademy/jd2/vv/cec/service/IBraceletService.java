package com.itacademy.jd2.vv.cec.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vv.cec.dao.api.filter.BraceletFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.IBracelet;

public interface IBraceletService {
	IBracelet get(Integer id);

	// дописа+ть getters setters
	IBracelet createEntity();

	List<IBracelet> getAll();

	List<IBracelet> find(BraceletFilter filter);

	@Transactional
	void save(IBracelet entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	long getCount(BraceletFilter filter);
}
