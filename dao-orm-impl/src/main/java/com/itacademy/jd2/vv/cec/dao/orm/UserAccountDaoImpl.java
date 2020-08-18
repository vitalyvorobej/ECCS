package com.itacademy.jd2.vv.cec.dao.orm;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vv.cec.dao.api.IUserAccountDao;
import com.itacademy.jd2.vv.cec.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.IUserAccount;
import com.itacademy.jd2.vv.cec.dao.orm.model.UserAccount;
import com.itacademy.jd2.vv.cec.dao.orm.model.UserAccount_;

@Repository
public class UserAccountDaoImpl extends AbstractDaoImpl<IUserAccount, Integer> implements IUserAccountDao {
	protected UserAccountDaoImpl() {
		super(UserAccount.class);
	}

	@Override
	public IUserAccount createEntity() {
		/*
		 * final UserAccount user = new UserAccount(); user.setVersion(IUserAccount.DEFAULT_VERSION); return user;
		 */
		return new UserAccount();
	}

	@Override
	public List<IUserAccount> find(UserAccountFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IUserAccount> cq = cb.createQuery(IUserAccount.class);
		final Root<UserAccount> from = cq.from(UserAccount.class);
		cq.select(from);

		applyFilter(filter, cb, cq, from);

		if (filter.getSortColumn() != null) {
			final Path<?> expression = getSortPath(from, filter.getSortColumn());
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IUserAccount> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private Path<?> getSortPath(Root<UserAccount> from, String sortColumn) {
		switch (sortColumn) {
		case "created":
			return from.get(UserAccount_.created);
		case "updated":
			return from.get(UserAccount_.updated);
		case "id":
			return from.get(UserAccount_.id);
		case "email":
			return from.get(UserAccount_.email);
		case "password":
			return from.get(UserAccount_.password);
		case "role":
			return from.get(UserAccount_.role);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	private void applyFilter(UserAccountFilter filter, CriteriaBuilder cb, CriteriaQuery<IUserAccount> cq, Root<UserAccount> from) {
		final List<Predicate> ands = new ArrayList<>();

		final String email = filter.getEmail();
		if (!StringUtils.isBlank(email)) {
			ands.add(cb.like(from.get(UserAccount_.email), "%" + email + "%"));
		}
		if (!ands.isEmpty()) {
			cq.where(cb.and(ands.toArray(new Predicate[0])));
		}
	}

	@Override
	public long getCount(UserAccountFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<UserAccount> from = cq.from(UserAccount.class);
		cq.select(cb.count(from));
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public IUserAccount getEmail(String email) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IUserAccount> cq = cb.createQuery(IUserAccount.class);
		final Root<UserAccount> from = cq.from(UserAccount.class);
		cq.select(from);
		cq.where(cb.equal(from.get(UserAccount_.email), email));
		final TypedQuery<IUserAccount> q = em.createQuery(cq);

		final List<IUserAccount> resultList = q.getResultList();
		final int size = resultList.size();
		if (size == 0) {
			return null;
		} else {
			return resultList.get(0);
		}

	}

}
