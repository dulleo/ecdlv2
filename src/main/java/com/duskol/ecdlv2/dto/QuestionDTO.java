package com.duskol.ecdlv2.dto;

import java.io.Serializable;
import java.util.List;

import com.duskol.ecdlv2.common.QuestionType;

/**
 * 
 * Created by Dusko Lucic on Jan 14, 2019
 *
 */
public class QuestionDTO implements Serializable {

	private static final long serialVersionUID = 4513552085459287996L;
	
	private Long id;
	private String text;
	private QuestionType type;
	private List<AnswerDTO> answers;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public QuestionType getType() {
		return type;
	}
	public void setType(QuestionType type) {
		this.type = type;
	}
	public List<AnswerDTO> getAnswers() {
		return answers;
	}
	public void setAnswers(List<AnswerDTO> answers) {
		this.answers = answers;
	}
	@Override
	public String toString() {
		return "QuestionDTO [id=" + id + ", text=" + text + ", type=" + type + ", answers=" + answers + "]";
	}
}
