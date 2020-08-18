package com.itacademy.jd2.vv.cec.web.converter;

import org.springframework.stereotype.Component;
import java.util.function.Function;
import com.itacademy.jd2.vv.cec.dao.api.model.IClient;
import com.itacademy.jd2.vv.cec.web.dto.ClientDTO;

@Component
public class ClientToDtoConverter implements Function<IClient, ClientDTO> {

	@Override
	public ClientDTO apply(final IClient entity) {
		final ClientDTO clientDto = new ClientDTO();
		clientDto.setId(entity.getId());
		clientDto.setFirst_name(entity.getFirstName());
		clientDto.setLast_name(entity.getLastName());
		clientDto.setBirthday_date(entity.getBirthdayDate());
		clientDto.setPhone_number(entity.getPhoneNumber());
		clientDto.setCreated(entity.getCreated());
		clientDto.setUpdated(entity.getUpdated());

		return clientDto;
	}

}
