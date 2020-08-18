package com.itacademy.jd2.vv.cec.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vv.cec.dao.api.model.IBracelet;
import com.itacademy.jd2.vv.cec.dao.api.model.ICard;
import com.itacademy.jd2.vv.cec.dao.api.model.IOrderObject;
import com.itacademy.jd2.vv.cec.dao.api.model.ITicketType;
import com.itacademy.jd2.vv.cec.web.dto.OrderObjectDTO;

@Component
public class OrderObjectToDtoConverter implements Function<IOrderObject, OrderObjectDTO> {

	@Override
	public OrderObjectDTO apply(IOrderObject entity) {
		final OrderObjectDTO dto = new OrderObjectDTO();
		dto.setId(entity.getId());
		dto.setStartTime(entity.getStartTime());
		dto.setEndTime(entity.getEndTime());
		dto.setTicketPrice(entity.getTicketPrice());

		final ICard card = entity.getCard();
		dto.setCardId(card.getId());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		// возможно понадобиться дописывать orderobjectDto дополнительные поля из таблиц связок

		final ITicketType ticket = entity.getTicketType();
		dto.setTicketTypeId(ticket.getId());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());

		final IBracelet bracelet = entity.getBracelet();
		dto.setBraceletId(bracelet.getId());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}

}
