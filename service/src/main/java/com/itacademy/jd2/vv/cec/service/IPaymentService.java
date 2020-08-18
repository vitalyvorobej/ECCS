package com.itacademy.jd2.vv.cec.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vv.cec.dao.api.filter.PaymentFilter;
import com.itacademy.jd2.vv.cec.dao.api.model.IPayment;

public interface IPaymentService {

    IPayment get(Integer id);

    IPayment creatEntity();

    List<IPayment> getAll();

    @Transactional
    void save(IPayment entity);

    @Transactional
    void delete(Integer id);

    @Transactional
    void deleteAll();

    List<IPayment> find(PaymentFilter filter);

    long getCount(PaymentFilter filter);

    IPayment getNewestPayment();
}
