package com.itacademy.jd2.vv.cec.dao.api;

import java.util.List;

import com.itacademy.jd2.vv.cec.dao.api.filter.PaymentFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.IPayment;

public interface IPaymentDao extends BaseDao<IPayment, Integer> {

    long getCount(PaymentFilter filter);

    List<IPayment> find(PaymentFilter filter);

}
