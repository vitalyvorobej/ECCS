package com.itacademy.jd2.vv.cec.dao.orm;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vv.cec.dao.api.IOrderObjectDao;
import com.itacademy.jd2.vv.cec.dao.api.filter.OrderObjectFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.IOrderObject;
import com.itacademy.jd2.vv.cec.dao.orm.model.Bracelet_;
import com.itacademy.jd2.vv.cec.dao.orm.model.Card_;
import com.itacademy.jd2.vv.cec.dao.orm.model.OrderObject;
import com.itacademy.jd2.vv.cec.dao.orm.model.OrderObject_;
import com.itacademy.jd2.vv.cec.dao.orm.model.TicketType_;

@Repository
public class OrderObjectDaoImpl extends AbstractDaoImpl<IOrderObject, Integer> implements IOrderObjectDao {

	protected OrderObjectDaoImpl() {
		super(OrderObject.class);
	}

	@Override
	public IOrderObject createEntity() {
		/*
		 * final OrderObject order = new OrderObject(); order.setVersion(IOrderObject.DEFAULT_VERSION); return order;
		 */
		return new OrderObject();
	}

	@Override
	public long getCount(OrderObjectFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<OrderObject> from = cq.from(OrderObject.class);
		cq.select(cb.count(from));
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<IOrderObject> find(OrderObjectFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		// create empty query and define returning type
		final CriteriaQuery<IOrderObject> cq = cb.createQuery(IOrderObject.class);
		// define target entity(table)
		final Root<OrderObject> from = cq.from(OrderObject.class); // select from object
		// define what will be added to result set
		cq.select(from); // select * from object

		from.fetch(OrderObject_.card, JoinType.LEFT);
		applyFilter(filter, cb, cq, from);

		if (filter.getSortColumn() != null) {
			final Path<?> expression = getSortPath(from, filter.getSortColumn());
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}
		// set sort params
		/*
		 * final SingularAttribute sortProperty = filter.getSortProperty(); if (sortProperty != null) { final Path expression =
		 * from.get(sortProperty); cq.orderBy(new OrderImpl(expression, filter.isSortOrder())); }
		 */
		final TypedQuery<IOrderObject> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private Path<?> getSortPath(Root<OrderObject> from, String sortColumn) {
		switch (sortColumn) {
		case "created":
			return from.get(OrderObject_.created);
		case "updated":
			return from.get(OrderObject_.updated);
		case "id":
			return from.get(OrderObject_.id);
		case "start_time":
			return from.get(OrderObject_.startTime);
		case "end_time":
			return from.get(OrderObject_.endTime);
		case "card_id":
			return from.get(OrderObject_.card).get(Card_.id);
		case "ticket_type_id":
			return from.get(OrderObject_.ticketType).get(TicketType_.id);
		case "ticket_price":
			return from.get(OrderObject_.ticketPrice);
		case "bracelet_id":
			return from.get(OrderObject_.bracelet).get(Bracelet_.id);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}

	}

	private void applyFilter(OrderObjectFilter filter, CriteriaBuilder cb, CriteriaQuery<IOrderObject> cq, Root<OrderObject> from) {
		final List<Predicate> ands = new ArrayList<>();

		final Integer id = filter.getCardId();
		if (id != null) {
			ands.add(cb.equal(from.get(OrderObject_.card), id));
		}
		if (!ands.isEmpty()) {
			cq.where(cb.and(ands.toArray(new Predicate[0])));
		}

	}

	@Override
	public IOrderObject getNewesOrder() {
		// TODO Auto-generated method stub
		return null;
	}

}
