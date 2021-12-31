package com.webapplication.springboot.service;

import com.webapplication.springboot.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
	List<Todo> findByUser(String name);
}
