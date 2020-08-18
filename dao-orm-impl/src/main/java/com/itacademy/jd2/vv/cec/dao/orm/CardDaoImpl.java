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

import com.itacademy.jd2.vv.cec.dao.api.ICardDao;
import com.itacademy.jd2.vv.cec.dao.api.filter.CardFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.ICard;
import com.itacademy.jd2.vv.cec.dao.orm.model.Card;
import com.itacademy.jd2.vv.cec.dao.orm.model.Card_;
import com.itacademy.jd2.vv.cec.dao.orm.model.Client_;

@Repository
public class CardDaoImpl extends AbstractDaoImpl<ICard, Integer> implements ICardDao {

	protected CardDaoImpl() {
		super(Card.class);
	}

	@Override
	public ICard createEntity() {
		/*
		 * final Card card = new Card(); card.setVersion(ICard.DEFAULT_VERSION); return card;
		 */
		return new Card();
	}

	@Override
	public long getCount(final CardFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Card> from = cq.from(Card.class);
		cq.select(cb.count(from));
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<ICard> find(final CardFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ICard> cq = cb.createQuery(ICard.class);
		final Root<Card> from = cq.from(Card.class);
		cq.select(from);

		from.fetch(Card_.client, JoinType.LEFT);
		applyFilter(filter, cb, cq, from);

		if (filter.getSortColumn() != null) {
			final Path<?> expression = getSortPath(from, filter.getSortColumn());
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<ICard> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private Path<?> getSortPath(Root<Card> from, String sortColumn) {
		switch (sortColumn) {
		case "created":
			return from.get(Card_.created);
		case "updated":
			return from.get(Card_.updated);
		case "id":
			return from.get(Card_.id);
		case "date_registration":
			return from.get(Card_.dateRegistration);
		case "client_id":
			return from.get(Card_.client).get(Client_.id);
		case "active":
			return from.get(Card_.active);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}

	}

	private void applyFilter(CardFilter filter, CriteriaBuilder cb, CriteriaQuery<ICard> cq, Root<Card> from) {
		final List<Predicate> ands = new ArrayList<>();

		final Integer id = filter.getClientId();
		if (id != null) {
			ands.add(cb.equal(from.get(Card_.client), id));
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
	public ICard getNewestCard() {
		// TODO Auto-generated method stub
		return null;
	}

}
