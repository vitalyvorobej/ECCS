package com.itacademy.jd2.vv.cec.dao.orm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Version;

import com.itacademy.jd2.vv.cec.dao.api.model.IBracelet;

@Entity
public class Bracelet extends BaseEntity implements IBracelet {
	@Column
	private String uuid;
	@Column
	private Boolean free;

/*	@Column
	@Version
	private Integer version;*/

	@Override
	public String getUuId() {
		return uuid;
	}

	@Override
	public Boolean getFree() {
		return free;
	}

	@Override
	public void setUuId(final String uuid) {
		this.uuid = uuid;
	}

	@Override
	public void setFree(final Boolean free) {
		this.free = free;
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
