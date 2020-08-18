package com.itacademy.jd2.vv.cec.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class BraceletDTO {
	private Integer id;
	@Size(min = 1, max = 25)
	private String uuid;
	private Boolean free;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(final String uuid) {
		this.uuid = uuid;
	}

	public Boolean getFree() {
		return free;
	}

	public void setFree(final Boolean free) {
		this.free = free;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(final Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(final Date updated) {
		this.updated = updated;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updated;

/*	@NotNull
	private Integer version;

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}*/
}
