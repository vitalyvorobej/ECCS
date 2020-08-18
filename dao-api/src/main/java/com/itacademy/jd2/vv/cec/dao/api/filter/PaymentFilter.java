package com.itacademy.jd2.vv.cec.dao.api.filter;

public class PaymentFilter extends AbstractFilter {
    private Double amount;
    private Integer orderId;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(final Double amount) {
        this.amount = amount;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(final Integer orderId) {
        this.orderId = orderId;
    }

}
