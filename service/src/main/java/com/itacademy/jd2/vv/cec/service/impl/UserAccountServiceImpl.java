package com.itacademy.jd2.vv.cec.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vv.cec.dao.api.IUserAccountDao;
import com.itacademy.jd2.vv.cec.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.IUserAccount;
import com.itacademy.jd2.vv.cec.javamail.JavaMail;
import com.itacademy.jd2.vv.cec.service.IUserAccountService;

@Service
public class UserAccountServiceImpl implements IUserAccountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountServiceImpl.class);
	@Autowired
	private IUserAccountDao userAccountDao;

	/*
	 * @Override public IUserAccount get(final Integer id) { return userAccountDao.get(id); }
	 * 
	 * @Override public IUserAccount createEntity() { return userAccountDao.createEntity(); }
	 * 
	 * @Override public List<IUserAccount> getAll(){ final List<IUserAccount> all = userAccountDao.selectAll();
	 * LOGGER.info("total count in DB:{}", all.size()); return all; }
	 * 
	 * @Override public void save (final IUserAccount entity) { final Date modifedOn = new Date(); entity.setUpdated(modifedOn);
	 * if(entity.getId()==null) { entity.setCreated(modifedOn); userAccountDao.insert(entity); LOGGER.info("new saved user:{}", entity);
	 * }else { userAccountDao.update(entity); } }
	 * 
	 * @Override public void deleteAll() { LOGGER.info("delete all user etities"); userAccountDao.deleteAll(); }
	 * 
	 * @Override public List<IUserAccount> find (final UserAccountFilter filter){ return userAccountDao.find(filter); }
	 * 
	 * @Override public long getCount (final UserAccountFilter filter) { return userAccountDao.getCount(filter); }
	 * 
	 * @Override public void delete(Integer id) { LOGGER.info("delete all entity : {}", id); final IUserAccount iUser =
	 * userAccountDao.get(id); userAccountDao.update(iUser); userAccountDao.delete(id); }
	 */

	@Override
	public void save(final IUserAccount entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			userAccountDao.insert(entity);
			try {
				JavaMail.sendEmail(entity.getEmail());
			} catch (Exception e) {
			}
			LOGGER.info("new saved user_account:{}", entity);
		} else {
			userAccountDao.update(entity);
		}
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete entity: {}", id);
		userAccountDao.delete(id);

	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete_all_client_entities");
		userAccountDao.deleteAll();
	}

	@Override
	public IUserAccount createEntity() {
		return userAccountDao.createEntity();
	}

	@Override
	public List<IUserAccount> getAll() {
		final List<IUserAccount> all = userAccountDao.selectAll();
		LOGGER.info("total_count_in_DB:{}", all.size());
		return all;
	}

	@Override
	public IUserAccount get(final Integer id) {
		final IUserAccount entity = userAccountDao.get(id);
		LOGGER.debug("entity_by_id:{}", entity);
		return entity;
	}

	@Override
	public List<IUserAccount> find(UserAccountFilter filter) {
		return userAccountDao.find(filter);
	}

	@Override
	public long getCount(UserAccountFilter filter) {
		return userAccountDao.getCount(filter);
	}

	@Override
	public IUserAccount getByMail(String email) {
		return userAccountDao.getEmail(email);
	}

}
