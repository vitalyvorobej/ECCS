package com.itacademy.jd2.vv.cec.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vv.cec.dao.api.model.ITicketType;
import com.itacademy.jd2.vv.cec.web.dto.TicketTypeDTO;

@Component
public class TicketTypeToDtoConverter implements Function<ITicketType, TicketTypeDTO> {

	@Override
	public TicketTypeDTO apply(final ITicketType entity) {
		final TicketTypeDTO ticketTypeDto = new TicketTypeDTO();

		ticketTypeDto.setId(entity.getId());
		ticketTypeDto.setName(entity.getName());
		ticketTypeDto.setPrice(entity.getPrice());
		ticketTypeDto.setDeleted(entity.getDeleted());
		ticketTypeDto.setCreated(entity.getCreated());
		ticketTypeDto.setUpdated(entity.getUpdated());

		return ticketTypeDto;
	}

}
