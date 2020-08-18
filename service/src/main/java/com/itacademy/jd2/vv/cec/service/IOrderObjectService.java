package com.itacademy.jd2.vv.cec.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vv.cec.dao.api.filter.OrderObjectFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.IOrderObject;

public interface IOrderObjectService {
	IOrderObject createEntity();

	List<IOrderObject> getAll();

	List<IOrderObject> find(OrderObjectFilter filter);

	IOrderObject get(Integer id);

	@Transactional
	void save(IOrderObject entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	long getCount(OrderObjectFilter filter);

	IOrderObject getNewesOrder();

}
