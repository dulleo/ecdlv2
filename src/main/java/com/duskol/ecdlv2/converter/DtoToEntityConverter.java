package com.duskol.ecdlv2.converter;

import com.duskol.ecdlv2.dto.TestDTO;
import com.duskol.ecdlv2.entity.Test;

/**
 * 
 * Created by Dusko Lucic on Jan 14, 2019
 *
 */
public interface DtoToEntityConverter {

	void convert(TestDTO testDTO, Test test);
}
