package com.surveyapplication.jpa;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
// By using above annotation, UserRestRepository is exposed to "http://localhost:8080/users" - GET request.

// We can do a POST request also - "http://localhost:8080/users"
// In POST, we have to pass a RequestBody as well.
//{
//"name": "Harish",
//"role": "Admin"
//}

// We can also access separate records directly.
// GET - http://localhost:8080/users/1

// We can do
// PUT - http://localhost:8080/users/1
// RequestBody
//{
//"name": "Harish - Updated",
//"role": "Admin - Updated"
//}

// In GET, we can specify the number of records we want to retrieve
// http://localhost:8080/users?size=3
// When we hit with size, it returns the requested number of records and also links to
// next, prev, last page etc.
// Those additional links are known as HATEOAS
// HATEOAS - Hypermedia As Representation Of Application State

// We can do a sort and retrieve
// GET - http://localhost:8080/users/?sort=name,desc

public interface UserRestRepository extends PagingAndSortingRepository<User, Long>
{
	// If we need to expose a method, we can do it using @Param annotation.
	// http://localhost:8080/users/search/findByRole?role=Admin
	List<User> findByRole(@Param("role") String role);
}
