package com.webapplication.springboot.service;

import org.springframework.stereotype.Component;

// We need to tell Spring that this is a bean.
// Spring needs to create an instance of this.
// So, we use @Component
// Once it is done, Spring will create an instance of LoginService.
// The entire life cycle of that bean will be managed by Spring
// To use this in controller, we use @Autowired in controller.
@Component
public class LoginService
{	
	public boolean validateUser(String user, String password)
	{
        return user.equalsIgnoreCase("harish") && password.equals("encapsulation");
    }
}
