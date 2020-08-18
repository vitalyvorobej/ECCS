package com.itacademy.jd2.vv.cec.dao.api.filter;

public class UserAccountFilter extends AbstractFilter {
    private String email;
    private String passWord;
    private String role;

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }


    public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getRole() {
        return role;
    }

    public void setRole(final String role) {
        this.role = role;
    }

}
