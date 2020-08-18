package com.itacademy.jd2.vv.cec.web.dto.search;

import java.util.Date;

public class CardSearchDTO {
	private Date dateRegistration;
	private Integer clientId;

	public Date getDateRegistration() {
		return dateRegistration;
	}

	public void setDateRegistration(Date dateRegistration) {
		this.dateRegistration = dateRegistration;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
}
