package com.surveyapplication.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // When we use @Entity, a table is created in database and the default name of the table is the Class name.
public class User // Default table name is the Class name.
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String role;
	
	private User()
	{
		// JPA Expects a default constructor.
		// We keep it private because, it need not be used by others outside the class.
	}
	
	public User(String name, String role) {
		super();
		this.name = name;
		this.role = role;
	}

	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getRole() {
		return role;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, name=%s, role=%s]", id, name, role);
	}
}
