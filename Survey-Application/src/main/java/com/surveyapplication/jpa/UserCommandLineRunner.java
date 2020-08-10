package com.surveyapplication.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserCommandLineRunner implements CommandLineRunner
{
	
	// CommandLineRunner is executed when the Application is started.
	
	private static final Logger logger = LoggerFactory.getLogger(UserCommandLineRunner.class);
	
	@Autowired
	private UserRepository repository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Command Line Runner");
		
		repository.save(new User("Ranga", "Admin"));
        repository.save(new User("Ravi", "User"));
        repository.save(new User("Satish", "Admin"));
        repository.save(new User("Raghu", "User"));

        // repository.save() is one of the default methods in CRUD Repository
        // repository.findAll() is also one of the default methods in CRUD Repository which returns all entities.
        // There are several other methods like save(), saveAll(), count(), findById(), delete() etc.
        
        logger.info("All users :");
        for (User user : repository.findAll())
        {
        	logger.info(user.toString());
        }
        
        logger.info("Admin users :");
        for (User user : repository.findByRole("Admin"))
        {
        	logger.info(user.toString());
        }
	}
}
