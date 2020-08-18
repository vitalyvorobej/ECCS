package com.itacademy.jd2.vv.cec.web.converter;

import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vv.cec.dao.api.model.IBracelet;
import com.itacademy.jd2.vv.cec.service.IBraceletService;
import com.itacademy.jd2.vv.cec.web.dto.BraceletDTO;

@Component
public class BraceletFromDtoConverter implements Function<BraceletDTO, IBracelet> {
	@Autowired
	private IBraceletService braceletService;

	@Override
	public IBracelet apply(final BraceletDTO dto) {
		final IBracelet entity = braceletService.createEntity();
		entity.setId(dto.getId());
		entity.setUuId(dto.getUuid());
		entity.setFree(dto.getFree());
		/*entity.setVersion(dto.getVersion());*/
		return entity;
	}

}
