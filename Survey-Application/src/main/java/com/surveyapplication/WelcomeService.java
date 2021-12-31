package com.surveyapplication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WelcomeService {
	
	@Value("${welcome.message}")
	// @Value annotation autowires a property from application.properties file and 
	// we can use it where ever we want.
	private String welcomeMessage;
	
	public String retrieveWelcomeMessage() {
		//Complex Method
//		return "Good Morning updated";
		return welcomeMessage;
	}
}