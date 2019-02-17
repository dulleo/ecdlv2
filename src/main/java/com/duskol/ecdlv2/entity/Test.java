package com.duskol.ecdlv2.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.duskol.ecdlv2.common.TestStatus;

/**
 * 
 * Created by Dusko Lucic on Jan 14, 2019
 *
 */
@Entity
@Table(name="tests")
public class Test {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(name="title")
	private String title;
	
	@Column(name="duration")
	private Integer duration;
	
	@Column(name="passing_limit")
	private BigDecimal passingLimit;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private TestStatus status;
	
	@Column(name="total_exam_questions")
	private Integer totalExamQuestions;

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
}
