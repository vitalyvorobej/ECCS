package com.itacademy.jd2.vv.cec.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vv.cec.dao.api.model.IOrderObject;
import com.itacademy.jd2.vv.cec.dao.api.model.IPayment;
import com.itacademy.jd2.vv.cec.web.dto.PaymentDTO;

@Component
public class PaymentToDtoConverter implements Function<IPayment, PaymentDTO> {

	@Override
	public PaymentDTO apply(IPayment entity) {
		final PaymentDTO dto = new PaymentDTO();
		dto.setId(entity.getId());
		dto.setAmount(entity.getAmount());
		dto.setPaymentType(entity.getPaymentType());

		final IOrderObject order = entity.getOrder();
		dto.setOrderId(order.getId());

		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}

}
