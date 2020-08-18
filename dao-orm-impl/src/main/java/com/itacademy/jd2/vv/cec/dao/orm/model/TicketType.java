package com.itacademy.jd2.vv.cec.dao.orm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Version;

import com.itacademy.jd2.vv.cec.dao.api.model.ITicketType;

@Entity
public class TicketType extends BaseEntity implements ITicketType {
	@Column
	private String name;
	@Column
	private Double price;
	@Column
	private Boolean deleted;

	/*@Column
	@Version
	private Integer version;*/

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public Double getPrice() {
		return price;
	}

	@Override
	public void setPrice(final Double price) {
		this.price = price;
	}

	@Override
	public Boolean getDeleted() {
		return deleted;
	}

	@Override
	public void setDeleted(final Boolean deleted) {
		this.deleted = deleted;
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
