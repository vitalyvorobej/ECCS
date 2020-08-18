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

import com.itacademy.jd2.vv.cec.dao.api.IPaymentDao;
import com.itacademy.jd2.vv.cec.dao.api.filter.PaymentFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.IPayment;
import com.itacademy.jd2.vv.cec.dao.orm.model.OrderObject_;
import com.itacademy.jd2.vv.cec.dao.orm.model.Payment;
import com.itacademy.jd2.vv.cec.dao.orm.model.Payment_;

@Repository
public class PaymentDaoImpl extends AbstractDaoImpl<IPayment, Integer> implements IPaymentDao {
	protected PaymentDaoImpl() {
		super(Payment.class);
	}

	@Override
	public IPayment createEntity() {
		/*
		 * final Payment payment = new Payment(); payment.setVersion(IPayment.DEFAULT_VERSION); return payment;
		 */
		return new Payment();
	}

	@Override
	public long getCount(PaymentFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Payment> from = cq.from(Payment.class);
		cq.select(cb.count(from));
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<IPayment> find(PaymentFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IPayment> cq = cb.createQuery(IPayment.class);
		final Root<Payment> from = cq.from(Payment.class);
		cq.select(from);

		from.fetch(Payment_.order, JoinType.LEFT);

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
		final TypedQuery<IPayment> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private Path<?> getSortPath(Root<Payment> from, String sortColumn) {
		switch (sortColumn) {
		case "created":
			return from.get(Payment_.created);
		case "updated":
			return from.get(Payment_.updated);
		case "id":
			return from.get(Payment_.id);
		case "amount":
			return from.get(Payment_.amount);
		case "order_id":
			return from.get(Payment_.order).get(OrderObject_.id);
		case "payment_type":
			return from.get(Payment_.paymentType);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}

	}

	private void applyFilter(PaymentFilter filter, CriteriaBuilder cb, CriteriaQuery<IPayment> cq, Root<Payment> from) {
		final List<Predicate> ands = new ArrayList<>();
		final Integer id = filter.getOrderId();
		if (id != null) {

			ands.add(cb.equal(from.get(Payment_.order), id));
		}
		if (!ands.isEmpty()) {
			cq.where(cb.and(ands.toArray(new Predicate[0])));
		}
	}

}
