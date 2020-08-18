package com.itacademy.jd2.vv.cec.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vv.cec.dao.api.model.IBracelet;
import com.itacademy.jd2.vv.cec.web.dto.BraceletDTO;

@Component
public class BraceletToDtoConverter implements Function<IBracelet, BraceletDTO> {

	@Override
	public BraceletDTO apply(final IBracelet entity) {
		final BraceletDTO braceletDto = new BraceletDTO();
		braceletDto.setId(entity.getId());
		braceletDto.setUuid(entity.getUuId());
		braceletDto.setFree(entity.getFree());
		braceletDto.setCreated(entity.getCreated());
		braceletDto.setUpdated(entity.getUpdated());
		/*braceletDto.setVersion(entity.getVersion());*/

		return braceletDto;
	}

}
