package com.webapplication.springboot.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.webapplication.springboot.model.Todo;
import com.webapplication.springboot.service.TodoService;

@Controller
//@SessionAttributes("name")
public class TodoController
{
	@Autowired
	TodoService todoService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)	// http://localhost:8080/list-todos
	public String showTodos(ModelMap model)
	{
//		model.put("todos", todoService.retrieveTodos("in28Minutes"));
		// Instead of using hard coded name to retrieve todos, I can fetch the name from session.
		String name = getLoggedInUserName(model);
		model.put("todos", todoService.retrieveTodos(name));
		return "list-todos";	// returns list-todos.jsp
	}

//	private String getLoggedInUserName(ModelMap model) {
//		return (String) model.get("name");
//	}
	
	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}
	
	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)	// http://localhost:8080/add-todo
	public String showAddTodo(ModelMap model)
	{
		model.addAttribute("todo", new Todo(0, getLoggedInUserName(model), "", new Date(), false));
		return "todo";	// returns add-todo.jsp
	}
	
	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)	// http://localhost:8080/add-todo
	public String deleteTodo(@RequestParam int id)
	{
		if (id == 4)
		{
			throw new RuntimeException("Throwing Runtime Exception");
		}
		// Above condition is to demonstrate error page.
		
		todoService.deleteTodo(id);
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)	// http://localhost:8080/add-todo
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model)
	{
		Todo todo = todoService.retrieveTodo(id);
		model.put("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)	// http://localhost:8080/add-todo
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result)
	{
		if (result.hasErrors())
		{
			return "todo";
		}
		
		todo.setUser(getLoggedInUserName(model));
		todoService.updateTodo(todo);
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)	// http://localhost:8080/add-todo
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result)
	{
		if (result.hasErrors())
		{
			return "todo";
		}
		String name = getLoggedInUserName(model);
		todoService.addTodo(name, todo.getDesc(), todo.getTargetDate(), false);
		return "redirect:/list-todos";
	}
}
