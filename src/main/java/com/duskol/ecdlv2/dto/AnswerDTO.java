package com.duskol.ecdlv2.dto;

import java.io.Serializable;

/**
 * 
 * Created by Dusko Lucic on Jan 14, 2019
 *
 */
public class AnswerDTO implements Serializable {
	
	private static final long serialVersionUID = -4004650756892524681L;
	
	private Long id;
	private String text;
	private Boolean isCorrect;
	
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
	public Boolean getIsCorrect() {
		return isCorrect;
	}
	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	@Override
	public String toString() {
		return "AnswerDTO [id=" + id + ", text=" + text + ", isCorrect=" + isCorrect + "]";
	}
}
