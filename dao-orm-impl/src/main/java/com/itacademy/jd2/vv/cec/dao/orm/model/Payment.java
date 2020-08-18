package com.itacademy.jd2.vv.cec.dao.orm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.itacademy.jd2.vv.cec.dao.api.model.IOrderObject;
import com.itacademy.jd2.vv.cec.dao.api.model.IPayment;

@Entity
public class Payment extends BaseEntity implements IPayment {
	@Column
	private Double amount;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = OrderObject.class)
	private IOrderObject order;

	@Column
	private String paymentType;

	/*@Column
	@Version
	private Integer version;*/

	@Override
	public Double getAmount() {
		return amount;
	}

	@Override
	public void setAmount(final Double amount) {
		this.amount = amount;
	}

	public IOrderObject getOrder() {
		return order;
	}

	public void setOrder(IOrderObject order) {
		this.order = order;
	}

	@Override
	public String getPaymentType() {
		return paymentType;
	}

	@Override
	public void setPaymentType(final String paymenttype) {
		this.paymentType = paymenttype;
	}

	/*@Override
	public Integer getVersion() {
		return version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}*/

}
