package com.itacademy.jd2.vv.cec.dao.orm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Version;

import com.itacademy.jd2.vv.cec.dao.api.model.IClient;

@Entity
public class Client extends BaseEntity implements IClient {
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String phoneNumber;
	@Column
	private Date birthdayDate;

	/*@Column
	@Version
	private Integer version;*/

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	public Date getBirthdayDate() {
		return birthdayDate;
	}

	@Override
	public void setBirthdayDate(final Date birthdayDayte) {
		this.birthdayDate = birthdayDayte;
	}

	@Override
	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	@Override
	public void setLastName(final String lastName) {
		this.lastName = lastName;
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
