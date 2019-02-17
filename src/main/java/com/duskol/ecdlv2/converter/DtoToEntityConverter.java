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
public class DtoToEntityConverter implements DtoToEntityConverterInterface {

	@Override
	public void convert(TestDTO testDTO, Test test) {
		test.setTitle(testDTO.getTitle());
		test.setDuration(testDTO.getDuration());
		test.setPassingLimit(testDTO.getPassingLimit());
		test.setStatus(testDTO.getStatus());
		test.setTotalExamQuestions(testDTO.getTotalExamQuestions());
	}

	@Override
	public void convert(QuestionDTO questionDTO, Question question) {
		question.setText(questionDTO.getText());
		question.setType(questionDTO.getType());
	}

	@Override
	public void convert(AnswerDTO answerDTO, Answer answer) {
		if(answerDTO.getId() != null) {
			answer.setId(answerDTO.getId());
		}
		answer.setIsCorrect(answerDTO.getIsCorrect());
		answer.setText(answerDTO.getText());
	}

}
