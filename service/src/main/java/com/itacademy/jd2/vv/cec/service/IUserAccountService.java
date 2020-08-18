package com.itacademy.jd2.vv.cec.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vv.cec.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.IUserAccount;

public interface IUserAccountService {

	IUserAccount get(Integer id);

	IUserAccount createEntity();

	List<IUserAccount> getAll();

	List<IUserAccount> find(UserAccountFilter filter);

	@Transactional
	void save(IUserAccount entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	long getCount(UserAccountFilter filter);

	IUserAccount getByMail(String email);
}
