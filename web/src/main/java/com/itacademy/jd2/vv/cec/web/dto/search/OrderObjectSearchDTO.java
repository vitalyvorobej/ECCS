package com.itacademy.jd2.vv.cec.web.dto.search;

import java.util.Date;

public class OrderObjectSearchDTO {
	private Date startTime;
	private Date endTime;
	private Integer cardId;
	private Integer ticketTypeId;
	private Integer braceletId;
	

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	public Integer getTicketTypeId() {
		return ticketTypeId;
	}

	public void setTicketTypeId(Integer ticketTypeId) {
		this.ticketTypeId = ticketTypeId;
	}

	public Integer getBraceletId() {
		return braceletId;
	}

	public void setBraceletId(Integer braceletId) {
		this.braceletId = braceletId;
	}

}
