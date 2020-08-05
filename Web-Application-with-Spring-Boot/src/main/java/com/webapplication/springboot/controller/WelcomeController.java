package com.webapplication.springboot.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@SessionAttributes("name")
// We are using @SessionAttributes because, the value of name in model.put("name", name) is required in other requests
// or other controllers.
// If I want value of name in TodoController, I can use the same annotation there @SessionAttributes("name").
// To get the value, I use model.get("name").
public class WelcomeController
{
	
	// Main concepts of Spring are :
	// 1. Component Scan - Finds all the components needed, injects where ever needed and manages
	// the life cycle.
	// 2. Auto wiring
	
//	@Autowired	// We use @Autowired because, we need the LoginService here. 
//	LoginService loginService;
		
//	@RequestMapping("/login")	// http://localhost:8080/login
//	@ResponseBody
//	// We use @ResponseBody because, the String will be returned to the browser as it is.
//	// If @ResponseBody is not used, it will look for something like jsp or html to return the string.
//	public String loginMessage()
//	{
//		return "Hello World modified";
//	}
	
	
//	// The below method is to return a JSP when /login is called.
//	// To enable jsp support in embedded tomcat server, 1 dependency is added in pom.xml
//	@RequestMapping("/login")	// http://localhost:8080/login
//	public String loginMessage()
//	{
//		return "login";	// returns login.jsp
//	}
	
//	@RequestMapping("/login")	// http://localhost:8080/login?name=harish
//	public String loginMessageWithParameter(@RequestParam String name, ModelMap model)
//	// @RequestParam is used to access the variables that is present in the url.
//	// ModelMap is used to create a model and it will be available to jsp.
//	{
//		model.put("name", name);	// This is available to jsp.
//		System.out.println("GET Parameter : " + name);
//		return "login";	// returns login.jsp
//	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)	// http://localhost:8080/login
	public String showWelcomePage(ModelMap model)
	{
		model.put("name", getLoggedInUserName(model));
		return "welcome";	// returns welcome.jsp
	}
	
	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}
	
//	@RequestMapping(value = "/login", method = RequestMethod.POST)	// http://localhost:8080/login
//	public String showWelcomePage(ModelMap model, @RequestParam String name, @RequestParam String password)
//	// ModelMap is used to create a model and it will be available to jsp.
//	{
//		boolean isValidUser = loginService.validateUser(name, password);
//		
//		if (!isValidUser)
//		{
//			model.put("errorMessage", "Invalid Credentials");
//			return "login";
//		}
//		else
//		{
//			model.put("name", name);
//			model.put("password", password);
//			return "welcome";		// returns welcome.jsp
//		}
//	}
}
