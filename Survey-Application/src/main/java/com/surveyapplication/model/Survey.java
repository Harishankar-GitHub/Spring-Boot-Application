package com.surveyapplication.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString
public class Survey {
	private String id;
	private String title;
	private String description;
	private List<Question> questions;

	public Survey(String id, String title, String description,	List<Question> questions) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.questions = questions;
	}
}