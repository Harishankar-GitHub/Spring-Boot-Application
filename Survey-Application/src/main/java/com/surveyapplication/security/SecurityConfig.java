package com.surveyapplication.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	// Authentication : User -> Roles	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
        auth.inMemoryAuthentication()
        			.passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance())
        			.withUser("user1").password("secret1").roles("USER").and()
        			.withUser("admin1").password("secret1").roles("USER", "ADMIN");
    }
	
	// Authorization : Role -> Access
	// Any URL which has survey in it needs a USER Role.
	 @Override
	    protected void configure(HttpSecurity http) throws Exception
	 {
	        http.httpBasic()
	        	.and()
	        		.authorizeRequests()
	        		.antMatchers("/surveys/**").hasRole("USER")	        		
	        		.antMatchers("/users/**").hasRole("USER")
	        		// Any URL that starts with survey and users can be accessed by USER
	        		
	                .antMatchers("/**").hasRole("ADMIN")
	                // Other URLs can be accessed by ADMIN
	                
	                .and().csrf().disable()
	                .headers().frameOptions().disable();
	    }
}
