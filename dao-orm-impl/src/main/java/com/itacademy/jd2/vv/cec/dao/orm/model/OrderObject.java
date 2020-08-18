package com.itacademy.jd2.vv.cec.dao.orm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.itacademy.jd2.vv.cec.dao.api.model.IBracelet;
import com.itacademy.jd2.vv.cec.dao.api.model.ICard;
import com.itacademy.jd2.vv.cec.dao.api.model.IOrderObject;
import com.itacademy.jd2.vv.cec.dao.api.model.ITicketType;

@Entity
public class OrderObject extends BaseEntity implements IOrderObject {
	@Column
	private Date startTime;
	@Column
	private Date endTime;
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Card.class)
	private ICard card;
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = TicketType.class)
	private ITicketType ticketType;
	@Column
	private Double ticketPrice;
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Bracelet.class)
	private IBracelet bracelet;

	/*@Column
	@Version
	private Integer version;*/

	@Override
	public Date getStartTime() {
		return startTime;
	}

	@Override
	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	@Override
	public Date getEndTime() {
		return endTime;
	}

	@Override
	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}

	public ICard getCard() {
		return card;
	}

	public void setCard(ICard card) {
		this.card = card;
	}

	public ITicketType getTicketType() {
		return ticketType;
	}

	public void setTicketType(ITicketType ticketType) {
		this.ticketType = ticketType;
	}

	public IBracelet getBracelet() {
		return bracelet;
	}

	public void setBracelet(IBracelet bracelet) {
		this.bracelet = bracelet;
	}

	@Override
	public Double getTicketPrice() {
		return ticketPrice;
	}

	@Override
	public void setTicketPrice(final Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

/*	@Override
	public Integer getVersion() {
		return version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}*/

}
