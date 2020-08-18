package com.itacademy.jd2.vv.cec.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vv.cec.dao.api.model.ICard;
import com.itacademy.jd2.vv.cec.dao.api.model.IClient;
import com.itacademy.jd2.vv.cec.service.ICardService;
import com.itacademy.jd2.vv.cec.service.IClientService;
import com.itacademy.jd2.vv.cec.web.dto.CardDTO;

@Component
public class CardFromDtoConverter implements Function<CardDTO, ICard> {

	@Autowired
	private ICardService cardService;
	@Autowired
	private IClientService clientService;

	@Override
	public ICard apply(final CardDTO dto) {
		final ICard entity = cardService.createEntity();
		entity.setId(dto.getId());
		entity.setDateRegistration(dto.getDateRegistration());
		entity.setActive(dto.getActive());
		/*entity.setVersion(dto.getVersion());*/

		final IClient client = clientService.createEntity();
		client.setId(dto.getClientId());
		entity.setClient(client);

		return entity;
	}

}
