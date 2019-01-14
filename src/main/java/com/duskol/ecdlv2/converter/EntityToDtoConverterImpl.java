package com.duskol.ecdlv2.converter;

import org.springframework.stereotype.Component;

import com.duskol.ecdlv2.dto.TestDTO;
import com.duskol.ecdlv2.entity.Test;

/**
 * 
 * Created by Dusko Lucic on Jan 14, 2019
 *
 */
@Component
public class EntityToDtoConverterImpl implements EntityToDtoConverter {

	@Override
	public void convert(Test test, TestDTO testDTO) {
		testDTO.setId(test.getId());
		testDTO.setTitle(test.getTitle());
	}

}
