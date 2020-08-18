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

import com.itacademy.jd2.vv.cec.dao.api.ITicketTypeDao;
import com.itacademy.jd2.vv.cec.dao.api.filter.TicketTypeFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.ITicketType;
import com.itacademy.jd2.vv.cec.dao.orm.model.TicketType;
import com.itacademy.jd2.vv.cec.dao.orm.model.TicketType_;

@Repository
public class TicketTypeDaoImpl extends AbstractDaoImpl<ITicketType, Integer> implements ITicketTypeDao {

	protected TicketTypeDaoImpl() {
		super(TicketType.class);
	}

	@Override
	public ITicketType createEntity() {
		/*
		 * final TicketType ticket = new TicketType(); ticket.setVersion(ITicketType.DEFAULT_VERSION); return ticket;
		 */
		return new TicketType();
	}

	@Override
	public List<ITicketType> find(final TicketTypeFilter filter) {

		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ITicketType> cq = cb.createQuery(ITicketType.class);
		final Root<TicketType> from = cq.from(TicketType.class);

		cq.select(from);

		applyFilter(filter, cb, cq, from);

		if (filter.getSortColumn() != null) {
			final Path<?> expression = getSortPath(from, filter.getSortColumn());
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<ITicketType> q = em.createQuery(cq);
		setPaging(filter, q);

		return q.getResultList();
	}

	private Path<?> getSortPath(Root<TicketType> from, String sortColumn) {
		switch (sortColumn) {
		case "created":
			return from.get(TicketType_.created);
		case "updated":
			return from.get(TicketType_.updated);
		case "id":
			return from.get(TicketType_.id);
		case "name":
			return from.get(TicketType_.name);
		case "price":
			return from.get(TicketType_.price);
		case "deleted":
			return from.get(TicketType_.deleted);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	private void applyFilter(TicketTypeFilter filter, CriteriaBuilder cb, CriteriaQuery<ITicketType> cq, Root<TicketType> from) {
		final List<Predicate> ands = new ArrayList<>();

		final String name = filter.getName();
		if (!StringUtils.isBlank(name)) {
			ands.add(cb.equal(from.get(TicketType_.name), name));
		}
		if (!ands.isEmpty()) {
			cq.where(cb.and(ands.toArray(new Predicate[0])));
		}
	}

	@Override
	public long getCount(TicketTypeFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<TicketType> from = cq.from(TicketType.class);
		cq.select(cb.count(from));
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

}
