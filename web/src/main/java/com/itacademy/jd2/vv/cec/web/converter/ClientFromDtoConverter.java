package com.itacademy.jd2.vv.cec.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import com.itacademy.jd2.vv.cec.dao.api.model.IClient;
import com.itacademy.jd2.vv.cec.service.IClientService;
import com.itacademy.jd2.vv.cec.web.dto.ClientDTO;

@Component
public class ClientFromDtoConverter implements Function<ClientDTO, IClient> {

	@Autowired
	private IClientService clientService;

	@Override
	public IClient apply(final ClientDTO dto) {
		final IClient entity = clientService.createEntity();
		entity.setId(dto.getId());
		entity.setFirstName(dto.getFirst_name());
		entity.setLastName(dto.getLast_name());
		entity.setBirthdayDate(dto.getBirthday_date());
		entity.setPhoneNumber(dto.getPhone_number());
		return entity;
	}

}
