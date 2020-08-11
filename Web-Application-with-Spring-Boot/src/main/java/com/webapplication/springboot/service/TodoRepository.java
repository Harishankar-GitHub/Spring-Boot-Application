package com.webapplication.springboot.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapplication.springboot.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer>
{
	List<Todo> findByUser(String name);
}
