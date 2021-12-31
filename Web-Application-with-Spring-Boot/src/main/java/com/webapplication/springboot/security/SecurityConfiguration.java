package com.webapplication.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
//  @Configuration is a Spring Annotation which helps us to add configurations.
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
// We have to extend WebSecurityConfigurerAdapter class and override its methods to add our
// configuration
	
	@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .passwordEncoder(NoOpPasswordEncoder.getInstance())
        		.withUser("Harish").password("encapsulation")
                .roles("USER", "ADMIN");
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login", "/h2-console/**").permitAll()
                .antMatchers("/", "/*todo*/**").access("hasRole('USER')").and()
                .formLogin();
        
        http.csrf().disable();
        http.headers().frameOptions().disable();
        
        // Explanation for this method : 
        // For /login permit anybody
        // If someone wants to hit / or any of the todo pages, check if he has USER role, if yes, allow him.
        // If not, show him a form login
    }
}
