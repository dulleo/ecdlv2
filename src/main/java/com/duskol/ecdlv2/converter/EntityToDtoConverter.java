package com.duskol.ecdlv2.converter;

import org.springframework.stereotype.Component;

import com.duskol.ecdlv2.dto.AnswerDTO;
import com.duskol.ecdlv2.dto.QuestionDTO;
import com.duskol.ecdlv2.dto.TestDTO;
import com.duskol.ecdlv2.entity.Answer;
import com.duskol.ecdlv2.entity.Question;
import com.duskol.ecdlv2.entity.Test;

/**
 * 
 * Created by Dusko Lucic on Jan 14, 2019
 *
 */
@Component
public class EntityToDtoConverter implements EntityToDtoConverterInterface {

	@Override
	public void convert(Test test, TestDTO testDTO) {
		testDTO.setId(test.getId());
		testDTO.setTitle(test.getTitle());
		testDTO.setDuration(test.getDuration());
		testDTO.setPassingLimit(test.getPassingLimit());
		testDTO.setStatus(test.getStatus());
		testDTO.setTotalExamQuestions(test.getTotalExamQuestions());
	}

	@Override
	public void convert(Question question, QuestionDTO questionDTO) {
		questionDTO.setId(question.getId());
		questionDTO.setText(question.getText());
		questionDTO.setType(question.getType());
	}

	@Override
	public void convert(Answer answer, AnswerDTO answerDTO) {
		answerDTO.setId(answer.getId());
		answerDTO.setText(answer.getText());
		answerDTO.setIsCorrect(answer.getIsCorrect());
	}

}
