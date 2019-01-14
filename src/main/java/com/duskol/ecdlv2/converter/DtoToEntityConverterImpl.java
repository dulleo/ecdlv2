package com.duskol.ecdlv2.converter;

import org.springframework.stereotype.Component;

import com.duskol.ecdlv2.dto.QuestionDTO;
import com.duskol.ecdlv2.dto.TestDTO;
import com.duskol.ecdlv2.entity.Question;
import com.duskol.ecdlv2.entity.Test;

/**
 * 
 * Created by Dusko Lucic on Jan 14, 2019
 *
 */
@Component
public class DtoToEntityConverterImpl implements DtoToEntityConverter {

	@Override
	public void convert(TestDTO testDTO, Test test) {
		
		
	}

	@Override
	public void convert(QuestionDTO questionDTO, Question question) {
		question.setText(questionDTO.getText());
		question.setType(questionDTO.getType());
	}

}
