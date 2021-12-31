package com.surveyapplication.jpa;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // When we use @Entity, a table is created in database and the default name of the table is the Class name.
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
// JPA Expects a default constructor.
// We keep it private because, it need not be used by others outside the class.
@ToString
public class User { // Default table name is the Class name.
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String role;
	
	public User(String name, String role) {
		super();
		this.name = name;
		this.role = role;
	}
}
