package com.itacademy.jd2.vv.cec.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vv.cec.dao.api.model.IOrderObject;
import com.itacademy.jd2.vv.cec.dao.api.model.IPayment;
import com.itacademy.jd2.vv.cec.service.IOrderObjectService;
import com.itacademy.jd2.vv.cec.service.IPaymentService;
import com.itacademy.jd2.vv.cec.web.dto.PaymentDTO;

@Component
public class PaymentFromDtoConverter implements Function<PaymentDTO, IPayment> {

	@Autowired
	private IPaymentService paymentService;

	@Autowired
	IOrderObjectService orderService;

	@Override
	public IPayment apply(final PaymentDTO dto) {
		final IPayment entity = paymentService.creatEntity();
		entity.setId(dto.getId());
		entity.setAmount(dto.getAmount());
		entity.setPaymentType(dto.getPaymentType());

		final IOrderObject order = orderService.createEntity();
		order.setId(dto.getOrderId());
		entity.setOrder(order);

		return entity;
	}

}
