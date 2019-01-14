package com.duskol.ecdlv2.converter;

import com.duskol.ecdlv2.dto.QuestionDTO;
import com.duskol.ecdlv2.dto.TestDTO;
import com.duskol.ecdlv2.entity.Question;
import com.duskol.ecdlv2.entity.Test;

/**
 * 
 * Created by Dusko Lucic on Jan 14, 2019
 *
 */
public interface EntityToDtoConverter {
	
	void convert(Test test, TestDTO testDTO);

	void convert(Question question, QuestionDTO questionDTO);

}
