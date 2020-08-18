package com.itacademy.jd2.vv.cec.dao.api;

import java.util.List;

import com.itacademy.jd2.vv.cec.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.IUserAccount;

public interface IUserAccountDao extends BaseDao<IUserAccount, Integer> {

	List<IUserAccount> find(UserAccountFilter filter);

	long getCount(UserAccountFilter filter);

	IUserAccount getEmail(String email);

}
