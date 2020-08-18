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

import com.itacademy.jd2.vv.cec.dao.api.IClientDao;
import com.itacademy.jd2.vv.cec.dao.api.filter.ClientFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.IClient;
import com.itacademy.jd2.vv.cec.dao.orm.model.Client;
import com.itacademy.jd2.vv.cec.dao.orm.model.Client_;

@Repository
public class ClientDaoImpl extends AbstractDaoImpl<IClient, Integer> implements IClientDao {
	protected ClientDaoImpl() {
		super(Client.class);
	}

	@Override
	public IClient createEntity() {
		/*
		 * final Client client = new Client(); client.setVersion(IClient.DEFAULT_VERSION); return client;
		 */
		return new Client();
	}

	@Override
	public List<IClient> find(final ClientFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IClient> cq = cb.createQuery(IClient.class);
		final Root<Client> from = cq.from(Client.class);
		cq.select(from);

		applyFilter(filter, cb, cq, from);
		// set sort params
		/*
		 * final SingularAttribute sortProperty = filter.getSortProperty(); if (sortProperty != null) { final Path expression =
		 * from.get(sortProperty); cq.orderBy(new OrderImpl(expression, filter.isSortOrder())); }
		 */
		if (filter.getSortColumn() != null) {
			final Path<?> expression = getSortPath(from, filter.getSortColumn());
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}
		final TypedQuery<IClient> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();

	}

	private Path<?> getSortPath(Root<Client> from, String sortColumn) {
		switch (sortColumn) {
		case "created":
			return from.get(Client_.created);
		case "updated":
			return from.get(Client_.updated);
		case "id":
			return from.get(Client_.id);
		case "first_name":
			return from.get(Client_.firstName);
		case "last_name":
			return from.get(Client_.lastName);
		case "birthday_date":
			return from.get(Client_.birthdayDate);
		case "phone_number":
			return from.get(Client_.phoneNumber);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);

		}

	}

	private void applyFilter(ClientFilter filter, CriteriaBuilder cb, CriteriaQuery<IClient> cq, Root<Client> from) {
		final List<Predicate> ands = new ArrayList<>();

		final String name = filter.getLast_name();
		if (!StringUtils.isBlank(name)) {
			ands.add(cb.like(from.get(Client_.lastName), "%" + name + "%"));
		}
		// тут пилим параметры поиска
		/*
		 * final Boolean active = filter.getActive(); if (Boolean.FALSE.equals(active)) { ands.add(cb.equal(from.get(Card_.active), false));
		 * }
		 */
		if (!ands.isEmpty()) {
			cq.where(cb.and(ands.toArray(new Predicate[0])));
		}

	}

	@Override
	public long getCount(final ClientFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Client> from = cq.from(Client.class);
		cq.select(cb.count(from));
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

}
