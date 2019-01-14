package com.duskol.ecdlv2.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * Created by Dusko Lucic on Jan 14, 2019
 *
 */
public class TestDTO implements Serializable {
	
	private static final long serialVersionUID = 7845177678739909979L;
	
	private Long id;
	private String title;
	private List<QuestionDTO> questions;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<QuestionDTO> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionDTO> questions) {
		this.questions = questions;
	}
	@Override
	public String toString() {
		return "TestDTO [id=" + id + ", title=" + title + ", questions=" + questions + "]";
	}
}
