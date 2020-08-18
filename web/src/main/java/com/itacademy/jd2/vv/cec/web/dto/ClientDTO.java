package com.itacademy.jd2.vv.cec.web.dto;

import java.util.Date;

import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class ClientDTO {
	private Integer id;
	@Size(min = 1, max = 30)
	private String first_name;
	@Size(min = 1, max = 50)
	private String last_name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday_date;
	@Size(min = 1, max = 25)
	private String phone_number;
	private Date created;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(final String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(final String last_name) {
		this.last_name = last_name;
	}

	public Date getBirthday_date() {
		return birthday_date;
	}

	public void setBirthday_date(final Date birthday_date) {
		this.birthday_date = birthday_date;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(final String phone_number) {
		this.phone_number = phone_number;
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

	private Date updated;
}
