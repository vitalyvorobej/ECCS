package com.itacademy.jd2.vv.cec.dao.api.filter;

public class TicketTypeFilter extends AbstractFilter {
    private String name;
    private Boolean deleted;
    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
