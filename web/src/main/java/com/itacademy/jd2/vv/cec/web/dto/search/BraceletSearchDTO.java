package com.itacademy.jd2.vv.cec.web.dto.search;

public class BraceletSearchDTO {
	private String uuid;
	private Boolean free;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(final String uuid) {
		this.uuid = uuid;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(final Boolean free) {
		this.free = free;
	}
}
