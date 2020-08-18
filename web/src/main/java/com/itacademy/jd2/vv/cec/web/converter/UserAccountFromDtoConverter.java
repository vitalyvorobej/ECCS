package com.itacademy.jd2.vv.cec.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vv.cec.dao.api.model.IUserAccount;
import com.itacademy.jd2.vv.cec.service.IUserAccountService;
import com.itacademy.jd2.vv.cec.web.dto.UserAccountDTO;

@Component
public class UserAccountFromDtoConverter implements Function<UserAccountDTO, IUserAccount> {

	@Autowired
	private IUserAccountService userAccountService;

	@Override
	public IUserAccount apply(final UserAccountDTO dto) {
		final IUserAccount entity = userAccountService.createEntity();
		entity.setId(dto.getId());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		entity.setRole(dto.getRole());
		/*entity.setVersion(dto.getVersion());*/
		return entity;
	}

}
