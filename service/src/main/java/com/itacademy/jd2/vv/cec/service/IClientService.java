package com.itacademy.jd2.vv.cec.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vv.cec.dao.api.filter.ClientFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.IClient;

public interface IClientService {

	IClient get(Integer id);

	@Transactional
	void save(IClient entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IClient createEntity();

	List<IClient> getAll();

	List<IClient> find(ClientFilter filter);

	long getCount(ClientFilter filter);
}
