package com.itacademy.jd2.vv.cec.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vv.cec.dao.api.model.ICard;
import com.itacademy.jd2.vv.cec.dao.api.model.IClient;
import com.itacademy.jd2.vv.cec.web.dto.CardDTO;

@Component
public class CardToDtoConverter implements Function<ICard, CardDTO> {

	@Override
	public CardDTO apply(final ICard entity) {
		final CardDTO dto = new CardDTO();
		dto.setId(entity.getId());
		dto.setDateRegistration(entity.getDateRegistration());
		dto.setActive(entity.getActive());
		/*dto.setVersion(entity.getVersion());*/
		final IClient client = entity.getClient();
		dto.setClientId(client.getId());
		dto.setClientFirstName(client.getFirstName());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}

}
