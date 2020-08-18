package com.itacademy.jd2.vv.cec.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vv.cec.dao.api.model.IBracelet;
import com.itacademy.jd2.vv.cec.dao.api.model.ICard;
import com.itacademy.jd2.vv.cec.dao.api.model.IOrderObject;
import com.itacademy.jd2.vv.cec.dao.api.model.ITicketType;
import com.itacademy.jd2.vv.cec.service.IBraceletService;
import com.itacademy.jd2.vv.cec.service.ICardService;
import com.itacademy.jd2.vv.cec.service.IOrderObjectService;
import com.itacademy.jd2.vv.cec.service.ITicketTypeService;
import com.itacademy.jd2.vv.cec.web.dto.OrderObjectDTO;

@Component
public class OrderObjectFromDtoConverter implements Function<OrderObjectDTO, IOrderObject> {
	@Autowired
	private IOrderObjectService orderService;
	@Autowired
	private ICardService cardService;
	@Autowired
	private ITicketTypeService ticketService;
	@Autowired
	private IBraceletService braceletService;

	@Override
	public IOrderObject apply(OrderObjectDTO dto) {
		final IOrderObject entity = orderService.createEntity();
		entity.setId(dto.getId());
		entity.setStartTime(dto.getStartTime());
		entity.setEndTime(dto.getEndTime());
		entity.setTicketPrice(dto.getTicketPrice());

		final ICard card = cardService.createEntity();
		card.setId(dto.getCardId());
		entity.setCard(card);

		final ITicketType ticket = ticketService.createEntity();
		ticket.setId(dto.getTicketTypeId());
		entity.setTicketType(ticket);

		final IBracelet bracelet = braceletService.createEntity();
		bracelet.setId(dto.getBraceletId());
		entity.setBracelet(bracelet);

		return entity;

	}

}
