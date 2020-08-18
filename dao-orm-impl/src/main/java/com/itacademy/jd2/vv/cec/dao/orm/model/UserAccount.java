package com.itacademy.jd2.vv.cec.dao.orm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Version;

import com.itacademy.jd2.vv.cec.dao.api.model.IUserAccount;

@Entity
public class UserAccount extends BaseEntity implements IUserAccount {
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private String role;
	/*@Column
	@Version
	private Integer version;*/

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getRole() {
		return role;
	}

	@Override
	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void setRole(final String role) {
		this.role = role;
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
