package com.itacademy.jd2.vv.cec.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vv.cec.dao.api.model.ITicketType;
import com.itacademy.jd2.vv.cec.service.ITicketTypeService;
import com.itacademy.jd2.vv.cec.web.dto.TicketTypeDTO;

@Component
public class TicketTypeFromDtoConverter implements Function<TicketTypeDTO, ITicketType> {

	@Autowired
	private ITicketTypeService ticketTypeService;

	@Override
	public ITicketType apply(final TicketTypeDTO dto) {
		final ITicketType entity = ticketTypeService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setPrice(dto.getPrice());
		entity.setDeleted(dto.getDeleted());
		entity.setCreated(dto.getCreated());
		entity.setUpdated(dto.getUpdated());

		return entity;
	}

}
