package com.duskol.ecdlv2.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.duskol.ecdlv2.common.TestStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 
 * Created by Dusko Lucic on Jan 14, 2019
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestDTO implements Serializable {
	
	private static final long serialVersionUID = 7845177678739909979L;
	
	private Long id;
	private String title;
	private Integer duration;
	private BigDecimal passingLimit;
	private TestStatus status;
	private Integer totalExamQuestions;
	private Integer totalQuestions;
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
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public BigDecimal getPassingLimit() {
		return passingLimit;
	}
	public void setPassingLimit(BigDecimal passingLimit) {
		this.passingLimit = passingLimit;
	}
	public TestStatus getStatus() {
		return status;
	}
	public void setStatus(TestStatus status) {
		this.status = status;
	}
	public Integer getTotalExamQuestions() {
		return totalExamQuestions;
	}
	public void setTotalExamQuestions(Integer totalExamQuestions) {
		this.totalExamQuestions = totalExamQuestions;
	}
	public Integer getTotalQuestions() {
		return totalQuestions;
	}
	public void setTotalQuestions(Integer totalQuestions) {
		this.totalQuestions = totalQuestions;
	}
	public List<QuestionDTO> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionDTO> questions) {
		this.questions = questions;
	}
	@Override
	public String toString() {
		return "TestDTO [id=" + id + ", title=" + title + ", duration=" + duration + ", passingLimit=" + passingLimit
				+ ", status=" + status + ", totalExamQuestions=" + totalExamQuestions + ", totalQuestions="
				+ totalQuestions + ", questions=" + questions + "]";
	}
	
}
