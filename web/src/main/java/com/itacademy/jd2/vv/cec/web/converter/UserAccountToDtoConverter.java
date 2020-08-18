package com.itacademy.jd2.vv.cec.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vv.cec.dao.api.model.IUserAccount;
import com.itacademy.jd2.vv.cec.web.dto.UserAccountDTO;

@Component
public class UserAccountToDtoConverter implements Function<IUserAccount, UserAccountDTO> {

	@Override
	public UserAccountDTO apply(IUserAccount entity) {
		final UserAccountDTO userDto = new UserAccountDTO();
		userDto.setId(entity.getId());
		userDto.setEmail(entity.getEmail());
		userDto.setPassword(entity.getPassword());
		userDto.setRole(entity.getRole());
		userDto.setCreated(entity.getCreated());
		userDto.setUpdated(entity.getUpdated());
		/*userDto.setVersion(entity.getVersion());*/
		return userDto;
	}
}
