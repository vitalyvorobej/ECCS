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

import com.itacademy.jd2.vv.cec.dao.api.IBraceletDao;
import com.itacademy.jd2.vv.cec.dao.api.filter.BraceletFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.IBracelet;
import com.itacademy.jd2.vv.cec.dao.orm.model.Bracelet;
import com.itacademy.jd2.vv.cec.dao.orm.model.Bracelet_;

@Repository
public class BraceletDaoImpl extends AbstractDaoImpl<IBracelet, Integer> implements IBraceletDao {

	protected BraceletDaoImpl() {
		super(Bracelet.class);
	}

	@Override
	public IBracelet createEntity() {
		/*
		 * final Bracelet bracelet = new Bracelet(); bracelet.setVersion(IBracelet.DEFAULT_VERSION); return bracelet;
		 */
		return new Bracelet();
	}

	@Override
	public List<IBracelet> find(final BraceletFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IBracelet> cq = cb.createQuery(IBracelet.class);
		final Root<Bracelet> from = cq.from(Bracelet.class);

		cq.select(from);

		applyFilter(filter, cb, cq, from);

		if (filter.getSortColumn() != null) {
			final Path<?> expression = getSortPath(from, filter.getSortColumn());
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IBracelet> q = em.createQuery(cq);
		// TODO search paging
		setPaging(filter, q);

		return q.getResultList();
	}

	private Path<?> getSortPath(Root<Bracelet> from, String sortColumn) {
		switch (sortColumn) {
		case "created":
			return from.get(Bracelet_.created);
		case "updated":
			return from.get(Bracelet_.updated);
		case "id":
			return from.get(Bracelet_.id);
		case "uuid":
			return from.get(Bracelet_.uuid);
		case "free":
			return from.get(Bracelet_.free);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}

	}

	private void applyFilter(BraceletFilter filter, CriteriaBuilder cb, CriteriaQuery<IBracelet> cq, Root<Bracelet> from) {
		final List<Predicate> ands = new ArrayList<>();

		final String uuid = filter.getUuid();
		if (!StringUtils.isBlank(uuid)) {
			ands.add(cb.equal(from.get(Bracelet_.uuid), uuid));
		}

		if (!ands.isEmpty()) {
			cq.where(cb.and(ands.toArray(new Predicate[0])));
		}

	}

	@Override
	public long getCount(BraceletFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Bracelet> from = cq.from(Bracelet.class);
		cq.select(cb.count(from));
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

}
