package com.surveyapplication.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
	
	// Spring Data JPA Reference Documentation : https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference
	
	// CrudRepository provides default read, write methods.
	
	// If we want to have custom methods, we can do it.
	// Syntax : findBy{column_name}
	// Spring Data JPA will provide the implementation for the custom method.
	// For example : findByRole(String role)
	// Spring Data JPA will provide the implementation to find using the given Role.
	
	List<User> findByRole(String role);
	
	// There are various custom methods that we can make use of.
	// Examples : 
	
// Fetching using 2 columns	
//	List<Person> findByEmailAddressAndLastname(EmailAddress emailAddress, String lastname);
//
//	  // Enables the distinct flag for the query
//	  List<Person> findDistinctPeopleByLastnameOrFirstname(String lastname, String firstname);
//	  List<Person> findPeopleDistinctByLastnameOrFirstname(String lastname, String firstname);
//	
//	  // Enabling ignoring case for an individual property
//	  List<Person> findByLastnameIgnoreCase(String lastname);
//	  // Enabling ignoring case for all suitable properties
//	  List<Person> findByLastnameAndFirstnameAllIgnoreCase(String lastname, String firstname);
//	
//	  // Enabling static ORDER BY for a query
//	  List<Person> findByLastnameOrderByFirstnameAsc(String lastname);
//	  List<Person> findByLastnameOrderByFirstnameDesc(String lastname);
}
