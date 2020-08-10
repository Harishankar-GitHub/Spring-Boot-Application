package com.surveyapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
//@ComponentScan("com.surveyapplication")
public class Application {

	public static void main(String[] args)
	{
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
	}
	
	@Profile("prod")
	// When we use @Profile here. this bean will be created only in Dev profile.
	@Bean
	public String dummy()
	{
		return "Something";
	}
}