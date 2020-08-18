package com.itacademy.jd2.vv.cec.dao.orm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.itacademy.jd2.vv.cec.dao.api.model.ICard;
import com.itacademy.jd2.vv.cec.dao.api.model.IClient;

@Entity
public class Card extends BaseEntity implements ICard {
	@Column
	private Date dateRegistration;
	@Column
	private Boolean active;
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Client.class)
	private IClient client;
	/*@Column
	@Version
	private Integer version;*/

	@Override
	public Date getDateRegistration() {
		return dateRegistration;
	}

	@Override
	public void setDateRegistration(final Date dateRegistration) {
		this.dateRegistration = dateRegistration;

	}

	@Override
	public Boolean getActive() {
		return active;
	}

	@Override
	public void setActive(final Boolean active) {
		this.active = active;
	}

	public IClient getClient() {
		return client;
	}

	public void setClient(IClient client) {
		this.client = client;
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
