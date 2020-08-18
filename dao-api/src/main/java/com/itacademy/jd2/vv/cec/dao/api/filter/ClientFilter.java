package com.itacademy.jd2.vv.cec.dao.api.filter;

import java.util.Date;

public class ClientFilter extends AbstractFilter {
    private String first_name;
    private String last_name;
    private Date birthday_date;
    private String phone_number;

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
}
