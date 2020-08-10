package com.surveyapplication;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.surveyapplication.configuration.BasicConfiguration;

@RestController
public class WelcomeController
{
	
	@Autowired
	private WelcomeService service;
	
	@Autowired
	private BasicConfiguration basicConfiguration; 
	
	@RequestMapping("/welcome")
	public String welcome()
	{
//		return "This is great ! I see something on screen !";
		return service.retrieveWelcomeMessage();
	}
	
	@RequestMapping("/dynamic-configuration")
	public Map dynamicConfiguration()
	{
		Map map = new HashMap();
		
		map.put("message", basicConfiguration.getMessage());
		map.put("number", basicConfiguration.getNumber());
		map.put("key", basicConfiguration.isValue());
		
		return map;
	}
}